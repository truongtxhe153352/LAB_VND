package edu.hanoi.service.dao;

import edu.hanoi.spring.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO{
    private final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Override
    public String insert(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            Serializable value = session.save(user);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("Save data------------ " + user.getUsername() + " successful !---------------");
            return value.toString();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String name) {
        Session session = sessionFactory.getObject().openSession();
        try {
            User user = session.get(User.class, name);
            session.getTransaction().begin();
            if (user != null) session.delete(user);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("Delete data------------ " + user.getUsername() + " successful !---------------");
        } finally {
            session.close();
        }


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
    public User get(String username) {
        Session session = sessionFactory.getObject().openSession();
        try {
            return session.get(User.class, username);
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            session.update(user);
            session.flush();
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}
