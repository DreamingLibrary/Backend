package opensource.DreamingLibrary.book.repository;

import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.book.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 기본 CRUD 메서드 제공
    
    //카테고리별 조회
    List<Book> findByCategory(Category category);
}