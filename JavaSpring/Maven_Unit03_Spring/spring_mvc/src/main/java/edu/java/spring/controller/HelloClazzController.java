package edu.java.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.PanelUI;

@Controller
@RequestMapping("/hello")
public class HelloClazzController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView prinMessage(@RequestParam(value = "data", required = false)String message) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("message", "Hello Java Clazz!");

        mv.addObject("message", message);
        return mv;
    }

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public ModelAndView welcome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/clazz");
        mv.addObject("name", "Truong!");
        return mv;
    }

    @RequestMapping(value = "site", method = RequestMethod.GET)
    public String redirect(){
        return "redirect:http://moon.vn";
    }

    @RequestMapping(value = "data", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String saw(){
    return "Xin chao moi nguoi";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "redirect:/student/list";
    }


}