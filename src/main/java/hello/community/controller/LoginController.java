package hello.community.controller;

import hello.community.domain.User.User;
import hello.community.dto.UserLoginDto;
import hello.community.service.LoginService;
import hello.community.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto") UserLoginDto userLoginDto) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginDto") UserLoginDto userLoginDto,
                        BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        User loginUser = loginService.login(userLoginDto.getLoginId(), userLoginDto.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginUser);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


}
