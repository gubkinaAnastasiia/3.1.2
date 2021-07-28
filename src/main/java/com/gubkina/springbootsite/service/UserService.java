package com.gubkina.springbootsite.service;

import com.gubkina.springbootsite.model.Role;
import com.gubkina.springbootsite.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    void createUser(User user, String...roles);
    List<User> getAllUsers();
    User getUserById(long id);
    void updateUser(User user, long id, String...roles);
    void deleteUserById(long id);
    Set<Role> getAllRoles();

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
