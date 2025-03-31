package opensource.DreamingLibrary.example.user.mapper;

import opensource.DreamingLibrary.example.user.dto.request.UserCreateRequest;
import opensource.DreamingLibrary.example.user.entity.User;

public class UserMapper {
    //DTO와 엔티티 사이의 변환을 담당
    public static User from(UserCreateRequest request){
        return User.builder()
                .loginId(request.loginId())
                .password(request.password())
                .name(request.name())
                .build();
    }
}
