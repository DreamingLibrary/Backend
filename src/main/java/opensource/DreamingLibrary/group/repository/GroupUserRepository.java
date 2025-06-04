package opensource.DreamingLibrary.group.repository;

import opensource.DreamingLibrary.group.entity.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

    boolean existsByGroupIdAndUserId(Long groupId, Long userId);

    List<GroupUser> findAllByGroupId(Long groupId);

    Optional<GroupUser> findByGroupIdAndUserId(Long groupId, Long userId);
}
