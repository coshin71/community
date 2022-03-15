package hello.community.controller;

import hello.community.domain.User.User;
import hello.community.dto.BoardWriteDto;
import hello.community.service.BoardService;
import hello.community.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write")
    public String writeForm(@ModelAttribute("boardDto")BoardWriteDto boardWriteDto) {
        return "boards/writeForm";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = true) User user,
                        @Validated @ModelAttribute("boardDto")BoardWriteDto boardWriteDto,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "boards/writeForm";
        }

        boardService.write(boardWriteDto, user);

        return "redirect:/";
    }
}
