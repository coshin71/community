package hello.community.domain.User;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String loginId;

    private String password;

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public User() {
    }
}
