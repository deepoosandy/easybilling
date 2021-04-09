package com.sanapp.sms.services;

import com.sanapp.sms.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

	UserDto findByUsername(String username);

	UserDto save(UserDto registration);
	
}