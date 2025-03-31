package opensource.DreamingLibrary.rent.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RentCreateRequest(
        @NotNull
        @Schema(description = "대여자(User) PK")
        Long userId,

        @NotNull
        @Schema(description = "그룹(Group) PK")
        Long groupId,

        @NotNull
        @Schema(description = "도서(Book) PK")
        Long bookId,

        @NotNull
        @Schema(description = "대여 기간 또는 대여 시작 시각(예: 2025-03-30T10:30:00)")
        LocalDateTime rentalPeriod,

        @Schema(description = "반납 시각(예: 2025-04-05T18:00:00)")
        LocalDateTime returnAt,

        @Schema(description = "연장 횟수(기본 0)")
        Integer rentalCount,

        @Schema(description = "생성 시각(서버에서 기본값 사용)")
        LocalDateTime createdAt,

        @Schema(description = "수정 시각(서버에서 기본값 사용)")
        LocalDateTime updatedAt
) {
}
