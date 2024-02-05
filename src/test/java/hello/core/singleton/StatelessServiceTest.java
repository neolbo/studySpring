package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatelessServiceTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    public void StatelessServiceSingleTon() {
        StatelessService statelessService1 = ac.getBean("statelessService", StatelessService.class);
        StatelessService statelessService2 = ac.getBean("statelessService", StatelessService.class);

        int userAPrice = statelessService1.Order("UserA", 10000);
        int userBPrice = statelessService2.Order("UserB", 20000);

        System.out.println("userAPrice = " + userAPrice);
        assertThat(userAPrice).isEqualTo(10000);
    }

    public static class TestConfig {
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}