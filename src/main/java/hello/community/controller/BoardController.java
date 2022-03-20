package hello.community.controller;

import hello.community.domain.Board;
import hello.community.domain.User;
import hello.community.dto.BoardUpdateDto;
import hello.community.dto.BoardWriteDto;
import hello.community.dto.CommentWriteDto;
import hello.community.service.BoardService;
import hello.community.service.CommentService;
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

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                       @PageableDefault(size = 5, sort="id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("boards", boardService.listBoard(pageable));

        if (user == null) {
            return "home";
        }

        model.addAttribute("user", user);
        return "loginHome";
    }

    @GetMapping("/boards/write")
    public String writeForm(@ModelAttribute("boardDto") BoardWriteDto boardWriteDto) {
        return "boards/writeForm";
    }

    @PostMapping("/boards/write")
    public String write(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = true) User user,
                        @Validated @ModelAttribute("boardDto") BoardWriteDto boardWriteDto,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "boards/writeForm";
        }

        boardService.writeBoard(boardWriteDto, user);

        return "redirect:/";
    }

    @GetMapping("/boards/{boardId}")
    public String view(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = true) User user,
                       @PathVariable Long boardId, Model model) {
        Board board = boardService.findBoardById(boardId);
        CommentWriteDto commentWriteDto = new CommentWriteDto();

        model.addAttribute("board", board);
        model.addAttribute("user", user);
        model.addAttribute("commentDto", commentWriteDto);


        return "boards/viewForm";
    }

    @GetMapping("/boards/{boardId}/delete")
    public String delete(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);

        return "redirect:/";
    }

    @GetMapping("/boards/{boardId}/update")
    public String updateForm(@PathVariable Long boardId, Model model) {
        Board board = boardService.findBoardById(boardId);
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto(board.getTitle(), board.getContent());
        model.addAttribute("boardDto", boardUpdateDto);

        return "boards/updateForm";
    }

    @PostMapping("/boards/{boardId}/update")
    public String update(@PathVariable Long boardId,
                         @Validated @ModelAttribute("boardDto") BoardUpdateDto boardUpdateDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "boards/updateForm";
        }

        boardService.updateBoard(boardId, boardUpdateDto);

        return "redirect:/";
    }

    @PostMapping("/boards/{boardId}/comments/write")
    public String writeComment(@PathVariable Long boardId,
                               @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = true) User user,
                               @Validated @ModelAttribute("commentDto") CommentWriteDto commentWriteDto,
                               BindingResult bindingResult, HttpServletRequest request) {
//        if (bindingResult.hasErrors()) {
//            return "";
//        }
        commentService.writeComment(boardId, user, commentWriteDto);

        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }

    @GetMapping("/boards/{boardId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable Long boardId, @PathVariable Long commentId,
                                HttpServletRequest request) {
        commentService.deleteComment(commentId);

        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }
}


