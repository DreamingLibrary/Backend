package opensource.DreamingLibrary.group.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.entity.GroupUser.RequestStatus;


public record GroupStatusResponse(
        @NotNull
        @Schema(description = "그룹 id", example = "1")
        Long groupId,

        @NotNull
        @Schema(description = "그룹 이름", example = "개발자 모임")
        String name,

        @Schema(description = "요청 상태", example = "PENDING")
        RequestStatus status
) {
    public static GroupStatusResponse of(Group group) {
        return new GroupStatusResponse(
                group.getGroupId(),
                group.getGroupName(),
                null
        );
    }

    public static GroupStatusResponse of(Group group, RequestStatus status) {
        return new GroupStatusResponse(
                group.getGroupId(),
                group.getGroupName(),
                status
        );
    }

}