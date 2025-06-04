package opensource.DreamingLibrary.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.user.dto.request.UserCreateRequest;
import opensource.DreamingLibrary.user.dto.response.UserResponse;
import opensource.DreamingLibrary.user.service.UserService;
import org.apache.coyote.BadRequestException;
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
    @ApiResponses(value={
            @ApiResponse(responseCode="200", content = {@Content(schema= @Schema(implementation = UserResponse.class)
            )})
    })
    public ResponseEntity<?> register(HttpServletResponse response, @Valid @RequestBody UserCreateRequest request) throws BadRequestException {
        userService.register(response, request);
        return ResponseEntity.ok("회원가입성공");
    }
    @GetMapping("/")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("접근 성공");
    }

}
