package opensource.DreamingLibrary.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import opensource.DreamingLibrary.user.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse{
        @NotNull
        @Schema(description = "회원 id", example = "1")
        Long id;
        @NotBlank
        @Schema(description = "학번", example = "20211530")
        String studentNumber;
        @NotBlank
        @Schema(description = "회원 이름", example = "정현정")
        String name;
        @NotBlank
        @Schema(description = "역할", example = "user")
        String role;
        @NotBlank
        @Schema(description = "이메일", example = "gimijinn@gmail.com")
        String email;
        @Schema(description = "전화번호", example = "010-2433-0248")
        String phoneNumber;

        public static UserResponse from(User entity){
                return UserResponse.builder()
                        .id(entity.getId())
                        .studentNumber(entity.getStudentNumber())
                        .role(entity.getRole().toString())
                        .name(entity.getName())
                        .email(entity.getEmail())
                        .phoneNumber(entity.getPhoneNumber())
                        .build();
        }
}
