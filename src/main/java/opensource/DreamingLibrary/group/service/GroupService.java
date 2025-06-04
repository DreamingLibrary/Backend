package opensource.DreamingLibrary.group.service;


import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.group.dto.request.GroupCreateRequest;
import opensource.DreamingLibrary.group.dto.response.GroupResponse;
import opensource.DreamingLibrary.group.dto.response.GroupStatusResponse;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.entity.GroupUser;
import opensource.DreamingLibrary.group.repository.GroupRepository;
import opensource.DreamingLibrary.group.repository.GroupUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupUserRepository groupUserRepository;

    // CREATE 예시
    public GroupResponse createGroup(GroupCreateRequest requestDto) {
        Group group = Group.builder()
                .groupName(requestDto.getGroupName()).build();

        Group saved = groupRepository.save(group);
        return GroupResponse.of(saved.getGroupId(), saved.getGroupName());
    }

    // READ 예시
    public GroupResponse getGroup(Long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        return optionalGroup
                .map(this::toResponse)
                .orElse(null); // 실제로는 예외처리
    }

    @Transactional
    public GroupResponse updateGroup(Long groupId, GroupCreateRequest request) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));

        group.setGroupName(request.getName());
        groupRepository.save(group);

        return GroupResponse.of(group);
    }

    @Transactional
    public void deleteGroup(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));

        groupRepository.delete(group);
    }

    @Transactional(readOnly = true)
    public List<GroupResponse> listGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map(GroupResponse::of).toList();
    }

    @Transactional(readOnly = true)
    public List<GroupStatusResponse> listGroupsWithStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        String currentUsername = authentication.getName();

        List<Group> groups = groupRepository.findAll();
        return groups.stream().map(group -> {
            GroupUser.RequestStatus status = groupUserRepository.findStatusByGroupGroupIdAndUsername(group.getGroupId(), currentUsername);
            return GroupStatusResponse.of(group, status);
        }).toList();
    }

    private GroupResponse toResponse(Group group) {
        return GroupResponse.of(group);
    }
}