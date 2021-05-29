package com.egg.library.controllers;

import com.egg.library.models.Author;
import com.egg.library.models.MyUser;
import com.egg.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new MyUser());
        return mav;
    }

    @GetMapping("register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new MyUser());
        return mav;
    }

    @PostMapping("register")
    public RedirectView register(@RequestParam String username, @RequestParam String password) {
        userService.createUser(username, password);
        return new RedirectView("/login");
    }


}
