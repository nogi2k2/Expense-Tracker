package com.microservice.userservice.service;

import com.microservice.userservice.entities.UserInfoDto;
import com.microservice.userservice.entities.UserInfo;
import com.microservice.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto){

        UnaryOperator<UserInfo> updatingUser = user -> {
            return userRepository.save(userInfoDto.transformToUserInfo());
        };

        Supplier<UserInfo> createUser = () -> {
            return userRepository.save(userInfoDto.transformToUserInfo());
        };

        UserInfo userInfoObj = userRepository.findByUserId(userInfoDto.getUserId())
                                            .map(updatingUser)
                                            .orElseGet(createUser);
                                            // .map(existing -> userRepository.save(userInfoDto.transformToUserInfo()))
                                            // .orElseGet(() -> userRepository.save(userInfoDto.transformToUserInfo()));
        
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
