package opensource.DreamingLibrary.group.repository;

import opensource.DreamingLibrary.group.entity.GroupJoinRequest;
import opensource.DreamingLibrary.group.entity.GroupJoinRequest.RequestStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupJoinRequestRepository extends JpaRepository<GroupJoinRequest, Long> {

    boolean existsByGroupIdAndUserIdAndStatus(Long groupId, Long userId, RequestStatus status);

    List<GroupJoinRequest> findAllByGroupId(Long groupId);

    @Query("SELECT r.status FROM GroupJoinRequest r WHERE r.group.groupId = :groupId AND r.user.username = :username")
    RequestStatus findStatusByGroupIdAndUsername(@Param("groupId") Long groupId, @Param("username") String username);
}
