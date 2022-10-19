package edu.hanoi.service.dao;

import edu.hanoi.spring.model.User;

import java.util.List;

public interface UserDAO {
    public String insert(User user);

    public void delete(String name);

    public List<User> list();

    public User get(String username);

    public void update(User user);
}
