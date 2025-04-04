package opensource.DreamingLibrary.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.user.dto.request.UserCreateRequest;
import opensource.DreamingLibrary.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {
    private final UserService userService;

    //회원가입
    @PostMapping("/join")
    @Operation(summary = "회원가입")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreateRequest request){
        userService.register(request);
        return ResponseEntity.ok("회원가입성공");
    }
    @GetMapping("/")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("접근 성공");
    }

}
