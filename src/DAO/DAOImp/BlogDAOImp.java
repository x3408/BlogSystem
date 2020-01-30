package DAO.DAOImp;

import Bean.Blog;
import Bean.User;
import DAO.BlogDAO;
import Utils.HibernateUtil;
import Utils.SqlHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class BlogDAOImp extends HibernateDaoSupport implements BlogDAO {
    @Override
    public List<Blog> findPage(int page,int limit) {
        /*List<Blog> list = null;
        QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        try {
            list = queryRunner.query("select * from blog limit ?,?", new BeanListHandler<>(Blog.class), (page-1)*limit, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;*/


//        Session session = HibernateUtil.getCurrentSession();

        List<Blog> list = getHibernateTemplate().execute(new HibernateCallback<List<Blog>>() {
            @Override
            public List<Blog> doInHibernate(Session session) throws HibernateException {
                String sql = "from Blog";
                List<Blog> list = session.createQuery(sql)
                        .setFirstResult((page-1)*limit)
                        .setMaxResults(limit)
                        .list();
                return list;
            }
        });

        return list;
        /*String sql = "from Blog";

        List list = session.createQuery(sql)
                .setFirstResult(page)
                .setMaxResults(limit)
                .list();

        return list;*/
    }

    @Override
    public int getTotalCount() {
        /*QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        long count = 0;
        try {
            count = queryRunner.query("select count(*) from blog", new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();*/

        //Hibernate方式
        /*Session session = HibernateUtil.getCurrentSession();

        String sql = "select count(*) from Blog";
        Long count = (Long) session.createQuery(sql).uniqueResult();

        return new Long(count).intValue();*/

        //Criteria
        DetachedCriteria dc = DetachedCriteria.forClass(Integer.class);
        dc.setProjection(Projections.rowCount());
        List<Integer> byCriteria = (List<Integer>) getHibernateTemplate().findByCriteria(dc);

        if(byCriteria != null && byCriteria.size()>0) {
            return byCriteria.get(0);
        } else {
            return 0;
        }
    }

    @Override
    public int getTotalCount(User user) {
        /*QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        long count = 0;
        try {
            count = queryRunner.query("select count(*) from u_b where userId = ?", new ScalarHandler<>(),user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();*/

        int count = getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                User user1 = session.get(User.class, user.getId());
                int count = user1.getBlogs().size();


                return count;
            }
        });

        return count;
    }

    @Override
    public List<Blog> findPage(User user, int page, int limit) {
        /*QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        List<Blog> list = null;

        try {
            list = queryRunner.query("select * from blog where id in (select blog from u_b where userId=?) limit ?,? ",
                    new BeanListHandler<>(Blog.class), user.getId(), (page-1)*limit, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;*/

        Session session = HibernateUtil.getCurrentSession();

        String sql = "select * from blog where id in (select blog from u_b where userId=?) limit ?,? ";

        List list = session.createSQLQuery(sql)
                .addEntity(Blog.class)
                .setParameter(0, user.getId())
                .setParameter(1,(page - 1) * limit)
                .setParameter(2, limit)
                .list();


        return list;
    }

    @Override
    public boolean addEssay(Blog blog, User user) {
       /*QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        try {
            queryRunner.update("insert into blog values(?,?,?)", blog.getId(), blog.getTitle(), blog.getContent());
            queryRunner.update("insert into u_b values(?,?)",user.getId(),blog.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;*/

//       Session session = HibernateUtil.getCurrentSession();
       /*User user1 = session.get(User.class, user.getId());
       user1.getBlogs().add(blog);*/

       /* User user1 = session.get(User.class, user.getId());
        Set<Blog> blogs = user1.getBlogs();
        blogs.add(blog);
        session.save(user1);*/

        boolean flag = getHibernateTemplate().execute(new HibernateCallback<Boolean>() {
            @Override
            public Boolean doInHibernate(Session session) throws HibernateException {
                User user1 = session.get(User.class, user.getId());
                Set<Blog> blogs = user1.getBlogs();
                blogs.add(blog);
                session.save(user1);
                return true;
            }
        });

       return flag;
    }
}
