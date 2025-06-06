package opensource.DreamingLibrary.group.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.entity.GroupUser.RequestStatus;

public record GroupResponse(
        @NotNull
        @Schema(description = "그룹 id", example = "1")
        Long groupId,

        @NotNull
        @Schema(description = "그룹 이름", example = "개발자 모임")
        String name
) {
    public static GroupResponse of(Long groupId, String name) {
        return new GroupResponse(groupId, name);
    }

    public static GroupResponse of(Group group) {
        return new GroupResponse(
                group.getGroupId(),
                group.getGroupName()
        );
    }

    public static GroupResponse of(Group group, RequestStatus status) {
        return new GroupResponse(
                group.getGroupId(),
                group.getGroupName()
        );
    }
}
