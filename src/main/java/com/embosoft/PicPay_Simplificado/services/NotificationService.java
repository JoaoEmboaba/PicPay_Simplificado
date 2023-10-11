package com.embosoft.PicPay_Simplificado.services;

import com.embosoft.PicPay_Simplificado.DTO.NotificationDTO;
import com.embosoft.PicPay_Simplificado.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) {

        final String notifyUrl = "http://o4d9z.mocklab.io/notify";

        String email = user.getEmail();

        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(notifyUrl, notificationRequest, String.class);

        if (!notificationResponse.getStatusCode().equals(OK)) {
            throw new ResponseStatusException(SERVICE_UNAVAILABLE, "Serviço de notificação fora do ar");
        }
    }
}
