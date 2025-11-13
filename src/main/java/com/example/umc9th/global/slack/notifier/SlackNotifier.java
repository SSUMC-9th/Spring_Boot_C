package com.example.umc9th.global.slack.notifier;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class SlackNotifier {
    @Value("${slack.webhook.url}")
    private String url;

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    private final RestTemplate restTemplate = new RestTemplate();

    public void notifyServerError(Exception e, HttpServletRequest request) {
        // 로컬 환경이면 슬랙 알림 보내지 않음
       //if ("local".equals(activeProfile)) return;


        String errorTitle = "*서버 에러 발생!*";
        String time = LocalDateTime.now().toString();
        String uri = request.getRequestURI();
        String exceptionType = e.getClass().getSimpleName();
        String message = e.getMessage();

        // Slack 기본 메시지 포맷 (Block kit 아님, 단순 텍스트)
        String text = String.format(
                "%s\n" +
                        "*시간:* %s\n" +
                        "*요청 URL:* `%s`\n" +
                        "*예외 타입:* `%s`\n" +
                        "*메시지:* %s\n",
                errorTitle, time, uri, exceptionType, message
        );

        Map<String, String> body = new HashMap<>();
        body.put("text", text);

        try {
            restTemplate.postForObject(url, body, String.class);
            log.info("슬랙 알림 전송 성공");
        } catch (Exception ex) {
            log.error("슬랙 알림 전송 실패: {}", ex.getMessage());
        }
    }
}

