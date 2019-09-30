package com.companyname.springbootcrudrest.controller;

import java.util.*;

import javax.validation.Valid;

import com.companyname.springbootcrudrest.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.springbootcrudrest.exception.ResourceNotFoundException;
import com.companyname.springbootcrudrest.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("/users")
	public List<UserAccount> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserAccount> getUserById(
			@PathVariable(value = "id") String userId) throws ResourceNotFoundException {
		/*User paramUser = new User();
		if(param != null)
			paramUser = param.get();*/
		UserAccount user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/users")
	public UserAccount createUser(@Valid @RequestBody UserAccount user) {
		return userRepository.save(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<UserAccount> updateUser(
			@PathVariable(value = "id") String userId,
			@Valid @RequestBody UserAccount userDetails) throws ResourceNotFoundException {
		UserAccount user = userRepository.findById(userId)
		        .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		
		user.setUser_email(userDetails.getUser_email());
		final UserAccount updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(
			@PathVariable(value = "id") String userId) throws ResourceNotFoundException {
		UserAccount user = userRepository.findById(userId)
		        .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
