package Service;

import Bean.Blog;
import Bean.BlogPage;
import Bean.User;
import DAO.BlogDAO;
import DAO.DAOImp.BlogDAOImp;
import Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BlogService {
    private BlogDAOImp dao;

    public BlogPage showPage(String s_page) {
        int page = Integer.parseInt(s_page);
        int limit = 1;
        int totalCount = totalCountAll();
        int totalPage;

        if (totalCount % limit == 0)
            totalPage = totalCount / limit;
        else
            totalPage = totalCount / limit + 1;

        BlogDAO dao = new BlogDAOImp();
        List<Blog> list = dao.findPage(page, limit);

        BlogPage blogPage = new BlogPage();
        blogPage.setPage(page);
        blogPage.setLimit(limit);
        blogPage.setTotalCount(totalCount);
        blogPage.setTotalPage(totalPage);
        blogPage.setList(list);


        return blogPage;
    }

    private int totalCountAll() {
        BlogDAO dao = new BlogDAOImp();
        int totalCount = dao.getTotalCount();
        return totalCount;
    }

    private int totalCount(User user) {
        BlogDAO dao = new BlogDAOImp();
        int totalCount = dao.getTotalCount(user);
        return totalCount;
    }

    public BlogPage showPage(String s_page, User user) {
        int page = Integer.parseInt(s_page);
        int limit = 1;
        int totalCount = totalCount(user);
        int totalPage;

        if (totalCount % limit == 0)
            totalPage = totalCount / limit;
        else
            totalPage = totalCount / limit + 1;

        BlogDAO dao = new BlogDAOImp();
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<Blog> list = dao.findPage(user, page, limit);

        transaction.commit();
        BlogPage blogPage = new BlogPage();
        blogPage.setPage(page);
        blogPage.setLimit(limit);
        blogPage.setTotalCount(totalCount);
        blogPage.setTotalPage(totalPage);
        blogPage.setList(list);

        return blogPage;
    }

    public boolean addEssay(Blog blog, User user) {
        /*BlogDAO dao = new BlogDAOImp();
        boolean flag = dao.addEssay(blog, user);
        return flag;*/

        boolean flag = dao.addEssay(blog, user);


        return flag;
    }

    public void setDao(BlogDAOImp dao) {
        this.dao = dao;
    }
}
