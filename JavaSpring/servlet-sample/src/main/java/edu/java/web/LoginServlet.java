package edu.java.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/j_security_check", name="login-servlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("j_username");
        String password = req.getParameter("j_password");

//        req.login(username, password);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/html");
//        dispatcher.forward(req, resp);

      //  resp.getWriter().write("Hello, you login successful!");

        try{
            req.login(username, password);

            req.setAttribute("say", "Hi Ha Noi!");

            RequestDispatcher dispatcher1 = req.getRequestDispatcher("hello.jsp");


            dispatcher1.forward(req, resp);

        }catch (Exception e){

            resp.getWriter().write("Sorry! Login failed!");
            e.printStackTrace(resp.getWriter());
        }

    }
}
