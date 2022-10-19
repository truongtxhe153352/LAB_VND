package edu.hanoi.jazz.controller;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class UserController {
    private Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GroupDAO groupDAO;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addForm() {
        ModelAndView mv = new ModelAndView("user.form", "command", new User());
        mv.addObject("groups", toGroupMap(groupDAO.list()));
        return mv;
    }

    private Map<Integer, String> toGroupMap(List<Group> groups) {
        Map<Integer, String> map = new HashMap<>();
        groups.forEach(group -> map.put(group.getId(), group.getName()));
        return map;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView addNew(User user, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("user.form", "command", new User());
            mv.addObject("groups", toGroupMap((groupDAO.list())));
            mv.addObject("errors", result);
            return mv;
        }
        userDAO.insert(user);
        LOGGER.info("add new user --------- > " + user);
        return new ModelAndView("redirect:/account/add");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Integer groupId) {
        ModelAndView mv = new ModelAndView("user.list");
        mv.addObject("users", userDAO.list(groupId));
        return mv;
    }

    @RequestMapping(value = "/listNull", method = RequestMethod.GET)
    public ModelAndView listNull() {
        ModelAndView mv = new ModelAndView("user.list");
        mv.addObject("users", userDAO.list());
       // mv.addObject("users", userDAO.page(1));
        mv.addObject("average", userDAO.averageAge());
        return mv;
    }

    @RequestMapping(value = "/detail/{username}", method = RequestMethod.GET)
    public ModelAndView list1(@PathVariable String username) {
        ModelAndView mv = new ModelAndView("user.detail");
        mv.addObject("user", userDAO.get(username));
        return mv;
    }

    @RequestMapping(value = "/delete-{name}", method = RequestMethod.GET)
    public String delete(@PathVariable String name) {
        userDAO.delete(name);
        return "redirect:/account/listNull";
    }

    @RequestMapping(value = "/listOlder", method = RequestMethod.GET)
    public ModelAndView listOlder() {
        ModelAndView mv = new ModelAndView("user.list");
       // mv.addObject("users", userDAO.listOlder());
        mv.addObject("users", userDAO.listUserByNameSQL());
        return mv;
    }

    @RequestMapping(value = "/add-more", method = RequestMethod.GET)
    public String addRandom() {
        userDAO.addBatch();
        return "redirect:/account/listNull";
    }

}
