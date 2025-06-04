package opensource.DreamingLibrary.group.repository;

import opensource.DreamingLibrary.group.entity.GroupUser;
import opensource.DreamingLibrary.group.entity.GroupUser.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

    boolean existsByGroupGroupIdAndUserId(Long groupId, Long userId);

    List<GroupUser> findAllByGroupGroupId(Long groupId);

    List<GroupUser> findAllByGroupGroupIdAndStatus(Long groupId, RequestStatus status);

    Optional<GroupUser> findByGroupGroupIdAndUserId(Long groupId, Long userId);

    @Query("SELECT u.status FROM GroupUser u WHERE u.group.groupId = :groupId AND u.user.name = :username")
    GroupUser.RequestStatus findStatusByGroupIdAndUsername(@Param("groupId") Long groupId, @Param("username") String username);

    @Query("SELECT u.status FROM GroupUser u WHERE u.group.groupId = :groupId AND u.user.name = :username")
    GroupUser.RequestStatus findStatusByGroupGroupIdAndUsername(@Param("groupId") Long groupId, @Param("username") String username);
}
