package edu.hanoi.spring.controller;

import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.spring.model.Message;
import edu.hanoi.spring.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserDAO userDAO;

//    @MessageMapping("/user")
//    @SendTo("/topic/chat")
//    public Message add(User user, Message username) {
////        System.out.println("user: " + user.getUsername() + " - email " + user.getEmail());
////        return new Message(user.getUsername());
//        String status = userDAO.insert(user);
//        return new Message("Save " + status + " successful !");
//    }

    @MessageMapping("/user")
    @SendTo("/topic/chat")
    public Message add(User user, Message username) {
//        System.out.println("user: " + user.getUsername() + " - email " + user.getEmail());
//        return new Message(user.getUsername());
        if (username.getContent() == null){
            String status = userDAO.insert(user);
            return new Message("Save " + status + " successful !");
        } else {
            userDAO.update(user);
            System.out.println(username.getContent());
            System.out.println("update user : "+user.getUsername()+" - email "+user.getEmail()+"  save " + "successfull");
            return new Message("update user "+user.getUsername()+" - email "+user.getEmail()+"  save "+ "successfull");
        }
    }

}
