package opensource.DreamingLibrary.group.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record GroupMemberResponse(
        @NotNull
        @Schema(description = "유저 ID", example = "1")
        Long userId,

        @NotNull
        @Schema(description = "유저 학번", example = "20201234")
        String studentNumber
) {
    public static GroupMemberResponse of(Long userId, String studentNumber) {
        return new GroupMemberResponse(userId, studentNumber);
    }
}
