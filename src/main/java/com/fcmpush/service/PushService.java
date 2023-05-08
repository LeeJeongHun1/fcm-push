package com.fcmpush.service;

import com.fcmpush.dto.MessageDto;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PushService {

    public void sendPush(MessageDto message) {
        MulticastMessage multicastMessage = buildPushMessage(message);

        int responseCnt = sendPushMessage(multicastMessage).size();

        log.info(responseCnt + "");
    }
    private MulticastMessage buildPushMessage(MessageDto messageDto) {
//        String publicUrl = pushNotificationDto.getImage() != null ?
//                pushNotificationDto.getImage().getPublicUrl() : "";
        Notification notification = getNotification(messageDto);

//        Map<String, String> data = new HashMap<>();
//        data.put("notificationKey", pushNotificationDto.getId());
//        if (pushNotificationDto.getLink() != null) {
//            data.put("link", pushNotificationDto.getLink());
//        }

        // ios app push config
        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(Aps.builder()
                        .setMutableContent(true)
                        .setSound("default")
                        .build())
                .build();

        // android app push config
        AndroidConfig androidConfig = AndroidConfig.builder()
                .setNotification(AndroidNotification.builder()
                        .setChannelId("default")
//                        .setImage(publicUrl)
                        .build())
                .build();

        WebpushConfig webpushConfig = WebpushConfig.builder()
                .setNotification(WebpushNotification.builder()
//                        .setChannelId("default")
                        .build())
                .build();

        return MulticastMessage.builder()
                .setNotification(notification)
//                .putAllData(data)
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setWebpushConfig(webpushConfig)
                .addToken(messageDto.getToken())
                .build();
    }

    private Notification getNotification(MessageDto messageDto) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(pushNotificationDto.getSender().getChannelName());
//        sb.append(" 채널에서 알림 전송");

//        String publicUrl = pushNotificationDto.getImage() != null ?
//                pushNotificationDto.getImage().getPublicUrl() : "";
        return Notification.builder()
                .setTitle(messageDto.getTitle())
                .setBody(messageDto.getMessage())
//                .setImage(publicUrl)
                .build();
    }

    private List<String> sendPushMessage(MulticastMessage pushMessage) {
        List<String> responseMsgIds = new ArrayList<>();
        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(pushMessage);
            responseMsgIds = response.getResponses().stream()
                    .map(SendResponse::getMessageId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            log.debug("[Push] request count: {}, success count: {}",
                    (response.getFailureCount() + response.getSuccessCount()), response.getSuccessCount());
        } catch (FirebaseMessagingException e) {
            log.error("[Push] Error: ", e);
        }
        return responseMsgIds;
    }

}
