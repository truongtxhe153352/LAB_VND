package edu.hanoi.spring.controller;

import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.spring.model.Message;
import edu.hanoi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllerAPI {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = "application/json")
    public List<User> list() {
        return userDAO.list();
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.GET)
    public Message delUser(@PathVariable String name) {
        userDAO.delete(name);
        return new Message("Delete successful !!!");
    }

    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user) {
        userDAO.update(user);
    }

    @RequestMapping(value = "/user/get/{name}", method = RequestMethod.GET)
    public User getUer(@PathVariable String name) {
        return userDAO.get(name);
    }
}
