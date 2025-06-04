package opensource.DreamingLibrary.group.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import opensource.DreamingLibrary.group.dto.request.GroupCreateRequest;
import opensource.DreamingLibrary.group.dto.response.GroupResponse;
import opensource.DreamingLibrary.group.service.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public SuccessResponse<SingleResult<GroupResponse>> createGroup(@RequestBody GroupCreateRequest request) {
        GroupResponse groupResponse = groupService.createGroup(request);

        SingleResult<GroupResponse> singleResult = new SingleResult<>();
        singleResult.setData(groupResponse);

        return SuccessResponse.ok(singleResult);
    }

    @GetMapping("/{groupId}")
    public SuccessResponse<SingleResult<GroupResponse>> getGroup(@PathVariable Long groupId) {
        GroupResponse groupResponse = groupService.getGroup(groupId);

        SingleResult<GroupResponse> singleResult = new SingleResult<>();
        singleResult.setData(groupResponse);

        return SuccessResponse.ok(singleResult);
    }

    @PutMapping("/{groupId}")
    public SuccessResponse<SingleResult<GroupResponse>> updateGroup(@PathVariable Long groupId, @RequestBody GroupCreateRequest request) {
        GroupResponse groupResponse = groupService.updateGroup(groupId, request);

        SingleResult<GroupResponse> singleResult = new SingleResult<>();
        singleResult.setData(groupResponse);

        return SuccessResponse.ok(singleResult);
    }

    @DeleteMapping("/{groupId}")
    public SuccessResponse<Void> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return SuccessResponse.ok(null);
    }

    @GetMapping
    public SuccessResponse<List<GroupResponse>> listGroups() {
        List<GroupResponse> groupResponses = groupService.listGroupsWithStatus();
        return SuccessResponse.ok(groupResponses);
    }

}
