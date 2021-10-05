package com.example.hospital.servieceImplements;

import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.UserDto;
import com.example.hospital.entity.User;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.serviece.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServieceImplements implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(UserDto userDTO) {

        User user = new User();
        user.setUserName(userDTO.getUserName());
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(userDTO.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user= new User();
        userRepository.findById(id);
        return user;
    }

    @Override
    public User updateUser(UserDto userDTO) {
        User existUser = new User();
        userRepository.findById(userDTO.getUserId());
        existUser.setUserName(userDTO.getUserName());
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        existUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userRepository.save(existUser);

        return existUser;
    }

    @Override
    public PageResponse<User> GetUserWithPagination(int offset, int pageSize, String name) {
            PageResponse pageResponse = new PageResponse();
            Pageable pageable = PageRequest.of(offset, pageSize);
            Page<User> Users = userRepository.searchAllByName("%"+ name + "%", pageable);
            pageResponse.setResponse(Users);
            pageResponse.setRecordCount(Users.getTotalPages());

        return pageResponse;
    }

    @Override
    public User deleteUser(int id) {
        User user = new User();
        userRepository.deleteById(id);
        return user;
    }

}
