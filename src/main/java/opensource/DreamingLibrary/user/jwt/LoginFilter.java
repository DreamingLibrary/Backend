package opensource.DreamingLibrary.user.jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.user.dto.response.UserResponse;
import opensource.DreamingLibrary.user.entity.User;
import opensource.DreamingLibrary.user.repository.UserRepository;
import opensource.DreamingLibrary.user.service.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private static final String CONTENT_TYPE = "application/json";
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if(request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE)){
            throw new AuthenticationServiceException("요청타입이 json이여야 합니다.");
        }
        try {
            // 요청 본문에서 JSON 형식의 데이터를 추출
            String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            // JSON 데이터에서 사용자 이름과 비밀번호 추출
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            String username = jsonNode.get("studentNumber").asText();
            String password = jsonNode.get("password").asText();
            // 추출한 사용자 이름과 비밀번호로 UsernamePasswordAuthenticationToken 생성
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

            // AuthenticationManager를 통해 인증을 시도하고 결과 반환
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();


        String token = jwtUtil.createJwt("access", role, username, 60*60*10L);
        response.addHeader("Authorization", "Bearer " + token);

        User user = userRepository.findByStudentNumber(username);
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Jackson ObjectMapper를 사용하여 DTO를 JSON으로 변환
            new ObjectMapper().writeValue(response.getWriter(), UserResponse.from(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(401);
    }
}
