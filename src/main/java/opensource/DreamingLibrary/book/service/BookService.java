package opensource.DreamingLibrary.book.service;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.book.dto.request.BookCreateRequest;
import opensource.DreamingLibrary.book.dto.response.BookResponse;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.book.mapper.BookMapper;
import opensource.DreamingLibrary.book.repository.BookRepository;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final GroupRepository groupRepository;

    /**
     * CREATE
     * - 만약 createdAt / updatedAt이 null이면 현재 시간(LocalDateTime.now())로 대체
     */
    public BookResponse createBook(BookCreateRequest request) {
        // DTO → 엔티티
        Book book = BookMapper.from(request);

        // createdAt / updatedAt이 null이면 서버 시간으로 설정
        if (book.getCreatedAt() == null) {
            book.setCreatedAt(LocalDateTime.now());
        }
        if (book.getUpdatedAt() == null) {
            book.setUpdatedAt(LocalDateTime.now());
        }

        // groupId가 있다면 실제 Group 엔티티를 조회 후 주입
        if (request.groupId() != null) {
            Group group = groupRepository.findById(request.groupId())
                    .orElseThrow(() -> new IllegalArgumentException("Group not found. ID=" + request.groupId()));
            book.setGroup(group);
        }

        // DB 저장
        Book savedBook = bookRepository.save(book);

        // **엔티티 → DTO 변환** 후 반환
        return toResponse(savedBook);
    }

    /**
     * READ (단일 조회)
     */
    public BookResponse getBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found. ID=" + bookId));

        // 엔티티 → DTO 변환
        return toResponse(book);
    }

    /**
     * 엔티티를 DTO로 변환하는 내부 헬퍼 메서드
     */
    private BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .category(book.getCategory())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .groupId(
                        (book.getGroup() != null)
                                ? book.getGroup().getGroupId()
                                : null
                )
                .build();
    }
}
