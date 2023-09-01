package com.example.cryptographychallenge.entites;

import com.example.cryptographychallenge.Dtos.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "tb_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long user_value;
    private Long salt;

    public Long getSalt() {
        return salt;
    }

    public void setSalt(Long salt) {
        this.salt = salt;
    }

    public UserEntity(Long id, String userDocument, String creditCardToken, Long user_value) {
        this.id = id;
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.user_value = user_value;
    }
    public UserEntity(UserDTO userDTO){
        this.userDocument = userDTO.getUserDocument();
        this.creditCardToken = userDTO.getCreditCardToken();
        this.user_value = userDTO.getUser_value();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public String getCreditCardToken() {
        return creditCardToken;
    }

    public void setCreditCardToken(String creditCardToken) {
        this.creditCardToken = creditCardToken;
    }

    public Long getUser_value() {
        return user_value;
    }

    public void setUser_value(Long user_value) {
        this.user_value = user_value;
    }

}
