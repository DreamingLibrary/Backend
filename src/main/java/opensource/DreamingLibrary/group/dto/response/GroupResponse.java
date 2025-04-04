package opensource.DreamingLibrary.group.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

/*
1.


 */


@Builder
public record GroupResponse(
        Long groupId,
        String groupName,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
}