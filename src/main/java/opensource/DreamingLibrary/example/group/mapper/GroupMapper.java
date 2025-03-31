package opensource.DreamingLibrary.example.group.mapper;

import opensource.DreamingLibrary.example.group.dto.request.GroupCreateRequest;
import opensource.DreamingLibrary.example.group.entity.Group;

public class GroupMapper {

    // DTO -> 엔티티 변환 (static 메서드)
    public static Group from(GroupCreateRequest request) {
        return Group.builder()
                .groupName(request.groupName())
                .createdTime(request.createdTime())
                .updatedTime(request.updatedTime())
                .build();
    }

}