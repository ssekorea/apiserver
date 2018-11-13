package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.User;
import com.wellness.sseproject.domain.repository.UserRepository;
import com.wellness.sseproject.web.controller.dto.UserInfoDTO;
import com.wellness.sseproject.web.controller.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserQueryService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean checkValidId(String userId) {
        System.out.println("count " + userRepository.countByUserId(userId));
        return userRepository.countByUserId(userId) == 0;
    }


    public boolean checkValidLogin(String userId, String password) {
        String encryptedPassword = userRepository.findPasswordByUserId(userId);
        if (encryptedPassword == null) {
            return false;
        }
        return passwordEncoder().matches(password, encryptedPassword);
    }


    public UserInfoDTO getUserInfoDTOById(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            UserInfoDTO userInfoDTO = new UserInfoDTO(user);
            return userInfoDTO;
        }
        return null;
    }


    public User getUserById(String userId) {
        User user = userRepository.findByUserId(userId);
        return user;
    }


    public List<UserDTO> getUserList(int startIndex, int count) {

        int startCount = startIndex * count;

        List<User> users = userRepository.getUsersByPage(startCount, count);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user);
            userDTOList.add(userDTO);
        }
        if (userDTOList.size() == 0){
            return null;
        }
        return userDTOList;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
