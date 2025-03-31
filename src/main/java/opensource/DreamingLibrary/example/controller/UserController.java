package opensource.DreamingLibrary.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.example.dto.request.UserCreateRequest;
import opensource.DreamingLibrary.example.service.UserService;
import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원(User)")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/register")
    @Operation(summary = "회원가입")
    public SuccessResponse<SingleResult<Long>> register(@Valid @RequestBody UserCreateRequest request){
        SingleResult<Long> result = userService.register(request);
        return SuccessResponse.ok(result);
    }

}
