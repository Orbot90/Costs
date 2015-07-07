package ru.orbot90;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orbot90.user.Authorization;

/**
 * Created by orbot on 06.07.15.
 */
@Controller
@RequestMapping("/")
public class CostsController {
    @RequestMapping("/")
    public String showLoginPage(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/login*", method = RequestMethod.POST)
    public String loginPage(ModelMap model, @RequestParam(value = "login", required = false)String username,
                            @RequestParam(value = "password",required = false)String password) {
        if("user".equals(username) && ("password".equals(password))) {
            return "success";
        }
        return "login";
    }

    @RequestMapping("/main")
    public String showMain(ModelMap model) {
        return "main";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinProcess(ModelMap model, @RequestParam(value = "login")String username,
                       @RequestParam(value = "password")String password,
                       @RequestParam(value = "balance")String balance) {
        if(Authorization.getInstance().userJoin(username, password, balance)) {
            return "joined";
        } else {
            model.addAttribute("notregistered");
            return "registration";
        }
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(ModelMap model) {
        return "registration";
    }
}
