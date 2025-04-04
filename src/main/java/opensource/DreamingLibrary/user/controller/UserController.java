package opensource.DreamingLibrary.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원(User)")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


}
