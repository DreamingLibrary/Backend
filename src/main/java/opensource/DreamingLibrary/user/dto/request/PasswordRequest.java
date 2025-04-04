package opensource.DreamingLibrary.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordRequest {
    @NotBlank
    @Schema(description = "수정할 비밀번호", example = "pwd")
    String password;
}
