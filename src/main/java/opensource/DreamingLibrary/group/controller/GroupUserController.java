package opensource.DreamingLibrary.group.controller;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.group.service.GroupUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import opensource.DreamingLibrary.group.entity.GroupUser;
import opensource.DreamingLibrary.group.entity.GroupUser.RequestStatus;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/api/group-user")
@RequiredArgsConstructor
public class GroupUserController {

    private final GroupUserService groupUserService;

    @PostMapping("/request-join")
    public ResponseEntity<?> requestJoinGroup(@RequestParam Long groupId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        groupUserService.requestJoinGroup(groupId, currentUsername);
        return ResponseEntity.ok("그룹 입장 신청이 완료되었습니다.");
    }

    @GetMapping("/is-in-group")
    public ResponseEntity<?> isUserInGroup(@RequestParam Long groupId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        boolean isInGroup = groupUserService.isUserInGroup(groupId, currentUsername);
        return ResponseEntity.ok(isInGroup);
    }

    @PostMapping("/set-admin")
    public ResponseEntity<?> setGroupAdmin(@RequestParam Long groupId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        groupUserService.setGroupAdmin(groupId, currentUsername);
        return ResponseEntity.ok("그룹 관리자가 성공적으로 지정되었습니다.");
    }

    @GetMapping("/list-members")
    public ResponseEntity<List<GroupUser>> listMembers(@RequestParam Long groupId, @RequestParam(required = false) RequestStatus status) {
        List<GroupUser> members = groupUserService.listMembers(groupId, status);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/list-join-requests")
    public ResponseEntity<List<GroupUser>> listJoinRequests(@RequestParam Long groupId) {
        List<GroupUser> joinRequests = groupUserService.listJoinRequests(groupId);
        return ResponseEntity.ok(joinRequests);
    }
}
