package edu.java.web;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/html"})
public class GzipFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("\n Destroy filter!\n");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String encoding = httpRequest.getHeader("Accept-Encoding");
        if (encoding != null && encoding.indexOf("gzip") > -1) {
            httpResponse.addHeader("Content-Encoding", "gzip");
            GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(httpResponse);
            filterChain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            filterChain.doFilter(request, response);
        }

        PrintWriter writer = response.getWriter();
        writer.println("=================\n Filter intercepted\n================");
        writer.flush();

        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("\n Destroy filter!\n");
    }


}
