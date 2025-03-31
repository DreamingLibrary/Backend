package opensource.DreamingLibrary.example.group.controller;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.example.group.dto.request.GroupCreateRequest;
import opensource.DreamingLibrary.example.group.dto.response.GroupResponse;
import opensource.DreamingLibrary.example.group.service.GroupService;
import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
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

    // 추후 update, delete, list 조회 등 추가 가능
}
