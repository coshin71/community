package hello.community.domain.User;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 15, unique = true)
    private String loginId;

    @Column(nullable = false, length = 15)
    private String password;
}
