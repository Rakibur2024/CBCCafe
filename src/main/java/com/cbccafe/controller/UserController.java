package com.cbccafe.controller;

import com.cbccafe.entity.User;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.projection.UserProjection;
import com.cbccafe.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserProjection>> userList(){
        List<UserProjection> userList = userService.userList();
        return new ResponseEntity<List<UserProjection>>(userList, HttpStatus.OK);
    }

    @GetMapping("/byid/{id}")
    public Optional<UserProjection> userById(@PathVariable("id") Long id){
        return userService.userById(id);
    }

    @PutMapping("update/byid/{id}")
    public ResponseEntity<ResponseMessage> updateUser(@Valid @RequestBody User user, @PathVariable("id") Long id){
        return userService.updateUser(user,id);
    }

    @DeleteMapping("/delete/byid/{id}")
    public ResponseEntity<ResponseMessage> deleteUserById(@PathVariable("id") Long id){
        return userService.deleteUserById(id);
    }
}
