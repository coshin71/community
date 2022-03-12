package hello.community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class SignupDto {

    @NotBlank(message = "ID 입력은 필수입니다.")
    @Size(max=15, message="ID는 최대 15자입니다.")
    private String loginId;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Size(max=15, message="비밀번호는 최대 15자입니다.")
    private String password;
}
