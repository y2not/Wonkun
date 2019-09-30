package com.companyname.springbootcrudrest.controller;

import java.util.*;

import javax.validation.Valid;

import com.companyname.springbootcrudrest.event.UserEvent;
import com.companyname.springbootcrudrest.model.UserAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.companyname.springbootcrudrest.exception.ResourceNotFoundException;
import com.companyname.springbootcrudrest.repository.UserRepository;

@RestController
@RequestMapping("user/api")
public class UserApiController {

	@Autowired
	private KafkaTemplate kafkaTemplate;

	@Autowired
	private UserRepository userRepository;

	PasswordEncoder passwordEncoder;

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

	@CrossOrigin("*")
	@PostMapping("/users")
	public UserAccount createUser(@Valid @RequestBody UserAccount inputUser) {
		passwordEncoder = new BCryptPasswordEncoder();
		UserAccount user = inputUser;
		user.setUser_password(passwordEncoder.encode(inputUser.getUser_password()));
		user.setAccount_non_expired(true);
		user.setAccount_non_locked(true);
		user.setCredential_non_expired(true);

		UserEvent userEvent = new UserEvent();
		userEvent.setEventType("NEW_USER");
		userEvent.setUserId(inputUser.getUser_id());
		sendNewUser(userEvent);

		return userRepository.save(user);
	}

	private void sendNewUser(Object object){
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("JSON format exception", e);
		}

		System.out.println("===== pub Event ====");
		System.out.println(json);
		ProducerRecord producerRecord = new ProducerRecord<>("USER", json);
		//kafkaTemplate.send(producerRecord);
	}

	/*@KafkaListener(topics = "USER", groupId = "test")
	public void onMessageSimulated(@Payload String message, ConsumerRecord<?, ?> consumerRecord) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserEvent newUser = null;
		try {
			newUser = objectMapper.readValue(message, UserEvent.class);

			if( newUser.getEventType().equals("NEW_USER")){

				System.out.println("이벤트 수신!!!!!!!!!");
				System.out.println(message);
			}
		} catch (
				IOException e) {
			e.printStackTrace();
		}
	}*/
	/*@PutMapping("/users/{id}")
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
	}*/
}
