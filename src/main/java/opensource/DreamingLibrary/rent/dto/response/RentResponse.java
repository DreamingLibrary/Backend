package opensource.DreamingLibrary.rent.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RentResponse(
        @Schema(description = "rent PK")
        Long rentId,

        @Schema(description = "대여자(User) PK")
        Long userId,

        @Schema(description = "그룹(Group) PK")
        Long groupId,

        @Schema(description = "도서(Book) PK")
        Long bookId,

        @Schema(description = "대여기간/시작시각")
        LocalDateTime rentalPeriod,

        @Schema(description = "반납시각")
        LocalDateTime returnAt,

        @Schema(description = "연장횟수")
        Integer rentalCount,

        @Schema(description = "생성 시각")
        LocalDateTime createdAt,

        @Schema(description = "수정 시각")
        LocalDateTime updatedAt
) {
}
