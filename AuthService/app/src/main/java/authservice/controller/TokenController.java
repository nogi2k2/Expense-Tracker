package authservice.controller;

import authservice.entities.RefreshToken;
import authservice.request.AuthRequestDTO;
import authservice.request.RefreshTokenRequestDTO;
import authservice.response.JwtResponseDTO;
import authservice.service.JwtService;
import authservice.service.RefreshTokenService;
import authservice.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

@Controller
public class TokenController {
	
	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private JwtService jwtService;

	@Autowired 
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("auth/v1/login")
	public ResponseEntity AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDto){
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
		if (authentication.isAuthenticated()){
			RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDto.getUsername());
			String userId = userDetailsService.getUserByUsername(authRequestDto.getUsername());

			if (Objects.nonNull(userId) && Objects.nonNull(refreshToken)){
				String jwtToken = jwtService.GenerateToken(authRequestDto.getUsername());
				return new ResponseEntity<>(JwtResponseDTO.builder()
														.accessToken(jwtToken)
														.token(refreshToken.getToken())
														.build(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("Esception thrown in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("auth/v1/refreshToken")
	public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDto){
		return refreshTokenService.findByToken(refreshTokenRequestDto.getToken())
								.map(refreshTokenService::verifyExpiration)
								.map(RefreshToken::getUserInfo)
								.map(userInfo ->{
									String jwtToken = jwtService.GenerateToken(userInfo.getUsername());
									return JwtResponseDTO.builder()
														.accessToken(jwtToken)
														.token(refreshTokenRequestDto.getToken())
														.build();
								}).orElseThrow(() -> new RuntimeException("Invalid Refresh Token"));
	}
}
