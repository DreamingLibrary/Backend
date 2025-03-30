package opensource.DreamingLibrary.example.book.dto.response;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookResponse(
        Long bookId,
        String title,
        String author,
        String description,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long groupId
) {
}