package hello.community.dto;

import hello.community.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInfoUserDto {

    private Long id;

    private String loginId;

    public LoginInfoUserDto(User user) {
        this.id = user.getId();
        this.loginId = user.getLoginId();
    }
}
