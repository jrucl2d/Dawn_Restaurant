package com.dawn.dto;


import com.dawn.model.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {

    private String loginId;
    private String userName;
    private String password;
    private boolean isBusiness;

    public User toUserEntity() {
        return new User(this.loginId, this.password, this.userName, this.isBusiness);
    }

}