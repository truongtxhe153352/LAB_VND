package edu.hanoi.jazz.service;

import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class JazzConnectionSignUp implements ConnectionSignUp {
    @Autowired
    private UserDAO userDAO;

    private final Logger LOGGER = Logger.getLogger(JazzConnectionSignUp.class);
    @Override
    public String execute(Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();
        LOGGER.info("============== > id " + userProfile.getId() + " name :" + userProfile.getName());
        User user = userDAO.get(userProfile.getEmail());
        if (user != null) return user.getUsername();

        User user1 = new User();
        user1.setUsername(userProfile.getEmail());
        user1.setPassword("123");
        user1.setGroupId(103);
        user1.setEmail(userProfile.getEmail());
        userDAO.insert(user);

        return user.getUsername();
    }
}
