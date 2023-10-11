package com.embosoft.PicPay_Simplificado.services;

import com.embosoft.PicPay_Simplificado.DTO.UserDTO;
import com.embosoft.PicPay_Simplificado.domain.user.User;
import com.embosoft.PicPay_Simplificado.domain.user.UserType;
import com.embosoft.PicPay_Simplificado.exceptions.BadRequestException;
import com.embosoft.PicPay_Simplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) {
        if (sender.getUserType().equals(UserType.MERCHANT)) {
            throw new BadRequestException("Usuário do itpo lojista não está autorizado a realizar transação");
        }

        if (sender.getBalance().compareTo(amount) <= 0) {
            throw new BadRequestException("Saldo insuficiente");
        }
    }

    public User findUserById(UUID id) {
        return this.repository.findUserById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }

    public User createUser(UserDTO userDto) {

        if (this.repository.findUserByDocument(userDto.document()).isPresent()) {
            throw new BadRequestException("Não é possível cadastrar um usuário com um documento já existente");
        }

        User newUser = new User(userDto);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
