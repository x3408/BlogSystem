package Junit;

import Bean.Blog;
import Bean.User;
import Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.Set;

public class DaoJunit {
    @Test
    public void function() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Blog blog = new Blog();
        blog.setTitle("test");
        blog.setId("123");

        User user = session.get(User.class, "0317397e-83d2-4f72-92e0-f604fb177331");
        user.getBlogs().add(blog);
        System.out.println(blog.getId());

        transaction.commit();
    }
}
