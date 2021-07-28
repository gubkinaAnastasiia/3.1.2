package com.gubkina.springbootsite.controller;

import com.gubkina.springbootsite.model.Role;
import com.gubkina.springbootsite.model.User;
import com.gubkina.springbootsite.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@Log
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public ModelAndView getAdminPage(Principal principal) {
        ModelAndView mav = new ModelAndView("adminPage");

        List<User> users = userService.getAllUsers();
        mav.addObject("users", users);

        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        mav.addObject("currentUser", currentUser);

        Set<Role> roles = userService.getAllRoles();
        mav.addObject("roles", roles);

        User newUser = new User();
        mav.addObject("newUser", newUser);

        return mav;
    }

    @PostMapping("/newUser")
    public String newUser(@ModelAttribute("newUser") User user,
                          @RequestParam(name = "roles", required = false) String...roles) {
        userService.createUser(user, roles);
        return "redirect:/admin";
    }

    @PutMapping("/update{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id,
                         @RequestParam(name = "roles", required = false) String...roles) {
        userService.updateUser(user, id, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete{id}")
    public String delete(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}