package opensource.DreamingLibrary.example.rent.repository;

import opensource.DreamingLibrary.example.rent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}