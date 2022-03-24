package hello.community.service;

import hello.community.domain.Board;
import hello.community.domain.Comment;
import hello.community.domain.User;
import hello.community.dto.WriteRequestCommentDto;
import hello.community.repository.BoardRepository;
import hello.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Comment> listComments(Board board) {
        return commentRepository.findAllWithUser(board);
    }

    @Transactional
    public void writeComment(Long boardId, User user, WriteRequestCommentDto writeRequestCommentDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("존재하지 않는 글입니다.");
                });
        commentRepository.save(new Comment(writeRequestCommentDto.getContent(), board, user));
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
