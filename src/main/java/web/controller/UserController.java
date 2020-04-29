package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.RoleServiceImp;
import web.service.UserServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public RoleServiceImp roleService;

    @GetMapping(path = "list")
    public String listUserGetController(ModelMap model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(path = "read")
    public String readUserGetController(ModelMap model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "user";
    }
}
