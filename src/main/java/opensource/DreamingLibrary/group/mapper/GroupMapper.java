package opensource.DreamingLibrary.group.mapper;

import opensource.DreamingLibrary.group.dto.request.GroupCreateRequest;
import opensource.DreamingLibrary.group.entity.Group;

public class GroupMapper {

    // DTO -> 엔티티 변환 (static 메서드)
    public static Group from(GroupCreateRequest request) {
        return Group.builder()
                .groupName(request.groupName())
                .build();
    }

}