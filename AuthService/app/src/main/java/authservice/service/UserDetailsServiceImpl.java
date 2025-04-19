package authservice.service;

import authservice.entities.UserInfo;
import authservice.model.UserInfoDto;
import authservice.repository.UserRepository;

import lombok.Data;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;  

import java.util.UUID;
import java.util.Optional;
import java.util.HashSet;
import java.util.Objects;

@Component
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		UserInfo userInfo = userRepository.findByUsername(username);
		if (userInfo == null){
			throw new UsernameNotFoundException("Could not find the Username!");
		}
		return new CustomUserDetails(userInfo);
	}

	public UserInfo checkIfUserAlreadyExists(UserInfoDto userInfoDto){
		return userRepository.findByUsername(userInfoDto.getUsername());
	}

	public String getUserByUsername(String username){
		return Optional.of(userRepository.findByUsername(username)).map(UserInfo::getUserId).orElse(null);
	}

	public String signupUser(UserInfoDto userInfoDto){
		userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
		if (Objects.nonNull(checkIfUserAlreadyExists(userInfoDto))){
			return null;
		}

		String userId = UUID.randomUUID().toString();
		UserInfo userInfo = new UserInfo(userId, userInfoDto.getUsername(), userInfoDto.getPassword(), new HashSet<>());
		userRepository.save(userInfo);
		return userId;
	}
}
