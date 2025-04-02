package opensource.DreamingLibrary.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import opensource.DreamingLibrary.global.exception.CustomException;
import opensource.DreamingLibrary.global.exception.ErrorCode;
import opensource.DreamingLibrary.global.service.ResponseService;
import opensource.DreamingLibrary.user.dto.request.UserCreateRequest;
import opensource.DreamingLibrary.user.entity.User;
import opensource.DreamingLibrary.user.mapper.UserMapper;
import opensource.DreamingLibrary.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public SingleResult<Long> register(UserCreateRequest request){
        //로그인 아이디 중복 체크
        if(userRepository.existsByLoginId(request.loginId())){
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
        }

        User newUser = userRepository.save(UserMapper.from(request));

        return ResponseService.getSingleResult(newUser.getId());
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
