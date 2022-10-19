package edu.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(value = "/upload", name = "upload-servlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest rep,
                          HttpServletResponse resp)
            throws ServletException, IOException {

      String  appPath = rep.getServletContext().getRealPath("");

      resp.getWriter().println("----------" + appPath);
      rep.getParts().forEach(part->{
         String name = extractFileName(part);
      });

        File folder = new File(appPath, "temp");
        // tạo đường dẫn thư mục
        if (!folder.exists()) folder.mkdir();
        for (Part part :
                rep.getParts()) {
            String name = extractFileName(part);
            byte[] buff = new byte[4*1204];
            int read = -1;
          FileOutputStream  ouputStream = new FileOutputStream(new File(folder, name));
            try {
                InputStream inputStream = part.getInputStream();
                while ((read = inputStream.read(buff)) != -1) {
                    ouputStream.write(buff);
                }
            } finally {
                ouputStream.close();
            }

            Collection<String> headers = part.getHeaderNames();
            headers.forEach(header -> {
                try {
                    resp.getWriter().println(header + ":" + part.getHeader(header));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        resp.getWriter().println("Upload has been done successfully!");
    }
    private String extractFileName(Part part){
       String content = part.getHeader("content-disposition");
       Pattern pattern = Pattern.compile(".*filename\\=\"(.)\".*");
       Matcher matcher = pattern.matcher(content);
        return matcher.matches()?matcher.group(1):"unknown";
    }
}


