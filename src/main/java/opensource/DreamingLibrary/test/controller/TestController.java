package opensource.DreamingLibrary.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sentry.Sentry;
import io.sentry.SentryLevel;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping("/error")
    public String testError() {
        throw new RuntimeException("Sentry 테스트용 의도적인 에러입니다!");
    }
    
    @GetMapping("/manual")
    public String testManualCapture() {
        try {
            // 의도적으로 에러 발생
            int result = 10 / 0;
        } catch (Exception e) {
            // 수동으로 Sentry에 에러 전송
            Sentry.captureException(e);
            return "에러가 Sentry로 전송되었습니다: " + e.getMessage();
        }
        return "success";
    }
    
    @GetMapping("/message")
    public String testMessage() {
        Sentry.captureMessage("테스트 메시지입니다", SentryLevel.INFO);
        return "메시지가 Sentry로 전송되었습니다";
    }
}
