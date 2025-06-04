package opensource.DreamingLibrary.group.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GroupCreateRequest(
        @NotBlank
        @Schema(description = "그룹 이름", example = "도서동아리")
        String groupName
) {
    public String getName() {
        return groupName;
    }
}