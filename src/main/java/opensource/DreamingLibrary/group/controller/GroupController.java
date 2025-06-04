package opensource.DreamingLibrary.group.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import opensource.DreamingLibrary.group.dto.request.GroupCreateRequest;
import opensource.DreamingLibrary.group.dto.response.GroupResponse;
import opensource.DreamingLibrary.group.dto.response.GroupStatusResponse;
import opensource.DreamingLibrary.group.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import opensource.DreamingLibrary.global.dto.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody GroupCreateRequest request) {
        try {
            GroupResponse groupResponse = groupService.createGroup(request);

            SingleResult<GroupResponse> singleResult = new SingleResult<>();
            singleResult.setData(groupResponse);

            return ResponseEntity.ok(SuccessResponse.ok(singleResult));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponse.of(500, "중복된 그룹 이름이 존재합니다.", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 생성 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getGroup(@PathVariable Long groupId) {
        try {
            GroupResponse groupResponse = groupService.getGroup(groupId);

            if (groupResponse == null) {
                return ResponseEntity.badRequest().body(ErrorResponse.of(500, "그룹을 찾을 수 없습니다."));
            }

            SingleResult<GroupResponse> singleResult = new SingleResult<>();
            singleResult.setData(groupResponse);

            return ResponseEntity.ok(SuccessResponse.ok(singleResult));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 조회 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> updateGroup(@PathVariable Long groupId, @RequestBody GroupCreateRequest request) {
        try {
            GroupResponse groupResponse = groupService.updateGroup(groupId, request);

            SingleResult<GroupResponse> singleResult = new SingleResult<>();
            singleResult.setData(groupResponse);

            return ResponseEntity.ok(SuccessResponse.ok(singleResult));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 수정 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long groupId) {
        try {
            groupService.deleteGroup(groupId);
            return ResponseEntity.ok(SuccessResponse.ok(null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 삭제 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> listGroups() {
        try {
            List<GroupStatusResponse> groupStatusResponses = groupService.listGroupsWithStatus();
            return ResponseEntity.ok(SuccessResponse.ok(groupStatusResponses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 목록 조회 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

}
