package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
      System.out.println("Delete Customer with ID = " + id + "...");
   
      userRepository.deleteById(id);
   
      return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }
    
    
    @PutMapping(("/users/{id}"))
    public Optional<User> update(@PathVariable("id") long id, @RequestBody User user) {
    	 Optional<User> userData = userRepository.findById(id);
    	 
    	    if(userData.isPresent()) {
    	    	User _user = userData.get();
    	    	     _user.setName(user.getName());
    	    	     _user.setEmail(user.getEmail());
    	    	     userRepository.save(_user);
    	    }
			
    	    return userData;
    	 
    	
    }
   
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getCustomerById(@PathVariable("id") long id) {
      Optional<User> customerData = userRepository.findById(id);
   
      if (customerData.isPresent()) {
        return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
    
   

    
   
}
