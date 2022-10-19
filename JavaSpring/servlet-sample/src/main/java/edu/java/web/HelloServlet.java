package edu.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/hello", name = "hello-servlet")
public class HelloServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
        throws ServletException, IOException{
        resp.getWriter().println("Hello Ha Noi Java Clazz. Change");
        resp.getWriter().println("Hello " + req.getParameter("user"));
    }
}
