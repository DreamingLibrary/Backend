package opensource.DreamingLibrary.test.controller;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import opensource.DreamingLibrary.global.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException e, HttpServletRequest request) {
        // 인증/권한 관련 에러 - WARNING 레벨로 Sentry 전송
        Sentry.setTag("error.type", "authentication");
        Sentry.setExtra("request.uri", request.getRequestURI());
        Sentry.captureException(e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of(401, e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        // 권한 부족 에러
        Sentry.setTag("error.type", "authorization");
        Sentry.setExtra("request.uri", request.getRequestURI());
        Sentry.captureMessage("권한 부족: " + e.getMessage(), SentryLevel.WARNING);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.of(403, "접근 권한이 없습니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        // 잘못된 입력값 에러 - INFO 레벨 (덜 심각함)
        Sentry.setTag("error.type", "validation");
        Sentry.setExtra("request.uri", request.getRequestURI());
        Sentry.captureMessage("입력값 검증 실패: " + e.getMessage(), SentryLevel.INFO);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(400, "잘못된 요청입니다."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception e, HttpServletRequest request) {
        // 예상치 못한 에러 - ERROR 레벨로 Sentry 전송
        Sentry.setTag("error.type", "unexpected");
        Sentry.setExtra("request.uri", request.getRequestURI());
        Sentry.setExtra("request.method", request.getMethod());
        Sentry.setExtra("error.class", e.getClass().getSimpleName());
        Sentry.captureException(e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(500, "서버 에러가 발생했습니다."));
    }
}
