package opensource.DreamingLibrary.user.service;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import opensource.DreamingLibrary.global.exception.CustomException;
import opensource.DreamingLibrary.global.exception.ErrorCode;
import opensource.DreamingLibrary.global.service.ResponseService;
import opensource.DreamingLibrary.user.dto.request.UserCreateRequest;
import opensource.DreamingLibrary.user.entity.Role;
import opensource.DreamingLibrary.user.entity.User;
import opensource.DreamingLibrary.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //회원가입
    @Transactional
    public void register(UserCreateRequest request){
        User user = User.builder()
                .studentNumber(request.getStudentNumber())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.USER)
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
        userRepository.save(user);
    }

    /*
    of 메서드 참고 용도!로...

    //특정 회원 조회
    public SingleResult<UserResponse> findById(String token){
        Long id = authService.getUserIdFromToken(token);
        User user = userRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_EXIST));

        return ResponseService.getSingleResult(UserResponse.of(user));
    }
    */
}
