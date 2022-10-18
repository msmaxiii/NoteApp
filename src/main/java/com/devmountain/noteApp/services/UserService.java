package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    //handles registering the user
    //verify user credentials and logging in
    // use transactional when you save to database to ensure transactions that get opened with datasource gets resolved
    @Transactional
    List<String> addUser(UserDto userDto);

    //optional are used to avoid NullPointerExceptions
    List<String> userLogin(UserDto userDto);
}
