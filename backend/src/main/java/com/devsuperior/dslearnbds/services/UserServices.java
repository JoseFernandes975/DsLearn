package com.devsuperior.dslearnbds.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.dtos.UserDTO;
import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.exceptions.ResourceNotFoundException;
import com.devsuperior.dslearnbds.repositories.UserRepository;

@Service
public class UserServices implements UserDetailsService{

	private static Logger logger = LoggerFactory.getLogger(UserServices.class);
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService serviceAuth;
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
	serviceAuth.validateSelfOrAdmin(id);
	 Optional<User> obj = repository.findById(id);
	 User result = obj.orElseThrow(() -> new ResourceNotFoundException("User not found!"));
	 return new UserDTO(result);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if(user == null) {
			logger.error("Email not found: " + username);
			throw new UsernameNotFoundException("Email not found!");
		}
		logger.info("Email found: "+username);
		return user;
	}
	
}
