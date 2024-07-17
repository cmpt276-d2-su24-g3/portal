package com.bhavjit.cmpt276.portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhavjit.cmpt276.portal.models.UserRepository;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {

        return "home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

/*
    // TODO: Handle index page elsewhere
    @GetMapping("/")
    public String getIndex(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "index";
    }

    @GetMapping("/register")
    public String getRegister(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            return "redirect:/";
        }

        return "register";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam Map<String, String> formData, Model model, HttpServletRequest request,
            HttpSession session) {
        String username = formData.get("username");
        String password = formData.get("password");

        if (!userRepository.findByUsername(username).isEmpty())
            return "redirect:/register";

        User user = new User(username, password);
        userRepository.save(user);

        session.setAttribute("user", user);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam Map<String, String> formData, Model model, HttpServletRequest request,
            HttpSession session) {
        String username = formData.get("username");
        String password = formData.get("password");

        List<User> users = userRepository.findByUsernameAndPassword(username, password);

        if (users.isEmpty())
            return "redirect:/login";

        User user = users.get(0);
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request, HttpSession session) {
        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model, HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/login";
        else if (((User) session.getAttribute("user")).getRole() != User.Role.ADMIN)
            return "redirect:/";

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "admin";
    }
*/
}

