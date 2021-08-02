package com.codeup.springblog.repositories;

import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    //what does the repository take the place of?
    //this repository replaces the DAO's that we had to write out for AdLister
    User findById(long id);
    User findByUsername(String username);


}
