package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserServiсe;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MyController {

    @Autowired
    private UserServiсe UserServiceImpl;

    @GetMapping(value = "/list")
    public List<User> listContact() {
        return UserServiceImpl.getAllUsers();
    }

    @PostMapping(value = "/admin/delete/{id}")
    public String deleteContact(@PathVariable("id") Integer id) {
        UserServiceImpl.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping(value = "/admin/add")
    public String addUser() {
        return "add";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute User user) {
        UserServiceImpl.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public User editUser(@PathVariable("id") Integer id) {
        return UserServiceImpl.getUserById(id);
    }

    @PutMapping(value = "/admin/edit")
    public void editUser(@RequestBody User user) {
        //user.setId(id);
        UserServiceImpl.updateUser(user);

    }

    @GetMapping(value = "/userprofile")
    public String readUserGetController(ModelMap model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "userProfile";
    }

}
