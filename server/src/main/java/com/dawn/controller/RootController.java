package com.dawn.controller;

import com.dawn.dto.SignupForm;
import com.dawn.service.SigninService;
import com.dawn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getUserId/{loginId}")
    public int getUserId(@PathVariable String loginId){
        System.out.println("바은거" + loginId);
        int id = userService.getUserId(loginId.replace('-', '.'));
        return id;
    }

}
