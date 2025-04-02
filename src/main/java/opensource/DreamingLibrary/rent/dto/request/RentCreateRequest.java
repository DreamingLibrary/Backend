package opensource.DreamingLibrary.rent.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RentCreateRequest(
        @NotNull
        @Schema(description = "대여자(User) PK", example = "정현정")
        Long userId,

        @NotNull
        @Schema(description = "그룹(Group) PK", example= "CNU")
        Long groupId,

        @NotNull
        @Schema(description = "도서(Book) PK", example = "열혈 c++ 프로그래밍")
        Long bookId

) {
}
