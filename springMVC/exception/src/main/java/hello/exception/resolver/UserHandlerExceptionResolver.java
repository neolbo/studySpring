package hello.exception.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    public static final String JSON = "application/json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof UserException) {
                log.info("UserException resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if (acceptHeader.equals(JSON)) {
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("exception", ex.getClass());
                    errorResult.put("message", ex.getMessage());
                    String result = objectMapper.writeValueAsString(errorResult);

                    response.setContentType(JSON);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(result);

                    return new ModelAndView();
                } else {
                    // text/html
                    return new ModelAndView("error/4xx");
                }
            }
        } catch (IOException e) {
            log.error("resolver ex", ex);
        }

        return null;
    }
}
