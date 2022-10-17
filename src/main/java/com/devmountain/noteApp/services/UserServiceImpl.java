package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.entities.User;
import com.devmountain.noteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;


//all business logic
//interacts with repository layer
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//handles registering the user
//verify user credentials and logging in
// use transactional when you save to database to ensure transactions that get opened with datasource gets resolved
 @Override
 @Transactional
    public List<String>addUser(UserDto userDto){
    List<String>response = new ArrayList<>();
     User user = new User(userDto);
     getUser(user);
     response.add("http://localhose:8080/login.html");
     return response;
 }

    private User getUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    //optional are used to avoid NullPointerExceptions
 @Override
 public List<String>userLogin(UserDto userDto) {
     List<String> response = new ArrayList<>();
     Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
     if (userOptional.isPresent()){
     if( passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
         response.add("http://localhose:8080/login.html");
         response.add(String.valueOf(userOptional.get().getId()));
     } else {
         response.add("Username or password incorrect");
     }
     } else {
     response.add("Username or password incorrect");
 }
 return response;
}
}
