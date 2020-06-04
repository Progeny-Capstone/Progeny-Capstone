package com.progeny.repositories;

import com.progeny.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> { // < What we are dealing with, How it will be identified >
    // We will extend this class and define the type of objects it will be manipulating, as well as the data type of the entity's id.

    User findByUsername(String username);
    User findById(long id);
    User getUserById(long id);

    List<User> findAllById(Long id); // find all by user and return a list of users
}
