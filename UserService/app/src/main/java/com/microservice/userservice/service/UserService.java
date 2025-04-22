package com.microservice.userservice.service;

import com.microservice.userservice.entities.UserInfoDto;
import com.microservice.userservice.entities.UserInfo;
import com.microservice.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto){
        UserInfo userInfoObj = userRepository.findByUserId(userInfoDto.getUserId())
                                            .map(existing -> userRepository.save(userInfoDto.transformToUserInfo()))
                                            .orElseGet(() -> userRepository.save(userInfoDto.transformToUserInfo()));
        
        return new UserInfoDto(
            userInfoObj.getUserId(),
            userInfoObj.getFirstName(),
            userInfoObj.getLastName(),
            userInfoObj.getPhoneNumber(),
            userInfoObj.getEmail(),
            userInfoObj.getProfilePic()
        );
    }

    public UserInfoDto getUser(UserInfoDto userInfoDto) throws Exception{
        Optional<UserInfo> userInfoOpt = userRepository.findByUserId(userInfoDto.getUserId());
        if (userInfoOpt.isEmpty()){
            throw new Exception("User Not Found!");
        }
        UserInfo userInfoObj = userInfoOpt.get();
        return new UserInfoDto(
            userInfoObj.getUserId(),
            userInfoObj.getFirstName(),
            userInfoObj.getLastName(),
            userInfoObj.getPhoneNumber(),
            userInfoObj.getEmail(),
            userInfoObj.getProfilePic()
        );
    }
}
