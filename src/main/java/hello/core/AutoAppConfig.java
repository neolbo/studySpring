package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
        // basePackages = "hello.core",        // component 시작 위치 지정 & Default => ComponentScan이 달려있는 패키지 부터
        // 따라서 ComponentScan 이 달려있는 AutoAppConfig.class 를 프로젝트의 최상단에 두고 사용 권장
        excludeFilters= @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)        
        // component 제외 필터
)
public class AutoAppConfig {

    /*@Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
