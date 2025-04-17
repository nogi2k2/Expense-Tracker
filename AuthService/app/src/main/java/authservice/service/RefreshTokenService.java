package authservice.service;

import authservice.entities.RefreshToken;
import authservice.entities.UserInfo;
import authservice.repository.UserRepository;
import authservice.repository.RefreshTokenRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

	@Autowired
	RefreshTokenRepository refreshTokenRepository;

	@Autowired
	UserRepository userRepository;

	public Optional<RefreshToken> findByToken(String token){
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(String username){
		UserInfo userInfo = userRepository.findByUsername(username);
		RefreshToken refreshToken = RefreshToken
											.builder()
											.userInfo(userInfo)
											.token(UUID.randomUUID().toString())
											.expiryDate(Instant.now().plusMillis(6000000))
											.build();
		return refreshTokenRepository.save(refreshToken);
	}

	public RefreshToken verifyExpiration(RefreshToken token){
		if (token.getExpiryDate().compareTo(Instant.now())<0){
			refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken() + " Refresh Token is Expired. Please Login Again!");
		}
		return token;
	}
}
