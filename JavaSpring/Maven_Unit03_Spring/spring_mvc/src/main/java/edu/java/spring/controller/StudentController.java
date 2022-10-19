package edu.java.spring.controller;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Controller
public class StudentController {
    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = "student/add", method = RequestMethod.GET)
    public ModelAndView add() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("student.form");
        return new ModelAndView("studentForm", "command", new Student());
        // return mv;
    }

//    @RequestMapping(value = "student/save", method = RequestMethod.POST)
//    public ModelAndView save(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false) int age) {
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("student.view");
//        mv.addObject("name", name);
//        mv.addObject("age", age);
//
//        Student student = new Student(name, age);
//        mv.addObject("student", student);
//
//        return mv;
//    }

    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public ModelAndView listStudents(@RequestParam(value = "q", required = false) String query) {
        if (query == null) {
            ModelAndView model = new ModelAndView();
            model.setViewName("StudentList");
            model.addObject("students", studentDAO.list());
            return model;
        } else {
            ModelAndView model = new ModelAndView();
            model.setViewName("StudentList");
            model.addObject("q", query);
            studentDAO.listSearch(query);
            model.addObject("students", studentDAO.listSearch(query));
            return model;
        }
    }


//    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
//    public ModelAndView listStudents(@RequestParam(value = "q", required = false)String query){
//        if (query == null){
//            ModelAndView model = new ModelAndView();
//            model.setViewName("student.list");
//            model.addObject("students", studentDAO.list());
//            return model;
//        } else {
//            ModelAndView model = new ModelAndView();
//            model.setViewName("student.list");
//            model.addObject("q", query);
//            studentDAO.listSearch(query);
//            model.addObject("students", studentDAO.listSearch(query));
//            return model;
//        }
//    }


//    @RequestMapping(value = "student/add", method = RequestMethod.GET)
//    public ModelAndView add(@Valid @ModelAttribute("command") Student student, BindingResult result) {
//        if (result.hasErrors()) {
//            ModelAndView model = new ModelAndView("student.form", "command", student);
//            model.addObject("errors", result);
//            return model;
//        } else {
//            return new ModelAndView("student.form", "command", new Student());
//        }
//    }

    @RequestMapping(value = "student/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult result) {
        ModelAndView mv = new ModelAndView();

        if (result.hasErrors()) {
            ModelAndView model = new ModelAndView("StudentFrom", "command", student);
            model.addObject("errors", result);
            mv.setViewName("StudentFrom");
            return model;
        }

        //  studentDAO.insert(student);

        mv.addObject("student", student);
        mv.setViewName("student.view");

        // studentDAO.insert(student);

        if (student.getId() > 0) {
            studentDAO.update(student);
        } else {
            studentDAO.insert(student);
        }

        return new ModelAndView("redirect:/student/list");

        // return mv;
    }

//    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
//    public ModelAndView listStudent(){
//            ModelAndView model = new ModelAndView();
//            model.setViewName("student.list");
//            model.addObject("students", studentDAO.list());
//            return model;
//        }


    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        model.setViewName("student.list");
        studentDAO.delete(id);
        return "redirect:/student/list";
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        Student student = studentDAO.get(id);
        return new ModelAndView("StudentForm", "command", student);
    }

    @RequestMapping(value = "/student/edit/save", method = RequestMethod.POST)
    public String saveEdit() {
        return "forward:/student/save";
    }

    @RequestMapping(value = "/student/json/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Student viewJson(@PathVariable int id) {
        return studentDAO.get(id);
    }


//    @RequestMapping(value = "/student/avatar/save", method = RequestMethod.POST)
//    public String handleFormUpload(@RequestParam("file")MultipartFile file) throws IOException {
//        if (file.isEmpty())return "student.error";
//        byte[] bytes = file.getBytes();
//        System.out.println("found ---> " + bytes.length);
//        return "redirect:/student/list";
//    }

    @RequestMapping(value = "/student/avatar/save", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("file") MultipartFile file, String id, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) return "student.error";
        Path avatarFile = getImageFile(request, id);
        Files.write(avatarFile, file.getBytes(), StandardOpenOption.CREATE);

        try {
            byte[] bytes = file.getBytes();
            System.out.println("found ---> " + bytes.length);

            System.out.println(avatarFile);
            return "redirect:/student/list";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getImageFile(HttpServletRequest request, String id) {
        ServletContext servletContext = request.getSession().getServletContext();
        String diskPath = servletContext.getRealPath("/");
        File folder = new File(diskPath + File.separator + "avatar" + File.separator);
        folder.mkdirs();
        return (new File(folder, id + ".jpg")).toPath();
    }

    @RequestMapping(value = "/student/avatar/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImager(@PathVariable(value = "id") String id, HttpServletRequest request) throws IOException {
        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        if (id != null) {
            Path avatarPath = getImageFile((HttpServletRequest) request, id);
            if (Files.exists(avatarPath)) byteArrayOutput.write(Files.readAllBytes(avatarPath));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(byteArrayOutput.toByteArray(), headers, HttpStatus.CREATED);
    }




}
