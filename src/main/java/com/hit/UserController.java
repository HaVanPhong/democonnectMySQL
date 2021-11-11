package com.hit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hit.Exception.DuplicateEx;
import com.hit.Exception.NotFoundEx;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepositoty repositoty;
	
	
	@GetMapping
	public ResponseEntity<?> getAllUser(){
		List<User> users= repositoty.findAll();
		if (users.size()==0) {
			throw new NotFoundEx("Not Found Any User");
		}
		return ResponseEntity.status(200).body(users);
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
		User oldUser= repositoty.findByUsername(userDTO.getUsername());
		if (oldUser!=null) {
			throw new DuplicateEx("The User existed");
		}
		oldUser= new User();
		oldUser.setUsername(userDTO.getUsername());
		oldUser.setPassword(userDTO.getPassword());
		User newUser= repositoty.save(oldUser);
		return ResponseEntity.status(201).body(newUser);
	}
}
