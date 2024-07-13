package com.cbccafe.service;

import com.cbccafe.entity.User;
import com.cbccafe.payload.LoginPayload;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    public ResponseEntity<Object> login(LoginPayload loginPayload);
}