package hello.community.repository;

import hello.community.domain.Board;
import hello.community.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(
            value = "select b from Board b join fetch b.user",
            countQuery = "select count(b) from Board b"
    )
    public Page<Board> findAllWithUser(Pageable pageable);
}
