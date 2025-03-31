package opensource.DreamingLibrary.example.group.service;


import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.example.group.dto.request.GroupCreateRequest;
import opensource.DreamingLibrary.example.group.dto.response.GroupResponse;
import opensource.DreamingLibrary.example.group.entity.Group;
import opensource.DreamingLibrary.example.group.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    // CREATE 예시
    public GroupResponse createGroup(GroupCreateRequest requestDto) {
        Group group = Group.builder()
                .groupName(requestDto.groupName())
                .createdTime(requestDto.createdTime() != null ? requestDto.createdTime() : LocalDateTime.now())
                .updatedTime(requestDto.updatedTime() != null ? requestDto.updatedTime() : LocalDateTime.now())
                .build();

        Group saved = groupRepository.save(group);
        return toResponse(saved);
    }

    // READ 예시
    public GroupResponse getGroup(Long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        return optionalGroup
                .map(this::toResponse)
                .orElse(null); // 실제로는 예외처리
    }

    private GroupResponse toResponse(Group group) {
        return GroupResponse.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .createdTime(group.getCreatedTime())
                .updatedTime(group.getUpdatedTime())
                .build();
    }
}