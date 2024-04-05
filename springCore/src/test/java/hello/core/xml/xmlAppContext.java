package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class xmlAppContext {

    ApplicationContext ac = new GenericXmlApplicationContext("AppConfig.xml");

    @Test
    void xmlAppContext() {
        Object bean = ac.getBean("memberService", MemberService.class);
        assertThat(bean).isInstanceOf(MemberService.class);
    }
}
