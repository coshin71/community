package hello.community.controller;

import hello.community.domain.User.User;
import hello.community.repository.UserRepository;
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

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user) {
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {

        System.out.println("user.getLoginId() = " + user.getLoginId());
        System.out.println("user.getPassword() = " + user.getPassword());

        if (bindingResult.hasErrors()) {
            return "users/addUserForm";
        }

        userService.save(user);
        return "redirect:/home";
    }
}
