package opensource.DreamingLibrary.book.service;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.book.dto.request.BookCreateRequest;
import opensource.DreamingLibrary.book.dto.response.BookResponse;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.book.entity.Category;
import opensource.DreamingLibrary.book.mapper.BookMapper;
import opensource.DreamingLibrary.book.repository.BookRepository;
import opensource.DreamingLibrary.global.dto.response.result.ListResult;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import opensource.DreamingLibrary.global.exception.CustomException;
import opensource.DreamingLibrary.global.exception.ErrorCode;
import opensource.DreamingLibrary.global.service.ResponseService;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final GroupRepository groupRepository;

    /**
     * 책 등록
     */
    public SingleResult<Long> createBook(BookCreateRequest request) {
        
        // groupId가 있다면 실제 Group 엔티티를 조회 후 주입
        Group group = null;
        if (request.groupId() != null) {
            group = groupRepository.findById(request.groupId())
                    .orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_EXIST));
        }
        
        //DB 저장
        Book newBook = BookMapper.from(request, group);
        newBook = bookRepository.save(newBook);


       return ResponseService.getSingleResult(newBook.getBookId());
    }

    /**
     * 단일 조회
     */
    public SingleResult<BookResponse> getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_EXIST));

        return ResponseService.getSingleResult(BookResponse.of(book));
    }

    /**
     * 책 전체 조회
     * - 대여 가능한 책 목록만 반환
     */
    public ListResult<BookResponse> getAllBooks(){
        List<Book> books = bookRepository.findAllAvailableBooks();
        List<BookResponse> responseList = books.stream()
                .map(BookResponse::of)
                .toList();

        return ResponseService.getListResult(responseList);
    }


    /**
     * 카테고리별 조회
     */
    public ListResult<BookResponse> getBooksByCategory(Category category){
        List<Book> books = bookRepository.findByCategory(category);
        List<BookResponse> responseList = books.stream()
                .map(BookResponse::of)
                .toList();

        return ResponseService.getListResult(responseList);
    }


    /**
     * 제목 또는 저자명으로 검색
     */
    public ListResult<BookResponse> searchBooksByKeyword(String keyword){
        List<Book> books = bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);

        List<BookResponse> responseList = books.stream()
                .map(BookResponse::of)
                .toList();

        return ResponseService.getListResult(responseList);
    }
}
