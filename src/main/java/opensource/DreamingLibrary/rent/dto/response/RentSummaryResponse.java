package opensource.DreamingLibrary.rent.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import opensource.DreamingLibrary.rent.entity.Rent;

import java.time.LocalDateTime;

@Builder
public record RentSummaryResponse(
        @Schema(description = "rent PK", example = "1")
        Long rentId,

        @Schema(description = "대여자 이름", example = "송은수")
        String userName,

        @Schema(description = "도서 제목", example = "누가 내 머리위에 똥 쌌어")
        String bookTitle,

        @Schema(description = "대여 일자(기간)", example = "14")
        Integer rentalStartAt,

        @Schema(description = "대여 생성 시각", example = "2025-02-01T18:00:00")
        LocalDateTime createdAt,

        @Schema(description = "반납 시각", example = "2025-02-15T18:00:00")
        LocalDateTime returnAt,

        @Schema(description = "연체 여부", example = "false")
        Boolean isOverdue
) {

        public static RentSummaryResponse of(Rent rent) {
                return RentSummaryResponse.builder()
                        .rentId(rent.getRentId())
                        .userName(rent.getUser().getName())
                        .bookTitle(rent.getBook().getTitle())
                        .rentalStartAt(rent.getRentalPeriod())
                        .createdAt(rent.getCreatedAt())
                        .returnAt(rent.getReturnAt())
                        .isOverdue(LocalDateTime.now().isAfter(rent.getReturnAt()))
                        .build();
        }
}
