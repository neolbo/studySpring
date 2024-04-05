package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURI = request.getRequestURI();        // 예제에는 toString() 붙어있는데 없어도 될 듯?
//        MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger.getClass() = " + myLogger.getClass()); //test

        myLogger.setRequestURL(requestURI);

        myLogger.log("controller test");
        logDemoService.logic("testID");
        return "OK";
    }
}
