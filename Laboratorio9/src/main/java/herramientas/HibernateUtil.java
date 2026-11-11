package herramientas;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.HibernateException;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Session session;

    static {
        try {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            Metadata metadata = new MetadataSources(ssr)
                    .getMetadataBuilder()
                    .build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();

            session = sessionFactory.openSession();
        } catch (HibernateException ex) {
            System.err.println("Error al crear la conf de hibernate: "
                    + ex.getMessage());
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
