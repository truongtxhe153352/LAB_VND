package edu.java.spring.controller;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.JavaClazz;
import edu.java.spring.model.Student;
import edu.java.spring.utils.XSLTUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
public class ClazzController {
    @Autowired
    private StudentDAO studentDAO;

    public ClazzController() {
    }

    public ClazzController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @RequestMapping(value = "/clazz/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody JavaClazz viewInXML() {
        return new JavaClazz(studentDAO.list());
    }

    @RequestMapping(value = "/clazz/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody JavaClazz viewInJSON() {
        return new JavaClazz(studentDAO.list());
    }

    @RequestMapping(value = "/clazz/xslt", method = RequestMethod.GET)
    public ModelAndView viewXSLT() throws JAXBException, ParserConfigurationException, IOException, SAXException {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("ClazzView");
        model.getModelMap().put("data", XSLTUtils.clazzToDomSource(clazz));
        return model;
    }

    @RequestMapping(value = "/clazz/excel", method = RequestMethod.GET)
    public ModelAndView viewExcel() throws JAXBException, ParserConfigurationException, IOException, SAXException {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("excelView");
        model.getModelMap().put("clazzObj", clazz);
        return model;
    }

    @RequestMapping(value = "/clazz/pdf", produces = "application/pdf")
    public ModelAndView viewPdf() {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView();
        model.setViewName("pdfView");
        model.getModelMap().put("clazzObj", clazz);
        return model;
    }

    @RequestMapping(value = "/clazz/report", produces = "application/pdf")
    public ModelAndView viewReport() {
//        🤣😂😊❤️😍😒👌😘💕😁👍🙌🤦‍♀️🤦‍♂️🤷‍♀️🤷‍♂️✌️🤞
        //        🤣😂😊❤️😍😒👌😘💕😁👍🙌🤦‍♀️🤦‍♂️🤷‍♀️🤷‍♂️✌️🤞
        //        🤣😂😊❤️😍😒👌😘💕😁👍🙌🤦‍♀️🤦‍♂️🤷‍♀️🤷‍♂️✌️🤞
        //        🤣😂😊❤️😍😒👌😘💕😁👍🙌🤦‍♀️🤦‍♂️🤷‍♀️🤷‍♂️✌️🤞
        //        🤣😂😊❤️😍😒👌😘💕😁👍🙌🤦‍♀️🤦‍♂️🤷‍♀️🤷‍♂️✌️🤞
        //        🤣😂😊❤️😍😒👌😘💕😁👍🙌🤦‍♀️🤦‍♂️🤷‍♀️🤷‍♂️✌️🤞
        //        🤣😂😊❤️😍😒👌😘💕😁👍🙌🤦‍♀️🤦‍♂️🤷‍♀️🤷‍♂️✌️🤞
        Student student = new Student();
        JRDataSource dataSource = new JRBeanCollectionDataSource(studentDAO.list());
        // JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView();
        model.setViewName("pdfReport");
        model.addObject("dataSource", dataSource);
        return model;
    }
}
