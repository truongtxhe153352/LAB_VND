package edu.hanoi.service.dao.impl;

import edu.hanoi.service.model.User;

import java.util.Random;

public class UserFactory {
    public static User generate(int index){
        User user = new User();
        Random r = new Random();
        user.setUsername("username-random" + index);
        user.setPassword("password" + r.nextInt() + 1);
        user.setEmail("name" + r.nextInt() + 1 + "@gmail.com");
        user.setAge(r.nextInt(100) + 1);
        user.setGroupId(1);
        return user;
    }
}
