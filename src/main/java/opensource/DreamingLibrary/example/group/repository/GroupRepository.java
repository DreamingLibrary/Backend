package opensource.DreamingLibrary.example.group.repository;

import opensource.DreamingLibrary.example.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {

}