package DAO.DAOImp;

import Bean.Blog;
import Bean.User;
import DAO.BlogDAO;
import Utils.SqlHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BlogDAOImp implements BlogDAO {
    @Override
    public List<Blog> findPage(int page,int limit) {
        List<Blog> list = null;
        QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        try {
            list = queryRunner.query("select * from blog limit ?,?", new BeanListHandler<>(Blog.class), (page-1)*limit, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int getTotalCount() {
        QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        long count = 0;
        try {
            count = queryRunner.query("select count(*) from blog", new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    @Override
    public int getTotalCount(User user) {
        QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        long count = 0;
        try {
            count = queryRunner.query("select count(*) from u_b where userId = ?", new ScalarHandler<>(),user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    @Override
    public List<Blog> findPage(User user, int page, int limit) {
        QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        List<Blog> list = null;

        try {
            list = queryRunner.query("select * from blog where id in (select blog from u_b where userId=?) limit ?,? ",
                    new BeanListHandler<>(Blog.class), user.getId(), (page-1)*limit, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean addEssay(Blog blog, User user) {
        QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        try {
            queryRunner.update("insert into blog values(?,?,?)", blog.getId(), blog.getTitle(), blog.getContent());
            queryRunner.update("insert into u_b values(?,?)",user.getId(),blog.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
