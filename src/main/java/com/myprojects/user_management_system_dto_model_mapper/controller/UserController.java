package com.myprojects.user_management_system_dto_model_mapper.controller;

import com.myprojects.user_management_system_dto_model_mapper.dto.UserDto;
import com.myprojects.user_management_system_dto_model_mapper.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userServiceImpl;

    @GetMapping("/start")
    public String welcome(){
        return "welcome to user management project";
    }

    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userServiceImpl.createUser(userDto),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userServiceImpl.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userServiceImpl.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userServiceImpl.updateUser(userDto),HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userServiceImpl.deleteUser(id),HttpStatus.OK);
    }
}
