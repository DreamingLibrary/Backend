package opensource.DreamingLibrary.group.repository;

import opensource.DreamingLibrary.group.entity.GroupJoinRequest;
import opensource.DreamingLibrary.group.entity.GroupJoinRequest.RequestStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupJoinRequestRepository extends JpaRepository<GroupJoinRequest, Long> {

    boolean existsByGroupIdAndUserIdAndStatus(Long groupId, Long userId, RequestStatus status);

    List<GroupJoinRequest> findAllByGroupId(Long groupId);
}
