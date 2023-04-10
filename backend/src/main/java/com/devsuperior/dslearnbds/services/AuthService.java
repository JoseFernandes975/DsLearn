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
	
	
	public User authenticated() {
		try {
		//Se o usuario está autenticado
	String username = SecurityContextHolder.getContext().getAuthentication().getName();
	 User user = userRepo.findByEmail(username);
	 System.out.println("Authenticated User: "+user.getId());
	 return user;
		}catch(Exception e) {
			throw new UnauthorizedException("Invalid User!");
		}
	}
	
	
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		System.out.println("User Id: "+user.getId());
		if(!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied!!");
		}
	}

}
