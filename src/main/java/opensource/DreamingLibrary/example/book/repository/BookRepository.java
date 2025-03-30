package opensource.DreamingLibrary.example.book.repository;

import opensource.DreamingLibrary.example.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 기본 CRUD 메서드 제공
}