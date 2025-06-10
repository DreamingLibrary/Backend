package opensource.DreamingLibrary.group.controller;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.group.service.GroupUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import opensource.DreamingLibrary.group.entity.GroupUser;
import opensource.DreamingLibrary.group.dto.response.GroupJoinResponse;
import opensource.DreamingLibrary.group.dto.response.GroupMemberResponse;
import opensource.DreamingLibrary.group.dto.response.GroupJoinRequestResponse;
import opensource.DreamingLibrary.group.dto.request.ApproveOrRejectRequest;
import opensource.DreamingLibrary.group.dto.response.ApproveOrRejectResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/group-user")
@RequiredArgsConstructor
public class GroupUserController {

    private final GroupUserService groupUserService;

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return authentication.getName();
    }

    @PostMapping("/set-admin")
    public ResponseEntity<?> setGroupAdmin(@RequestParam Long groupId, @RequestParam Long userId) {
        try {
            String currentUsername = getCurrentUsername();
            groupUserService.setGroupAdmin(groupId, userId);
            return ResponseEntity.ok(SuccessResponse.ok("그룹 관리자가 성공적으로 지정되었습니다."));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ErrorResponse.of(401, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 관리자 지정 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @PostMapping("/request-join")
    public ResponseEntity<?> requestJoinGroup(@RequestParam Long groupId) {
        try {
            String currentUsername = getCurrentUsername();
            GroupUser groupUser = groupUserService.requestJoinGroup(groupId, currentUsername);
            GroupJoinResponse response = GroupJoinResponse.of(groupUser);
            return ResponseEntity.ok(SuccessResponse.ok(response));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ErrorResponse.of(401, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 입장 신청 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @GetMapping("/is-in-group")
    public ResponseEntity<?> isUserInGroup(@RequestParam Long groupId) {
        try {
            String currentUsername = getCurrentUsername();
            GroupUser.RequestStatus status = groupUserService.getUserGroupStatus(groupId, currentUsername);
            return ResponseEntity.ok(SuccessResponse.ok(status));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ErrorResponse.of(401, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 참여 여부 확인 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @GetMapping("/list-members")
    public ResponseEntity<?> listMembers(@RequestParam Long groupId) {
        try {
            getCurrentUsername();
            List<GroupUser> approvedMembers = groupUserService.listApprovedMembers(groupId);
            List<GroupMemberResponse> response = approvedMembers.stream()
                    .map(member -> GroupMemberResponse.of(member.getUser().getId(), member.getUser().getStudentNumber(), member.getUser().getName()))
                    .toList();
            return ResponseEntity.ok(SuccessResponse.ok(response));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ErrorResponse.of(401, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 멤버 조회 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @GetMapping("/list-join-requests")
    public ResponseEntity<?> listJoinRequests(@RequestParam Long groupId, @RequestParam(required = false) GroupUser.RequestStatus status) {
        try {
            getCurrentUsername();
            List<GroupUser> joinRequests = groupUserService.listJoinRequests(groupId, status);
            List<GroupJoinRequestResponse> response = joinRequests.stream()
                    .map(GroupJoinRequestResponse::of)
                    .toList();
            return ResponseEntity.ok(SuccessResponse.ok(response));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ErrorResponse.of(401, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "그룹 입장 요청 조회 중 오류가 발생했습니다.", e.getMessage()));
        }
    }

    @PutMapping("/approve-or-reject")
    public ResponseEntity<?> approveOrRejectJoinRequest(@RequestBody ApproveOrRejectRequest request) {
        try {
            String currentUsername = getCurrentUsername();
            groupUserService.checkIfAdmin(request.groupId(), currentUsername);
            GroupUser.RequestStatus updatedStatus = groupUserService.updateJoinRequestStatus(request.groupId(), request.userId(), request.status());
            ApproveOrRejectResponse response = ApproveOrRejectResponse.of(request.groupId(), request.userId(), updatedStatus);
            return ResponseEntity.ok(SuccessResponse.ok(response));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ErrorResponse.of(401, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of(500, "입장 신청 상태 업데이트 중 오류가 발생했습니다.", e.getMessage()));
        }
    }
}
