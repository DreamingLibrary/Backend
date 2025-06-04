package opensource.DreamingLibrary.group.controller;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.group.service.GroupUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import opensource.DreamingLibrary.group.entity.GroupUser;
import opensource.DreamingLibrary.group.entity.GroupJoinRequest;

import java.util.List;

@RestController
@RequestMapping("/group-user")
@RequiredArgsConstructor
public class GroupUserController {

    private final GroupUserService groupUserService;

    @PostMapping("/request-join")
    public ResponseEntity<?> requestJoinGroup(@RequestParam Long groupId, @RequestParam Long userId) {
        groupUserService.requestJoinGroup(groupId, userId);
        return ResponseEntity.ok("그룹 입장 신청이 완료되었습니다.");
    }

    @GetMapping("/is-in-group")
    public ResponseEntity<?> isUserInGroup(@RequestParam Long groupId, @RequestParam Long userId) {
        boolean isInGroup = groupUserService.isUserInGroup(groupId, userId);
        return ResponseEntity.ok(isInGroup);
    }

    @PostMapping("/set-admin")
    public ResponseEntity<?> setGroupAdmin(@RequestParam Long groupId, @RequestParam Long userId) {
        groupUserService.setGroupAdmin(groupId, userId);
        return ResponseEntity.ok("그룹 관리자가 성공적으로 지정되었습니다.");
    }

    @DeleteMapping("/delete-group")
    public ResponseEntity<?> deleteGroup(@RequestParam Long groupId) {
        groupUserService.deleteGroup(groupId);
        return ResponseEntity.ok("그룹이 삭제되었습니다.");
    }

    @PutMapping("/update-group")
    public ResponseEntity<?> updateGroup(@RequestParam Long groupId, @RequestParam String newName) {
        groupUserService.updateGroup(groupId, newName);
        return ResponseEntity.ok("그룹 정보가 수정되었습니다.");
    }

    @DeleteMapping("/remove-member")
    public ResponseEntity<?> removeMember(@RequestParam Long groupId, @RequestParam Long userId) {
        groupUserService.removeMember(groupId, userId);
        return ResponseEntity.ok("멤버가 그룹에서 제거되었습니다.");
    }

    @GetMapping("/list-members")
    public ResponseEntity<List<GroupUser>> listMembers(@RequestParam Long groupId) {
        List<GroupUser> members = groupUserService.listMembers(groupId);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/list-join-requests")
    public ResponseEntity<List<GroupJoinRequest>> listJoinRequests(@RequestParam Long groupId) {
        List<GroupJoinRequest> joinRequests = groupUserService.listJoinRequests(groupId);
        return ResponseEntity.ok(joinRequests);
    }
}
