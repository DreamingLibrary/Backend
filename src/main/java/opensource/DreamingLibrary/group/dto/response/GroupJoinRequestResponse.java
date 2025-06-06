package opensource.DreamingLibrary.group.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import opensource.DreamingLibrary.group.entity.GroupUser;

public record GroupJoinRequestResponse(
        @NotNull
        @Schema(description = "유저 ID", example = "1")
        Long userId,

        @NotNull
        @Schema(description = "유저 학번", example = "20201234")
        String studentNumber,

        @NotNull
        @Schema(description = "현재 상태", example = "PENDING")
        GroupUser.RequestStatus status
) {
    public static GroupJoinRequestResponse of(GroupUser groupUser) {
        return new GroupJoinRequestResponse(
                groupUser.getUser().getId(),
                groupUser.getUser().getStudentNumber(),
                groupUser.getStatus()
        );
    }
}
