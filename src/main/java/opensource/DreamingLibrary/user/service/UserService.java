package opensource.DreamingLibrary.user.service;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import opensource.DreamingLibrary.global.exception.CustomException;
import opensource.DreamingLibrary.global.exception.ErrorCode;
import opensource.DreamingLibrary.global.service.ResponseService;
import opensource.DreamingLibrary.user.dto.request.InfoRequest;
import opensource.DreamingLibrary.user.dto.request.PasswordRequest;
import opensource.DreamingLibrary.user.dto.request.UserCreateRequest;
import opensource.DreamingLibrary.user.dto.response.UserResponse;
import opensource.DreamingLibrary.user.entity.Role;
import opensource.DreamingLibrary.user.entity.User;
import opensource.DreamingLibrary.user.repository.UserRepository;
import org.apache.coyote.BadRequestException;
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
    public void register(UserCreateRequest request) throws BadRequestException {
        if(userRepository.existsByStudentNumber(request.getStudentNumber())){
            throw new BadRequestException("이미 존재하는 student Number입니다.");
        }
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

    @Transactional
    public UserResponse updateInfo(String studentNumber, InfoRequest request){
        User entity = userRepository.findByStudentNumber(studentNumber);
        entity.setStudentNumber(request.getStudentNumber());
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(entity);
        return UserResponse.from(entity);
    }

    @Transactional
    public void updatePwd(String studentNumber, PasswordRequest request){
        User entity = userRepository.findByStudentNumber(studentNumber);
        entity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userRepository.save(entity);
    }

}
