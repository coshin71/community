package hello.community.repository;

import hello.community.domain.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardDetailRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<Board> findWithUserById(Long boardId) {
        List<Board> boards = em.createQuery("select b from Board b join fetch b.user where b.id = :id" ,
                        Board.class)
                .setParameter("id", boardId)
                .getResultList();
        return boards.stream().findAny();
    }
}
