package com.embosoft.PicPay_Simplificado.services;

import com.embosoft.PicPay_Simplificado.DTO.TransactionDTO;
import com.embosoft.PicPay_Simplificado.domain.transaction.Transaction;
import com.embosoft.PicPay_Simplificado.domain.user.User;
import com.embosoft.PicPay_Simplificado.exceptions.UnauthorizedException;
import com.embosoft.PicPay_Simplificado.repositories.TransactionRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@Setter(onMethod = @__(@Autowired))
public class TransactionService {

    private TransactionRepository transactionRepository;
    private UserService userService;
    private RestTemplate restTemplate;
    private NotificationService notificationService;

    private final String mockUrl = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";

    @Transactional
    public Transaction createTransaction(TransactionDTO transaction) {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        if (!this.authorizeTransaction()) {
            throw new UnauthorizedException("Transação não autorizada");
        }

        Transaction newTransaction = instantiateTransaction(transaction, sender, receiver);

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso!");

        return newTransaction;
    }

    private boolean authorizeTransaction() {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(mockUrl, Map.class);

        if (authorizationResponse.getStatusCode().equals(OK) && Objects.requireNonNull(authorizationResponse.getBody(), "O corpo do mock não pode ser nulo").get("message").equals("Autorizado")) {
            String message = (String) Objects.requireNonNull(authorizationResponse.getBody().get("message"), "O corpo do mock não pode ser nulo");
            return "Autorizado".equalsIgnoreCase(message);
        }

        return false;
    }

    private Transaction instantiateTransaction(TransactionDTO transactionDTO, User sender, User receiver) {

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        return transaction;
    }
}
