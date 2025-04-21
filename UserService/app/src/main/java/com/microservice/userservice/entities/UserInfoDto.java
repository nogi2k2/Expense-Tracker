package com.microservice.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfoDto {

    @JsonProperty("user_id")
    @NonNull
    private String userId;

    @JsonProperty("first_name")
    @NonNull
    private String firstName;

    @JsonProperty("last_name")
    @NonNull
    private String lastName;

    @JsonProperty("phone_number")
    @NonNull
    private Long phoneNumber;

    @JsonProperty("email")
    @NonNull
    private String email;

    @JsonProperty("profile_pic")
    private String profilePic;

    public UserInfo transformToUserInfo(){
        return UserInfo.builder()
                    .userId(userId)
                    .firstName(firstName)
                    .lastName(lastName)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .profilePic(profilePic)
                    .build();
    }
}
