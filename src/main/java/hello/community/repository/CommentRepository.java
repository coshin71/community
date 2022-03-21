package hello.community.repository;

import hello.community.domain.Board;
import hello.community.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(
            value = "select c from Comment c join fetch c.user where c.board = :board"
    )
    List<Comment> findAllWithUser(@Param("board") Board board);
}
