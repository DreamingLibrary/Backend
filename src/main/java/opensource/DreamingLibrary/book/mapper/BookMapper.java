package opensource.DreamingLibrary.book.mapper;
import opensource.DreamingLibrary.book.dto.request.BookCreateRequest;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.group.entity.Group;

public class BookMapper {

    // DTO → 엔티티 변환
    public static Book from(BookCreateRequest request, Group group) {
        return Book.builder()
                // group은 Service에서 주입할 수도 있음
                .title(request.title())
                .author(request.author())
                .description(request.description())
                .category(request.category())
                .group(group)
                .build();
    }

    // 필요시 엔티티 → DTO 변환용 메서드도 추가 가능
    // ex) public static BookResponse toResponse(Book book) { ... }
}
