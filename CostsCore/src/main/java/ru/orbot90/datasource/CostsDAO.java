package ru.orbot90.datasource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * Created by orbot on 06.07.15.
 */
public class CostsDAO {
    private SessionFactory sessionFactory;
    private static CostsDAO instance;

    private CostsDAO() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static CostsDAO getInstance() {
        if(null == instance) {
            instance = new CostsDAO();
        }
        return instance;
    }

    public void save(Object o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();
        session.close();
    }
}
