package com.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.LoginUserDetails;
import com.example.service.LoginUserDetailsService;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    LoginUserDetailsService userService;
    
    @ModelAttribute
    UserForm setUpForm() {
        return new UserForm();
    }
    
    @GetMapping
    String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }
    
    @PostMapping(path = "create")
    String create(@Validated UserForm form, BindingResult result, Model model,
                  @AuthenticationPrincipal LoginUserDetails userDetails) {
        if (result.hasErrors()) {
            return list(model);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        userService.create(user);
        return "redirect:/users";
    }
    
    @GetMapping(params = "goToTop")
    String goToTop() {
        return "redirect:/customers";
    }
}
