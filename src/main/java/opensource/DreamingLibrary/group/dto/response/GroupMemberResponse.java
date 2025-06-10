package opensource.DreamingLibrary.group.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record GroupMemberResponse(
        @NotNull
        @Schema(description = "유저 ID", example = "1")
        Long userId,

        @NotNull
        @Schema(description = "유저 학번", example = "20201234")
        String studentNumber,

        @NotNull
        @Schema(description = "유저 이름", example = "홍길동")
        String name
) {
    public static GroupMemberResponse of(Long userId, String studentNumber, String name) {
        return new GroupMemberResponse(userId, studentNumber, name);
    }
}
