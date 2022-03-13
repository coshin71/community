package hello.community.controller;

import hello.community.dto.BoardWriteDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @GetMapping("/write")
    public String writeForm(@ModelAttribute("boardDto")BoardWriteDto boardWriteDto) {
        return "boards/writeForm";
    }

    @PostMapping("/write")
    public String write(@Validated @ModelAttribute("boardDto")BoardWriteDto boardWriteDto,
                        BindingResult bindingResult) {
        return "redirect:/";
    }
}
