package com.fcmpush;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PushController {

    @RequestMapping()
    public String push() {
        System.out.println("dsadas");
        return "/fcm-snipet";
//        restTemplate.getForObject("https://fcm.googleapis.com/v1/web-push-76110/messages:send", String.class);
//        ResponseEntity<String> exchange = restTemplate.exchange("https://fcm.googleapis.com/v1/projects/web-push-76110/messages:send", HttpMethod.POST, null, String.class);
    }

}

//@RequiredArgsConstructor
//public class FirebaseSender {
//    private final RestTemplate restTemplate; // 비공개 키 경로////
//     private final String CONFIG_PATH = "firebase/firebase-key.json";
//     // 토큰 발급 URL
//private final String AUTH_URL = "https://www.googleapis.com/auth/cloud-platform";
//// 엔드포인트 URL
//private final String SEND_URL = "https://fcm.googleapis.com/v1/projects/프로젝트ID/messages:send";
//private String getAccessToken() throws IOException { // 토큰 발급 GoogleCredentials googleCredentials = GoogleCredentials .fromStream(new ClassPathResource(CONFIG_PATH).getInputStream()) .createScoped(List.of(AUTH_URL)); googleCredentials.refreshIfExpired(); return googleCredentials.getAccessToken().getTokenValue(); } @Getter @Builder private static class PushPayload { // API 호출시 Body에 보낼 객체 private boolean validate_only; private PushMessage message; @Getter @Builder private static class Message { private String token; private PushNotification notification; } @Getter @Builder private static class Notification { private String title; private String body; private String image; } } private PushPayload getBody(String to, String title, String body) { return PushPayload.builder() .validate_only(false) .message(PushPayload.Message.builder() .token(to) .notification(PushPayload.Notification.builder() .title(title) .body(body) .image(null) .build()) .build()) .build(); } public void pushBrowserSend(String to, String title, String body) { // 발송 API 호출 HttpHeaders headers = new HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON); headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken()); final HttpEntity<Object> entity = new HttpEntity<>(getBody(to, title, body), headers); final ResponseEntity<String> response = restTemplate.exchange(SEND_URL, HttpMethod.POST, entity, String.class); final HttpStatus status = response.getStatusCode(); final String responseBody = response.getBody(); if (status.equals(HttpStatus.OK)) { // 발송 API 호출 성공 } else { // 발송 API 호출 실패 } } }

