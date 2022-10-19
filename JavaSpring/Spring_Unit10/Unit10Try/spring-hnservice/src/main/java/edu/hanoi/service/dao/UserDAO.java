package edu.hanoi.service.dao;

import edu.hanoi.service.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> list();

    public void addBatch();
}
