package edu.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/count", name = "count-servlet")
public class HitCounter extends HttpServlet {
    private int hitCount;
    public void init(){
        hitCount = 0;
    }

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        hitCount++;
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<br>Hit count = " + hitCount);
        writer.println("</html>");

    }
}
