package opensource.DreamingLibrary.group.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.entity.GroupJoinRequest;
import opensource.DreamingLibrary.group.entity.GroupUser;
import opensource.DreamingLibrary.group.repository.GroupJoinRequestRepository;
import opensource.DreamingLibrary.group.repository.GroupRepository;
import opensource.DreamingLibrary.group.repository.GroupUserRepository;
import opensource.DreamingLibrary.user.entity.User;
import opensource.DreamingLibrary.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupUserService {

    private final GroupUserRepository groupUserRepository;
    private final GroupJoinRequestRepository groupJoinRequestRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Transactional
    public void requestJoinGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        GroupUser groupUser = GroupUser.builder()
                .group(group)
                .user(user)
                .build();

        groupUserRepository.save(groupUser);
    }

    @Transactional(readOnly = true)
    public boolean isUserInGroup(Long groupId, Long userId) {
        return groupUserRepository.existsByGroupGroupIdAndUserId(groupId, userId);
    }

    @Transactional
    public void setGroupAdmin(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        group.setAdmin(user);
        groupRepository.save(group);
    }

    @Transactional
    public void deleteGroup(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));
        groupRepository.delete(group);
    }

    @Transactional
    public void updateGroup(Long groupId, String newName) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));
        group.setGroupName(newName);
        groupRepository.save(group);
    }

    @Transactional
    public void removeMember(Long groupId, Long userId) {
        GroupUser groupUser = groupUserRepository.findByGroupGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다."));
        groupUserRepository.delete(groupUser);
    }

    @Transactional(readOnly = true)
    public List<GroupUser> listMembers(Long groupId) {
        return groupUserRepository.findAllByGroupGroupId(groupId);
    }

    @Transactional(readOnly = true)
    public List<GroupJoinRequest> listJoinRequests(Long groupId) {
        return groupJoinRequestRepository.findAllByGroupGroupId(groupId);
    }
}
