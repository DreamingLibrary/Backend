package opensource.DreamingLibrary.book.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.book.entity.Category;

import java.time.LocalDateTime;

@Builder
public record BookResponse(
        @NotNull
        @Schema(description = "책 id", example = "1")
        Long bookId,

        @NotNull
        @Schema(description = "제목", example = "제목입니다")
        String title,

        @NotNull
        @Schema(description = "저자명", example = "김도형")
        String author,

        @NotNull
        @Schema(description = "책 설명", example = "설명입니다")
        String description,

        @NotNull
        @Schema(description = "책 카테고리", example = "WEB_DEVELOPMENT")
        Category category,

        @NotNull
        @Schema(description = "그룹 id", example = "1")
        Long groupId
) {
    public static BookResponse of(Book book){
        return BookResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .category(book.getCategory())
                .groupId(book.getGroup().getGroupId())
                .build();
    }
}