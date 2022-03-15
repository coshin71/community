package hello.community.controller;

import hello.community.dto.UserSignupDto;
import hello.community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("signupDto") UserSignupDto userSignupDto) {
        return "users/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("signupDto") UserSignupDto userSignupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/signupForm";
        }

        if (userService.isDuplicatedLoginId(userSignupDto.getLoginId())) {
            bindingResult.reject("loginIdDuplicate", "아이디가 중복입니다.");
            return "users/signupForm";
        }

        userService.signup(userSignupDto);
        return "redirect:/";
    }
}
