package edu.hanoi.spring.controller;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.spring.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupDAO groupDAO;

    @RequestMapping(value = "/groups")
    public List<Group> list() {
        return groupDAO.list();
    }
}
