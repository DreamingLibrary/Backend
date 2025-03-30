package opensource.DreamingLibrary.example.book.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookCreateRequest(

        @NotBlank
        @Schema(example = "도서 제목")
        String title,

        @NotBlank
        @Schema(example = "저자 이름")
        String author,

        @Schema(example = "이 책에 대한 설명")
        String description,

        @NotBlank
        @Schema(example = "소설, 인문, 자기계발 등")
        String category,

        @Schema(example = "2025-03-30T10:30:00")
        LocalDateTime createdAt,

        @Schema(example = "2025-03-30T10:30:00")
        LocalDateTime updatedAt,

        @Schema(example = "1", description = "연결될 Group ID")
        Long groupId
) {
}
