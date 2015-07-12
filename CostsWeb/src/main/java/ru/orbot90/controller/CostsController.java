package ru.orbot90.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.orbot90.model.UserRepository;
import ru.orbot90.model.UserService;
import ru.orbot90.user.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by orbot on 06.07.15.
 */
@Controller
@RequestMapping("/")
public class CostsController {

    @Autowired
    private UserRepository accountRepository;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String showLoginPage(ModelMap model) {
        return "login";
    }

    @RequestMapping({"/main", "/"})
    public ModelAndView showMain(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("main");
        Principal principal = req.getUserPrincipal();
        String userName = "Not logged in";
        if(principal != null) {
            userName = principal.getName();
        }
        mv.addObject("username", userName);
        return mv;
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(ModelMap model) {
        return "registration";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String register(ModelMap model, @RequestParam(value="login", required = true)String userName,
                           @RequestParam(value="password", required = true)String password,
                           @RequestParam(value="balance")String balance, @RequestParam(value = "email")String email) {
        User user = new User(userName, password, balance, email);
        accountRepository.save(user);
        userService.signIn(user);
        return "joined";
    }

    @RequestMapping(value = "/test")
    public String testEnter(ModelMap model) {
        User user = accountRepository.getUserByUserName("test");
        userService.signIn(user);
        return "success";
    }
}
