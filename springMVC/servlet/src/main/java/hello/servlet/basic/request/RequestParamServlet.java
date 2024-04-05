package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
* 1. 파라미터 전송 기능
* Http://localhost:8080/request-param?username=kim&age=20
*
* 2. 동일한 파라미터 전송 기능
* Http://localhost:8080/request-param?username=kim&age=20&username=jeong
* */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("[전체 파라미터 조회]");
        request.getParameterNames().asIterator()
                .forEachRemaining(parameterName -> System.out.println(parameterName + ": " + request.getParameter(parameterName)));
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(\"username\")");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username: " + name);
        }
        System.out.println();
        System.out.println("request.getParameter(\"username\")");
        String username1 = request.getParameter("username");
        System.out.println(username1);
        response.getWriter().write("ok");
    }
}
