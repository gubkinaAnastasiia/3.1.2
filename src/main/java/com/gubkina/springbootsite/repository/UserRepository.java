package com.gubkina.springbootsite.repository;

import com.gubkina.springbootsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User user join fetch user.rolesOfUser where user.username = :username")
    User findUserByUsername(@Param("username") String username);

}
