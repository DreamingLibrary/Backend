package opensource.DreamingLibrary.book.repository;

import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.book.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 기본 CRUD 메서드 제공
    
    //카테고리별 조회
    List<Book> findByCategory(Category category);

    //제목 또는 저자명으로 조회
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String titleKeyword, String authorKeyword);

    //대여 가능한 책만 조회
    @Query("SELECT b FROM Book b WHERE b.bookId NOT IN (SELECT r.book.bookId FROM Rent r)")
    List<Book> findAllAvailableBooks();

}