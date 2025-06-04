package opensource.DreamingLibrary.group.controller;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.group.service.GroupJoinRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group-join-request")
@RequiredArgsConstructor
public class GroupJoinRequestController {

    private final GroupJoinRequestService groupJoinRequestService;

    @PostMapping("/create")
    public ResponseEntity<?> createJoinRequest(@RequestParam Long groupId, @RequestParam Long userId) {
        groupJoinRequestService.createJoinRequest(groupId, userId);
        return ResponseEntity.ok("그룹 입장 신청이 완료되었습니다.");
    }

    @GetMapping("/is-pending")
    public ResponseEntity<?> isJoinRequestPending(@RequestParam Long groupId, @RequestParam Long userId) {
        boolean isPending = groupJoinRequestService.isJoinRequestPending(groupId, userId);
        return ResponseEntity.ok(isPending);
    }
}
