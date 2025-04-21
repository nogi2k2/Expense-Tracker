package com.microservice.userservice.repository;

import com.microservice.userservice.entities.UserInfo;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends CrudRepository<UserInfo, String>{
    Optional<UserInfo> findByUserId(String userId);
}
