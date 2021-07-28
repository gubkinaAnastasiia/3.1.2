package com.gubkina.springbootsite.service;

import com.gubkina.springbootsite.model.Role;
import com.gubkina.springbootsite.model.User;
import com.gubkina.springbootsite.repository.RoleRepository;
import com.gubkina.springbootsite.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public void createUser(User user, String...roles) {
        user.setRolesOfUser(roleRepository.getRolesByRoleNames(roles));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user, long id, String...roles) {
        User userToUpdate = userRepository.getById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRolesOfUser(roleRepository.getRolesByRoleNames(roles));
        userRepository.save(userToUpdate);
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }
}
