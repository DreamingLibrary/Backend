package opensource.DreamingLibrary.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateRequest{
        @NotBlank
        @Schema(description = "학번", example = "20211530")
        String studentNumber;
        @NotBlank
        @Schema(description = "회원 비밀번호", example = "pwd")
        String password;
        @NotBlank
        @Schema(description = "회원 이름", example = "정현정")
        String name;
        @Schema(description = "이메일", example = "gimijinn@gmail.com")
        String email;
        @NotBlank
        @Schema(description = "전화번호", example = "010-2433-0248")
        String phoneNumber;
}
