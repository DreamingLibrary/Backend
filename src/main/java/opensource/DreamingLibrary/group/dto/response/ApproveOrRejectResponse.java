package opensource.DreamingLibrary.group.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import opensource.DreamingLibrary.group.entity.GroupUser;

public record ApproveOrRejectResponse(
        @NotNull
        @Schema(description = "그룹 ID", example = "1")
        Long groupId,

        @NotNull
        @Schema(description = "유저 ID", example = "2")
        Long userId,

        @NotNull
        @Schema(description = "업데이트된 상태", example = "APPROVED")
        GroupUser.RequestStatus updatedStatus
) {
    public static ApproveOrRejectResponse of(Long groupId, Long userId, GroupUser.RequestStatus updatedStatus) {
        return new ApproveOrRejectResponse(groupId, userId, updatedStatus);
    }
}
