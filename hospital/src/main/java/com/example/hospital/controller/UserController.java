package com.example.hospital.controller;

import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.UserDto;
import com.example.hospital.entity.User;
import com.example.hospital.serviece.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserInterface userInterface;

    @PostMapping("/create")

    public BaseResponse<User> addInfo(@RequestBody UserDto userDTO)   {
        BaseResponse<User> baseResponse=null;
        baseResponse = BaseResponse.<User>builder().Data(userInterface.addUser(userDTO)).build();
        return baseResponse;
    }

    @GetMapping("/userid/{id}")
    public BaseResponse<User> getUserById(@PathVariable Integer id){
        BaseResponse<User> baseResponse= null;
        baseResponse = BaseResponse.<User>builder().Data(userInterface.getUserById(id)).build();
        return baseResponse;
    }

    @PutMapping("/update")
    public BaseResponse<User> updateInfo(@RequestBody UserDto userDTO){
        BaseResponse<User> baseResponse= null;
        baseResponse = BaseResponse.<User>builder().Data(userInterface.updateUser(userDTO)).build();
        return baseResponse;
    }

    @GetMapping("/{offset}/{pageSize}/{name}")
    private PageResponse<User> getUserWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String name){
        return  userInterface.GetUserWithPagination(offset, pageSize, name);
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<User> deleteUser(@PathVariable int id){
        BaseResponse<User> baseResponse=null;
        baseResponse=BaseResponse.<User>builder().Data(userInterface.deleteUser(id)).build();
        return baseResponse;
    }
}
