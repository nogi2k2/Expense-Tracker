package com.microservice.userservice.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import com.microservice.userservice.entities.UserInfoDto;
import java.util.Map;

public class UserInfoDesrializer implements Deserializer<UserInfoDto>{

    @Override
    public void configure(Map<String, ?> map, boolean b){}

    @Override
    public void close(){}

    @Override
    public UserInfoDto deserialize(String arg0, byte[] arg1){
        UserInfoDto userInfoDto = null;
        ObjectMapper objMapper = new ObjectMapper();

        try{
            userInfoDto = objMapper.readValue(arg1, UserInfoDto.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return userInfoDto;
    }
}
