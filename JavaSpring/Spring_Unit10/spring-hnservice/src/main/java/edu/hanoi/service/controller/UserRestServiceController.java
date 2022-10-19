package edu.hanoi.service.controller;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserRestServiceController {
    private Logger LOGGER = Logger.getLogger(UserRestServiceController.class);
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GroupDAO groupDAO;


    ////    @PreAuthorize("hasAnyRole('ADMIN')")
////    @PostFilter("filterObject.username == 'username-random25'")
//    @PreAuthorize("hasRole('USER')")
//    @PostFilter("hasPermission(filterObject, 'read')")
    @RequestMapping(value = "/list/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @PostFilter("hasPermission(filterObject, 'read')")
    public List<User> listUser() {
        //userDAO.addBatch();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        LOGGER.info("\n----------Login---------->" + auth.getAuthorities());

//        if (!request.isUserInRole("ROLE_ADMIN")){
//            throw new RuntimeException("Access Denied!");
//        }
        return userDAO.list();
    }


    @RequestMapping(value = "/list/groups", method = RequestMethod.GET)
    public Group[] listGroups() {
        return groupDAO.list().toArray(new Group[0]);
    }

    @RequestMapping(value = "/add/users", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        return userDAO.inserts(user);
    }

    @RequestMapping(value = "/get/user/{name}", method = RequestMethod.GET)
    public User getUer(@PathVariable String name) {
        return userDAO.get(name);
    }

    @RequestMapping(value = "/delete/user/{name}", method = RequestMethod.GET)
    public void delUser(@PathVariable String name) {
        userDAO.delete(name);
    }

    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user) {
        userDAO.update(user);
    }

}

