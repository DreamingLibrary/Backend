package opensource.DreamingLibrary.rent.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import opensource.DreamingLibrary.rent.entity.Rent;

import java.time.LocalDateTime;

@Builder
public record RentResponse(
        @Schema(description = "rent PK", example = "1")
        Long rentId,

        @Schema(description = "대여자(User) PK", example = "1")
        Long userId,

        @Schema(description = "그룹(Group) PK", example = "1")
        Long groupId,

        @Schema(description = "도서(Book) PK", example = "1")
        Long bookId,

        @Schema(description = "대여자 이름", example = "정현정")
        String userName,

        @Schema(description = "그룹명", example = "CNU")
        String groupName,

        @Schema(description = "도서명", example = "열혈 c++ 프로그래밍")
        String bookTitle,

        @Schema(description = "대여 기간", example = "14")
        Integer rentalPeriod,

        @Schema(description = "연장 횟수", example = "0")
        Integer rentalCount,

        @Schema(description = "반납 시각", example ="2025-02-01T18:00:00")
        LocalDateTime returnAt,

        @Schema(description = "생성 시각", example = "2025-02-15T18:00:00")
        LocalDateTime createdAt,

        @Schema(description = "연체 여부", example = "false")
        Boolean isOverdue
) {

        public static RentResponse of(Rent rent) {
                return RentResponse.builder()
                        .rentId(rent.getRentId())
                        .userId(rent.getUser().getId())
                        .groupId(rent.getGroup().getGroupId())
                        .bookId(rent.getBook().getBookId())
                        .userName(rent.getUser().getName())
                        .groupName(rent.getGroup().getGroupName())
                        .bookTitle(rent.getBook().getTitle())
                        .rentalPeriod(rent.getRentalPeriod())
                        .rentalCount(rent.getRentalCount())
                        .returnAt(rent.getReturnAt())
                        .createdAt(rent.getCreatedAt())
                        .isOverdue( LocalDateTime.now().isAfter(rent.getReturnAt()))
                        .build();
        }
}
