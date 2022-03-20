package hello.community.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "REPLY_ID")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
