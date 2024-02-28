package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemoryMemberRepository;
import hello.login.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemoryMemberRepository memberRepository;
    private final SessionManager sessionManager;

    //    @GetMapping("/")
    public String home() {
        return "home";
    }

    //    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) {
            // 로그인 하지 않은 화면
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId);

        if (loginMember == null) {
            // 회원 정보 찾을 수 없음
            return "home";
        }

        // 로그인 성공
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        // 세션 관리자에 의해 저장된 회원 정보 조회
        Member member = (Member)sessionManager.getSession(request);
        if (member == null) {
            // 로그인 하지 않은 화면
            return "home";
        }

        // 로그인 성공
        model.addAttribute("member", member);
        return "loginHome";
    }
}