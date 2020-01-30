package Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sf;

    static {
        Configuration configuration = new Configuration().configure();

        sf = configuration.buildSessionFactory();
    }

    public static Session getCurrentSession() {
        Session currentSession = sf.getCurrentSession();

        return currentSession;
    }

    public static Session openSession() {
        Session session = sf.openSession();

        return session;
    }
}
