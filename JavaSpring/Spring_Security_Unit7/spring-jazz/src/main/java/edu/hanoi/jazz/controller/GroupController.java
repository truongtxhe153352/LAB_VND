package edu.hanoi.jazz.controller;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;
import org.apache.log4j.LogXF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
    @Autowired
    private GroupDAO groupDAO;

    private final Logger LOGGER = Logger.getLogger(GroupController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addForm() {
        return new ModelAndView("group.form", "command", new Group());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView addNew(@Valid @ModelAttribute("command") Group group, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView model = new ModelAndView("group.form", "command", group);
            model.addObject("errors", result);
            return new ModelAndView("group.form");
        }

        if (group.getId() > 0){
            groupDAO.update(group);
        } else {
            groupDAO.insert(group);
            LOGGER.info("add new group ------>" + group);
        }
        return new ModelAndView("redirect:/group/list");
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "q", required = false)String pattern) {
        ModelAndView mv = new ModelAndView("group.list");
        mv.addObject("groups", groupDAO.list(pattern));
        return mv;
    }

    @RequestMapping(value = "/delete/")
    public ModelAndView delete(@PathVariable Integer id) {
        if (id == null) return new ModelAndView("redirect:/group/list");
        groupDAO.delete(id);
        return new ModelAndView("redirect:/group/list");
    }

    @RequestMapping(value = "/edit")
    public ModelAndView updateFrom(@RequestParam(value = "id", required = true)Integer id) {
       Group group = groupDAO.getGroupById(id);
       if (group == null) return new ModelAndView("redirect:/group/list");
       return new ModelAndView("group.form", "command", group);
    }

}
