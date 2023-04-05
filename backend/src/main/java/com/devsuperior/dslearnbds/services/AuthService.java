package com.devsuperior.dslearnbds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.exceptions.ForbiddenException;
import com.devsuperior.dslearnbds.exceptions.UnauthorizedException;
import com.devsuperior.dslearnbds.repositories.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Transactional(readOnly = true)
	public User authenticated() {
		try {
		//Se o usuario está autenticado
	String username = SecurityContextHolder.getContext().getAuthentication().getName();
	 return userRepo.findByEmail(username);
		}catch(Exception e) {
			throw new UnauthorizedException("Invalid User!");
		}
	}
	
	
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if(!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied!!");
		}
	}

}
