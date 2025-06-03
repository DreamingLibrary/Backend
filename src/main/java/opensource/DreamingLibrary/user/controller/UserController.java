package opensource.DreamingLibrary.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.user.dto.request.InfoRequest;
import opensource.DreamingLibrary.user.dto.request.PasswordRequest;
import opensource.DreamingLibrary.user.dto.response.UserResponse;
import opensource.DreamingLibrary.user.service.CustomUserDetails;
import opensource.DreamingLibrary.user.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원(User)")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @PutMapping("/info")
    @Operation(summary = "회원정보 수정")
    public ResponseEntity<?> editInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails, @Valid @RequestBody InfoRequest request) throws BadRequestException {
        return ResponseEntity.ok(userService.updateInfo(customUserDetails.getUsername(), request));
    }
    @PutMapping("/pwd")
    @Operation(summary = "회원 비밀번호")
    public ResponseEntity<?> editPwd(@AuthenticationPrincipal CustomUserDetails customUserDetails, @Valid @RequestBody PasswordRequest request) throws BadRequestException {
        userService.updatePwd(customUserDetails.getUsername(), request);
        return ResponseEntity.ok("비밀번호 변경 완료");
    }
    @GetMapping("")
    @Operation(summary = "회원 비밀번호")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(userService.getUserInfo(customUserDetails.getUsername()));
    }

}
