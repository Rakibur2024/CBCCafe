package com.cbccafe.controller;

import com.cbccafe.entity.User;
import com.cbccafe.payload.LoginPayload;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    ResponseMessage responseMessage;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginPayload loginPayload){
        return loginService.login(loginPayload);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseMessage> logout(){
        responseMessage = new ResponseMessage("Logged Out");
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

}
