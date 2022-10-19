//package edu.java.web;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.sql.*;
//
//public class SaveServlet extends HttpServlet {
//    Connection connection;
//    Statement statement;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        File file = new File("/user_db");
//        try{
//            connection = ConnectionSQL.getSingeltonPatern().getConnection();
//            statement = connection.createStatement();
//            DatabaseMetaData dmd = connection.getMetaData();
//            ResultSet rs = dmd.getTables(null, null, null, new String[]{"TABLE"});
//            if
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//
//    @Override
//    public void destroy() {
//        statement.getConnection("jdbc:derby:C:/Temp/userdb;shutdown=true");
//    }
//}

package edu.java.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;

@WebServlet(value = "/save", name = "register")
public class SaveServlet extends HttpServlet {
    private Connection connection;
    private Statement statement;

    @Override
    public void init() throws ServletException {
        File file = new File("./user_db");

        try {
            connection = ConnectionSQL.getSingeltonPatern().getConnection();
            statement = connection.createStatement();
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, null, null, new String[]{"TABLE"});
            if (rs.next() && rs.getString("TABLE_NAME").compareTo("HANOI_USER") == 0) return;
            statement.executeUpdate("create table hanoi_user(username varchar(500) , password varchar(500), email varchar(1000))");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File("./user_db");
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        //  System.out.println(file.getAbsolutePath());
        String sql = "INSERT INTO hanoi_user(username, password, email) values('" + user + "','" + password + "','" + email + "')";
        try {
            statement.execute(sql);
            resp.getWriter().write("Register Successfull");
//            req.setAttribute("mess",mess);
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/register.html");
//            dispatcher.forward(req,resp);

            req.getRequestDispatcher("view-users.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").compareTo("del") == 0) {
            String sql = "delete from hanoi_user where username = \'" + req.getParameter("user") + "\'";
            try {
                statement.executeUpdate(sql);
                req.getRequestDispatcher("view-users.jsp").forward(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}