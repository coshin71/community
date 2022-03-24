package hello.community.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WriteRequestCommentDto {

    @NotNull(message = "내용 입력은 필수입니다.")
    private String content;
}
