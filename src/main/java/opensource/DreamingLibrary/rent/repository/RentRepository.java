package opensource.DreamingLibrary.rent.repository;

import opensource.DreamingLibrary.rent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findAllByUser_IdAndGroup_GroupId(Long userId, Long groupId);
}