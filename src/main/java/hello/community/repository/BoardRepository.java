package hello.community.repository;

import hello.community.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(
            value = "select b from Board b join fetch b.user",
            countQuery = "select count(b) from Board b"
    )
    Page<Board> findAllWithUser(Pageable pageable);
}
