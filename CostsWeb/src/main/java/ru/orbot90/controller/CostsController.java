package ru.orbot90.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.orbot90.model.CostRepository;
import ru.orbot90.model.UserRepository;
import ru.orbot90.model.UserService;
import ru.orbot90.record.Cost;
import ru.orbot90.user.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by orbot on 06.07.15.
 */
@Controller
@RequestMapping("/")
public class CostsController {
    private static final Logger LOG = LoggerFactory.getLogger(CostsController.class);

    @Autowired
    private UserRepository accountRepository;
    @Autowired
    private CostRepository costRepository;
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
        User user = null;
        if(principal != null) {
            userName = principal.getName();
            user = accountRepository.getUserByUserName(userName);
        }
        mv.addObject("username", userName);
        mv.addObject("balance", user.getBalance());
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

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public ModelAndView addCost(HttpServletRequest req, @RequestParam(value = "value")Double value,
                                @RequestParam(value = "description")String desc, @RequestParam(value = "tags")String tags,
                                @RequestParam(value = "type")Integer type, @RequestParam(value = "date")String date) {
        ModelAndView mv = new ModelAndView("redirect:/main");
        Principal principal = req.getUserPrincipal();
        User user = accountRepository.getUserByUserName(principal.getName());
        List<String> tagsList = Arrays.asList(tags.split(", "));
        try {
            Date costDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Cost cost = new Cost(value, desc, type == 0, tagsList, user);
            cost.setDate(costDate);
            costRepository.save(cost);
        } catch (ParseException e) {
            LOG.info("Parse error: " + e.getMessage());
            mv.addObject("parseerror", 1);
        }
        return mv;
    }

    @RequestMapping(value = "/history")
    public ModelAndView showHistory(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("history");
        User user = accountRepository.getUserByUserName(req.getUserPrincipal().getName());
        List<Cost> costList = user.getCosts();
        mv.addObject("costlist", costList);
        double sum = costList
                .stream()
                .filter(cost -> cost.isCost())
                .map(cost -> cost.getValue())
                .reduce(0d, (acc, elem) -> acc + elem);
        mv.addObject("costsum", sum);
        return mv;
    }

    @RequestMapping(value="/history/{beginDate}/{finishDate}")
    public ModelAndView showTimedHistory(@PathVariable String beginDate, @PathVariable String finishDate,HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("history");
        User user = accountRepository.getUserByUserName(req.getUserPrincipal().getName());
        List<Cost> costList = user.getCosts();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date begin = dateFormat.parse(beginDate);
            Date finish = dateFormat.parse(finishDate);
            costList = costList
                    .stream()
                    .filter(cost -> cost.getDate().getTime() > begin.getTime() && cost.getDate().getTime() < finish.getTime())
                    .collect(Collectors.toList());
        } catch (ParseException e) {
            LOG.info("Parse error: " + e.getMessage());
        }
        double sum = costList
                .stream()
                .filter(cost -> cost.isCost())
                .map(cost -> cost.getValue())
                .reduce(0d, (acc, elem) -> acc + elem);
        mv.addObject("costlist", costList);
        mv.addObject("begin", beginDate);
        mv.addObject("finish", finishDate);
        mv.addObject("costsum", sum);
        return mv;
    }
}
