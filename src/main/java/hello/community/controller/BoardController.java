package hello.community.controller;

import hello.community.domain.Board.Board;
import hello.community.domain.User.User;
import hello.community.dto.BoardWriteDto;
import hello.community.repository.BoardRepository;
import hello.community.service.BoardService;
import hello.community.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                       @PageableDefault(size = 5, sort="id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("boards", boardService.list(pageable));

        if (user == null) {
            return "home";
        }

        model.addAttribute("user", user);
        return "loginHome";
    }

    @GetMapping("/boards/write")
    public String writeForm(@ModelAttribute("boardDto")BoardWriteDto boardWriteDto) {
        return "boards/writeForm";
    }

    @PostMapping("/boards/write")
    public String write(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = true) User user,
                        @Validated @ModelAttribute("boardDto")BoardWriteDto boardWriteDto,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "boards/writeForm";
        }

        boardService.write(boardWriteDto, user);

        return "redirect:/";
    }

    @GetMapping("/boards/{boardId}")
    public String view(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = true) User user,
                       @PathVariable Long boardId, Model model) {
        Board board = boardRepository.findById(boardId).orElse(null);
        model.addAttribute("board", board);
        model.addAttribute("user", user);

        return "boards/viewForm";
    }
}


