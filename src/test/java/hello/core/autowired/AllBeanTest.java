package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,
                DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "User", Grade.VIP);
        int discountPrice1 = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice1).isEqualTo(1000);

        int discountPrice2 = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(discountPrice2).isEqualTo(2000);
    }

//    @Configuration
    public static class DiscountService {
        private Map<String, DiscountPolicy> policyMap;
        private List<DiscountPolicy> policies;

//        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}
