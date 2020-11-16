package com.dawn.controller;

import com.dawn.dto.SignupForm;
import com.dawn.service.SigninService;
import com.dawn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RootController {

    private final UserService userService;
    private final SigninService signinService;

    @GetMapping("/")
    public String index() {
        return "hello!";
    }

    @PostMapping("/register")
    public ResponseEntity signupPOST(@RequestBody SignupForm signupForm) {
        try {
            signinService.SignUp(signupForm);
        } catch (Exception exception) {
            return new ResponseEntity<String>(exception.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
