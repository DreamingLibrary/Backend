package opensource.DreamingLibrary.group.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.entity.GroupUser;
import opensource.DreamingLibrary.group.entity.GroupUser.RequestStatus;
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
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Transactional
    public void setGroupAdmin(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        group.setAdmin(user);
        groupRepository.save(group);
    }
    
    @Transactional
    public GroupUser requestJoinGroup(Long groupId, String username) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다. ID: " + groupId));
        User user = userRepository.findByStudentNumber(username);

        boolean alreadyExists = groupUserRepository.existsByGroupGroupIdAndUserId(groupId, user.getId());
        if (alreadyExists) {
            throw new IllegalStateException("이미 그룹에 입장 신청이 되어 있습니다.");
        }

        GroupUser groupUser = GroupUser.builder()
                .group(group)
                .user(user)
                .status(GroupUser.RequestStatus.PENDING) // 기본 상태를 PENDING으로 설정
                .build();

        return groupUserRepository.save(groupUser);
    }

    @Transactional(readOnly = true)
    public boolean isUserInGroup(Long groupId, String username) {
        User user = userRepository.findByStudentNumber(username);
        return groupUserRepository.existsByGroupGroupIdAndUserId(groupId, user.getId());
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
    public void removeMember(Long groupId, String username) {
        User user = userRepository.findByStudentNumber(username);
        GroupUser groupUser = groupUserRepository.findByGroupGroupIdAndUserId(groupId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다."));
        groupUserRepository.delete(groupUser);
    }

    @Transactional(readOnly = true)
    public List<GroupUser> listMembers(Long groupId, RequestStatus status) {
        if (status == null) {
            return groupUserRepository.findAllByGroupGroupId(groupId);
        }
        return groupUserRepository.findAllByGroupGroupIdAndStatus(groupId, status);
    }

    @Transactional(readOnly = true)
    public List<GroupUser> listJoinRequests(Long groupId, GroupUser.RequestStatus status) {
        if (status == null) {
            return groupUserRepository.findAllByGroupGroupId(groupId);
        }
        return groupUserRepository.findAllByGroupGroupIdAndStatus(groupId, status);
    }

    @Transactional(readOnly = true)
    public GroupUser.RequestStatus getUserGroupStatus(Long groupId, String studentNumber) {
        GroupUser groupUser = groupUserRepository.findByGroupGroupIdAndUserStudentNumber(groupId, studentNumber)
                .orElseThrow(() -> new IllegalArgumentException("그룹 또는 유저를 찾을 수 없습니다."));
        return groupUser.getStatus();
    }

    @Transactional(readOnly = true)
    public List<GroupUser> listApprovedMembers(Long groupId) {
        return groupUserRepository.findAllByGroupGroupIdAndStatus(groupId, GroupUser.RequestStatus.APPROVED);
    }

    public void checkIfAdmin(Long groupId, String username) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다. ID: " + groupId));
        if (group.getAdmin() == null) {
            throw new IllegalStateException("그룹에 관리자가 설정되어 있지 않습니다.");
        }
        if (!group.getAdmin().getStudentNumber().equals(username)) {
            throw new IllegalStateException("관리자만 가능합니다.");
        }
    }

    public GroupUser.RequestStatus updateJoinRequestStatus(Long groupId, Long userId, GroupUser.RequestStatus status) {
        GroupUser groupUser = groupUserRepository.findByGroupGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new IllegalArgumentException("입장 신청을 찾을 수 없습니다."));
        groupUser.setStatus(status);
        groupUserRepository.save(groupUser);
        return groupUser.getStatus();
    }

}
