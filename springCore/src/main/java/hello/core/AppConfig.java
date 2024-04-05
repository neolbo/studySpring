package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 코드 상
// call AppConfig.memberService
// call AppConfig.memberRepository
// call AppConfig.orderService
// call AppConfig.memberRepository
// call AppConfig.memberRepository
// 이렇게 나와야 할 것 같지만

// 실행해 보면 사실
// call AppConfig.memberService
// call AppConfig.memberRepository
// call AppConfig.orderService
// 이렇게 나온다

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
//        System.out.println("call AppConfig.memberService");

        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
//        System.out.println("call AppConfig.orderService");

        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public MemberRepository memberRepository() {
//        System.out.println("call AppConfig.memberRepository");

        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
