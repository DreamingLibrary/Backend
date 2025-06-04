package opensource.DreamingLibrary.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.entity.GroupJoinRequest.RequestStatus;

import java.time.LocalDateTime;

/*
1.


 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupResponse {

    private Long groupId;
    private String name;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private RequestStatus status;

    public GroupResponse(Group group) {
        this.groupId = group.getGroupId();
        this.name = group.getGroupName();
    }

    public GroupResponse(Group group, RequestStatus status) {
        this.groupId = group.getGroupId();
        this.name = group.getGroupName();
        this.status = status;
    }

    @Builder
    public GroupResponse(Long groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public static class GroupResponseBuilder {
        private Long groupId;
        private String name;

        public GroupResponseBuilder groupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }

        public GroupResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GroupResponseBuilder groupName(String groupName) {
            this.name = groupName;
            return this;
        }

        public GroupResponse build() {
            return new GroupResponse(groupId, name);
        }
    }
}