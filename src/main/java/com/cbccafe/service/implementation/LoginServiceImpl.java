package com.cbccafe.service.implementation;

import com.cbccafe.entity.User;
import com.cbccafe.payload.LoginPayload;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.repository.UserRepository;
import com.cbccafe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserRepository userRepo;

    ResponseMessage responseMessage;

    @Override
    public ResponseEntity<Object> login(LoginPayload loginPayload){
        Integer cntUser = userRepo.countByUserNameAndPassword(loginPayload.getUserName(),loginPayload.getPassword());
        if(cntUser.equals(1)){
            return new ResponseEntity<Object>(loginPayload, HttpStatus.OK);
        } else {
            responseMessage = new ResponseMessage("Invalid user name or password");
            return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
        }
    }
}
