package edu.hanoi.jazz.dao;

import edu.hanoi.jazz.dao.model.User;

import java.util.List;

public interface UserDAO {
    public void insert(User user);
    public List<User> list();

    public List<User> list(Integer group);
    public User get(String username);

    public void delete(String name);
    public List<User> listOlder();

    public Double averageAge();

    public List<User> page(int page);

    public List<User> listUserByNameSQL();

    public void addBatch();
}
