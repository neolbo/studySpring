package hello.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<!DOCTYPE html>\n");
        w.write("<html>\n");
        w.write("<head>\n");
        w.write("\t<meta charset=\"UTF-8\">\n");
        w.write("\t<title>Title</title>\n");
        w.write("</head>\n");
        w.write("<body>\n");
        w.write("\t<form action=\"/servlet/members/save\" method=\"post\">\n");
        w.write("\t\tusername: <input type=\"text\" name=\"username\" />\n");
        w.write("\t\tage: <input type=\"text\" name=\"age\" />\n");
        w.write("\t\t<button type=\"submit\">전송</button>\n");
        w.write("\t</form>\n");
        w.write("</body>\n");
        w.write("</html>\n");
    }
}
