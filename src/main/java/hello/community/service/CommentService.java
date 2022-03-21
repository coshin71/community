package hello.community.service;

import hello.community.domain.Board;
import hello.community.domain.Comment;
import hello.community.domain.User;
import hello.community.dto.CommentWriteDto;
import hello.community.repository.BoardRepository;
import hello.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void writeComment(Long boardId, User user, CommentWriteDto commentWriteDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("존재하지 않는 글입니다.");
                });
        commentRepository.save(new Comment(commentWriteDto.getContent(), board, user));
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
