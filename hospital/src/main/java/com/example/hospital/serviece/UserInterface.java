package com.example.hospital.serviece;

import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.UserDto;
import com.example.hospital.entity.User;

public interface UserInterface {

    User addUser(UserDto userDTO);

    User getUserById(Integer id);

    User updateUser(UserDto userDTO);

    PageResponse<User> GetUserWithPagination(int offset, int pageSize, String name);

    User deleteUser(int id);
}
