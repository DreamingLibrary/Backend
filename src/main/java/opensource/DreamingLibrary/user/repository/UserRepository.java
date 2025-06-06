package opensource.DreamingLibrary.user.repository;

import opensource.DreamingLibrary.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByStudentNumber(String studentNumber);
    Optional<User> findByName(String name);
    User findByStudentNumber(String studentNumber);
}
