package opensource.DreamingLibrary.group.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import opensource.DreamingLibrary.group.entity.GroupUser;

public record ApproveOrRejectRequest(
        @NotNull
        @Schema(description = "그룹 ID", example = "1")
        Long groupId,

        @NotNull
        @Schema(description = "유저 ID", example = "2")
        Long userId,

        @NotNull
        @Schema(description = "요청 상태", example = "APPROVED")
        GroupUser.RequestStatus status
) {
}
