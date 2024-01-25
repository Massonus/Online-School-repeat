package org.massonus;

import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreator {
    @Getter
    private static final SessionFactory sessionFactory
            = configureSessionFactory();

    private static SessionFactory configureSessionFactory() throws HibernateException {

        Configuration configuration = new Configuration().configure();

        return configuration.buildSessionFactory();
    }
}
