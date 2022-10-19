package edu.hanoi.service.dao;

import edu.hanoi.spring.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupDAOImpl implements GroupDAO{

    private final Logger LOGGER = Logger.getLogger(GroupDAOImpl.class);
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery("from Group");
        try {
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }
}
