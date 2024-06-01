package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		try {
			Optional<User> obj = userRepository.findById(id);
			return obj.orElseThrow(() -> new ResourceNotFoundException(id));
			} catch (RuntimeException e) {
			throw new ResourceNotFoundException(id);
			}
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public void delete(Long id) {
		try {
			userRepository.deleteById(id);
			} catch (RuntimeException e) {
			throw new ResourceNotFoundException(id);
			}
	}
	
	public User update(Long id, User user) {
		try {
			User entity = findById(id);
			updateData(entity, user);
			return userRepository.save(entity);
			} catch (RuntimeException e) {
			throw new ResourceNotFoundException(id);
			}
	}
	
	public void updateData(User entity, User obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setTelefone(obj.getTelefone());
		entity.setPassword(obj.getPassword());
	}
	
}
