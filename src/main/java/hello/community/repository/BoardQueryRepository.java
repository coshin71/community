package hello.community.repository;

import hello.community.domain.Board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final EntityManager em;

    public List<Board> findAllWithUser(int offset, int limit) {
        return em.createQuery(
                        "select b from Board b" +
                                " join fetch b.user u order by b.id desc", Board.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
