package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.User;
import com.wellness.sseproject.domain.repository.UserRepository;
import com.wellness.sseproject.web.controller.dto.UserInfoDTO;
import com.wellness.sseproject.web.controller.dto.UserModifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    @Autowired
    UserRepository userRepository;

    
    public void registerUser(UserInfoDTO userInfoDTO) {
        User user = new User(userInfoDTO);
        userRepository.save(user);
    }

    
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    
    public User modifyUser(UserModifyDTO userModifyDTO) {

        User user = userRepository.findByUserId(userModifyDTO.getUserId());
        user.modifyUser(userModifyDTO);
        userRepository.save(user);
        return user;

    }


}
