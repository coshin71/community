package hello.community.domain.Board;

import hello.community.domain.User.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
