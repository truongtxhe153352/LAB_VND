package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {
    private final Logger LOGGER = Logger.getLogger(GroupDAOImpl.class);

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    public GroupDAOImpl() {
    }

    public GroupDAOImpl(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public LocalSessionFactoryBean getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Group group) {
        Session session = sessionFactory.getObject().openSession();
        try {
            //  session.save(group);
//            session.flush();

            //===
            session.getTransaction().begin();
            session.save(group);
            session.flush();
            session.getTransaction().commit();
            //===

            LOGGER.info("Save group " + group.getName() + " done!");
        } finally {
            session.close();
        }
        //System.out.println(sessionFactory + ": Insert group" + group);
    }

    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery("from Group ");
        try {
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        if (group == null) return;
        //===
        session.getTransaction().begin();
        session.delete(group);
        session.flush();
        session.getTransaction().commit();
        //===
        LOGGER.info("Delete group " + group.getName() + " successful!");
        session.close();
    }

    @Override
    public void update(Group group) {
        Session session = sessionFactory.getObject().openSession();
        group = (Group) session.merge(group);
        session.beginTransaction();
        session.save(group);
        session.flush();
        session.getTransaction().commit();
        LOGGER.info("Update group " + group.getName() + " sucessful!");
        session.close();
    }

    @Override
    public Group getGroupById(Integer id) {
        Session session = sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        return group;
    }

    @Override
    public List<Group> list(String pattern) {
        Session session = sessionFactory.getObject().openSession();
        if (pattern == null || pattern.length() < 1) {
            org.hibernate.query.Query query = session.createQuery("from Group");
            return (List<Group>) query.list();
        }
        Criteria criteria = session.createCriteria(Group.class);
        criteria.add(Restrictions.like("name", "%" + pattern + "%", MatchMode.ANYWHERE));
        return new ArrayList<Group>(criteria.list());
    }


}
