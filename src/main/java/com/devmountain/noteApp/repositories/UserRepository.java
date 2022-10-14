package com.devmountain.noteApp.repositories;


import com.devmountain.noteApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//responsible for interacting with the database using JPA
// helps with keeping track of dependency injection
//handles dependency injection
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User>findByUsername(String username);

}
