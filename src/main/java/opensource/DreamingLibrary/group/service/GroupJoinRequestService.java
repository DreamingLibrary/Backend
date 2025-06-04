package opensource.DreamingLibrary.group.service;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.entity.GroupJoinRequest;
import opensource.DreamingLibrary.group.entity.GroupJoinRequest.RequestStatus;
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
public class GroupJoinRequestService {

    private final GroupJoinRequestRepository groupJoinRequestRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupUserRepository groupUserRepository;

    @Transactional
    public void createJoinRequest(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        GroupJoinRequest joinRequest = GroupJoinRequest.builder()
                .group(group)
                .user(user)
                .status(RequestStatus.PENDING)
                .build();

        groupJoinRequestRepository.save(joinRequest);
    }

    @Transactional(readOnly = true)
    public boolean isJoinRequestPending(Long groupId, Long userId) {
        return groupJoinRequestRepository.existsByGroupGroupIdAndUserIdAndStatus(groupId, userId, RequestStatus.PENDING);
    }

    @Transactional
    public void handleJoinRequest(Long requestId, boolean approve) {
        GroupJoinRequest joinRequest = groupJoinRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("입장 신청을 찾을 수 없습니다."));

        if (approve) {
            joinRequest.setStatus(RequestStatus.APPROVED);
            GroupUser groupUser = GroupUser.builder()
                    .group(joinRequest.getGroup())
                    .user(joinRequest.getUser())
                    .build();
            groupUserRepository.save(groupUser);
        } else {
            joinRequest.setStatus(RequestStatus.REJECTED);
        }

        groupJoinRequestRepository.save(joinRequest);
    }
}
