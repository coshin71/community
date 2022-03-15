package hello.community.controller;

import hello.community.domain.User.User;
import hello.community.service.BoardService;
import hello.community.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user, Model model) {
        model.addAttribute("boards", boardService.list());

        if (user == null) {
            return "home";
        }

        model.addAttribute("user", user);
        return "loginHome";
    }

}
