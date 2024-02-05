package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
@Getter
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*@Autowired//(required = false) - null 이어도 상관 없으면        default = true
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("수정자 - OrderServiceImpl.setMemberRepository");
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("수정자 OrderServiceImpl.setDiscountPolicy");
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

    // @Autowired   : 생성자가 1개뿐이면 생략 가능

    // @RequiredArgsConstructor (lombok) 가 final 이 붙은 것들을 자동으로 생성자 만들어줌
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        /*System.out.println("생성자 - OrderServiceImpl.OrderServiceImpl");
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);*/

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
