package opensource.DreamingLibrary.example.book.service;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.example.book.dto.request.BookCreateRequest;
import opensource.DreamingLibrary.example.book.entity.Book;
import opensource.DreamingLibrary.example.book.mapper.BookMapper;
import opensource.DreamingLibrary.example.book.repository.BookRepository;
import opensource.DreamingLibrary.example.group.entity.Group;
import opensource.DreamingLibrary.example.group.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final GroupRepository groupRepository;

    /**
     * CREATE
     * - 만약 createdAt / updatedAt이 null이면 현재 시간(LocalDateTime.now())로 대체
     */
    public Book createBook(BookCreateRequest request) {
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

        return bookRepository.save(book);
    }

    /**
     * READ (단일 조회)
     */
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElse(null);
    }

}
