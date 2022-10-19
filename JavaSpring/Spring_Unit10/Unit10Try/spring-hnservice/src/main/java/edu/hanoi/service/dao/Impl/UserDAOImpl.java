package edu.hanoi.service.dao.Impl;

import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("userDAO")
public class UserDAOImpl implements UserDAO {
    private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    private LocalSessionFactoryBean sessionFactory;


    public LocalSessionFactoryBean getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<User> list() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from User order by age desc");
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }


    @Override
    public String insert(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            session.save(user);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("Save group " + user.getUsername() + " done!");
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void addBatch() {
        Session session = sessionFactory.getObject().openSession();
        org.hibernate.Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (int i = 0; i < 50; i++) {
                session.save(UserFactory.generate(i + 1));
            }
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
    }
}