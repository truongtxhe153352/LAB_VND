package edu.java.spring.controller;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloClazzController {
//   @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView printMessage(){
//       ModelAndView mv = new ModelAndView();
//       mv.setViewName("index");
//       mv.addObject("message", "Hello Java Clazz!");
//       return mv;
//   }

   @RequestMapping(method = RequestMethod.GET)
   public ModelAndView printMessage(@RequestParam(value = "data", required=false)String message){
      ModelAndView mv = new ModelAndView();
      mv.setViewName("index");
      mv.addObject("message", message);
      return mv;
   }

   @RequestMapping(value = "welcome",method = RequestMethod.GET)
   public ModelAndView welcome(){
      ModelAndView mv = new ModelAndView();
      mv.setViewName("../clazz");
      mv.addObject("name", "Truong");
      return mv;
   }

   @RequestMapping(value = "data",produces = MediaType.TEXT_PLAIN_VALUE)
   public @ResponseBody String raw(){
      return "Xin chao moi nguoi";
   }

}
