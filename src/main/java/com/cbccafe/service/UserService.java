package com.cbccafe.service;

import com.cbccafe.entity.User;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.projection.UserProjection;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public ResponseEntity<ResponseMessage> createUser(User user);
    public List<UserProjection> userList();
    public ResponseEntity<ResponseMessage> deleteUserById(Long id);
    public ResponseEntity<ResponseMessage> updateUser(User user,Long id);
    public Optional<UserProjection> userById( Long id);
}
