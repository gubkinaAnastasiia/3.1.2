package com.gubkina.springbootsite.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
public class Role implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "rolesOfUser")
    private Set<User> usersOfRole;

    public Role() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsersOfRole() {
        return usersOfRole;
    }
    public void setUsersOfRole(Set<User> users) {
        this.usersOfRole = users;
    }

    @Override
    public String getAuthority() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

}
