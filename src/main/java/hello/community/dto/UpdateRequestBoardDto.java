package hello.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRequestBoardDto {

    @NotBlank(message = "제목 입력은 필수입니다.")
    @Size(max = 25, message = "제목은 최대 25자입니다.")
    private String title;

    @NotBlank(message = "내용 입력은 필수입니다.")
    private String content;
}
