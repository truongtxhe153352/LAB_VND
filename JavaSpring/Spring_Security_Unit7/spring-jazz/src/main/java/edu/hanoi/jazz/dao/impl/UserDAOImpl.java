package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    private final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void insert(User user) {
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
    }

    @Override
    public List<User> list() {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery("from User");
        try {
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

//    @Override
//    public List<User> list(Integer group) {
//        Session session = sessionFactory.getObject().openSession();
//        try {
//            if (group == null || group < 0) {
//                Query query = session.createQuery("from User");
//                return (List<User>) query.list();
//            }
//            Criteria criteria = session.createCriteria(User.class);
//            criteria.add(Restrictions.eq("groupId", group));
//            return new ArrayList<User>(criteria.list());
//        }finally {
//            session.close();
//        }
//    }

    @Override
    public List<User> list(Integer group) {
        Session session = sessionFactory.getObject().openSession();
        try {
            if (group == null || group < 0) {
                Query query = session.createQuery("from User order by age desc");
                return (List<User>) query.list();
            }
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("groupId", group));
            return new ArrayList<User>(criteria.list());
        }finally {
            session.close();
        }
    }

    @Override
    public User get(String username) {
        Session session = sessionFactory.getObject().openSession();
        User user = session.get(User.class, username);
        return user;
    }

    @Override
    public void delete(String name) {
        Session session = sessionFactory.getObject().openSession();
        String sql = "delete from User where username like :name";
        session.getTransaction().begin();
        Query query = session.createQuery(sql);
        query.setParameter("name", name);
        Integer result = query.executeUpdate();
        session.getTransaction().commit();
        LOGGER.info("Rows affected : " + result + "\n\n");
        session.close();
    }

    @Override
    public List<User> listOlder() {
        Session session = sessionFactory.getObject().openSession();
        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.gt("age", 50));
        return (List<User>) cr.list();
    }

    @Override
    public int averageAge() {
        Session session = sessionFactory.getObject().openSession();
        Criteria cr = session.createCriteria(User.class);
        cr.setProjection(Projections.avg("age"));
        List avgAge = cr.list();
        return ((Double) avgAge.get(0)).intValue();
    }


    private final static int SIZE_OF_PAGE = 2;
    @Override
    public List<User> page(int page) {
        Session session = sessionFactory.getObject().openSession();
        Criteria criteria = session.createCriteria(User.class);
        int start = (page -1 )*SIZE_OF_PAGE;
        criteria.setFirstResult(start);
        criteria.setMaxResults(SIZE_OF_PAGE);
        return (List<User>) criteria.list();
    }

    @Override
    public List<User> listUserByNativeSQL() {
        Session session = sessionFactory.getObject().openSession();
        String sql = "select * from hn_user";
        NativeQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);

        return query.list();
    }

    @Override
    public void addBatch() {
        Session session = sessionFactory.getObject().openSession();
        org.hibernate.Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (int i = 0; i < 50; i++) {
                session.save(UserFactory.generate(i+1));
            }
            session.flush();
            tx.commit();
        } catch (HibernateException e){
            if (tx != null) tx.rollback();
            LOGGER.error(e,e);@Autowired
            private UserDAO userDAO;
        } finally {
            session.close();
        }
    }


}
