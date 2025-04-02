package opensource.DreamingLibrary.book.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import opensource.DreamingLibrary.book.entity.Category;

import java.time.LocalDateTime;

@Builder
public record BookCreateRequest(

        @NotBlank
        @Schema(description = "도서명", example = "제목입니다")
        String title,

        @NotBlank
        @Schema(description = "저자명", example = "김도형")
        String author,

        @Schema(description = "책에 대한 설명", example = "설명입니다")
        String description,

        @NotBlank
        @Schema(description = "도서 카테고리", example = "WEB_DEVELOPMENT")
        Category category,

        @Schema(example = "1", description = "연결될 Group ID")
        Long groupId
) {
}
