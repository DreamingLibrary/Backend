package opensource.DreamingLibrary.example.repository;

import opensource.DreamingLibrary.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByLoginId(String loginId);
}
