package com.cbccafe.service.implementation;

import com.cbccafe.entity.User;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.projection.UserProjection;
import com.cbccafe.repository.UserRepository;
import com.cbccafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;

    ResponseMessage responseMessage;

    @Override
    public ResponseEntity<ResponseMessage> createUser(User user){
        Integer countEmployeeId = userRepo.countByEmployeeId(user.getEmployee().getId());
        if(countEmployeeId > 0){
            responseMessage = new ResponseMessage("Sorry! Already created user id for provided employee");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(userRepo.countByUserName(user.getUserName()) > 0){
            responseMessage = new ResponseMessage("Please try with another user name");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            User savedUser = userRepo.save(user);
            responseMessage = new ResponseMessage("User created successfully");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
    }

    @Override
    public List<UserProjection> userList(){
        List<UserProjection> users = userRepo.findAllUsers();
        return users;
    }

    @Override
    public Optional<UserProjection> userById(Long id){
        Optional<UserProjection> user = userRepo.findUserByID(id);
        return user;
    }

    @Override
    public ResponseEntity<ResponseMessage> updateUser(User user,Long id){
        Integer coundById = userRepo.countByUserNameAndIdNot(user.getUserName(),id);
        if(coundById > 0){
            responseMessage = new ResponseMessage("Please try another user name");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else if (userRepo.countByEmployeeIdAndIdNot(user.getEmployee().getId(),id) > 0) {
            responseMessage = new ResponseMessage("Sorry! Already created user id for provided employee");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            User existingUser = userRepo.findById(id).get();

            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmployee(user.getEmployee());

            userRepo.save(existingUser);

            ResponseMessage responseMessage = new ResponseMessage("User info saved successfully !");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteUserById(Long id){
        Integer countById = userRepo.countById(id);
        if(countById.equals(0)){
            responseMessage = new ResponseMessage("Sorry ! Invalid ID.");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            userRepo.deleteById(id);
            responseMessage = new ResponseMessage("User deleted successfully");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
    }
}
