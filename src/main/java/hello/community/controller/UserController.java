package hello.community.controller;

import hello.community.dto.SignupDto;
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
    public String signupForm(@ModelAttribute("signupDto") SignupDto signupDto) {
        return "users/SignupForm";
    }

    @PostMapping("/signup")
    public String save(@Validated @ModelAttribute("signupDto") SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "users/SignupForm";
        }

        if (userService.isDuplicatedLoginId(signupDto.getLoginId())) {
            bindingResult.reject("loginIdDuplicate", "아이디가 중복입니다.");
            return "users/SignupForm";
        }

        userService.save(signupDto);
        return "redirect:/";
    }
}
