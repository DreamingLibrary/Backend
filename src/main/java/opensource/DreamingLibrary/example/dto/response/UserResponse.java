package opensource.DreamingLibrary.example.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import opensource.DreamingLibrary.example.entity.User;

@Builder
public record UserResponse(
        @NotNull
        @Schema(description = "회원 id", example = "1")
        Long id,
        @NotBlank
        @Schema(description = "로그인 id", example = "abcd")
        String loginId,
        @NotBlank
        @Schema(description = "비밀번호", example = "pwd")
        String password,
        @NotBlank
        @Schema(description = "이름", example = "정현정")
        String name
) {
    public static UserResponse of(User user){
        return UserResponse.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }
}
