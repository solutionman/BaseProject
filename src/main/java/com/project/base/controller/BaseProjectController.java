package com.project.base.controller;

import com.project.base.model.SimpleRecord;
import com.project.base.model.User;
import com.project.base.service.SimpleRecordService;
import com.project.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BaseProjectController {

    @Autowired
    private SimpleRecordService simpleRecordService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String currentUser = authentication.getName();
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String getLogin(Model model, String error, String logout){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String currentUser = authentication.getName();

        String debug = "debug";
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    String secured(Model model){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String currentUser = authentication.getName();

        String debug = "debug";

        return "secured";
    }

    @GetMapping("/registration")
     String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
     }

     @PostMapping("/registration")
     String createUser(@ModelAttribute User user){

        if( null == userService.findByUsername(user.getUsername())){
            userService.save(user);
            return "/secured";
        }
        return "/registration";
     }

    @Value("injected message.")
    String message;

    @RequestMapping("/")
    String index(Model model){
//        String message = "Index page";
        model.addAttribute("message",message);

        User user = userService.findByUsername("user");
        List<User> userList = userService.findAll();
        List<User> users = userService.findUsersByUsertype("admin");

        return "index";
    }

    @RequestMapping("/welcome")
    String welcome(Model model){
        String welcomePage = "Welcome page";
        model.addAttribute("welcomePage",welcomePage);

        SimpleRecord newSimpleRecord = new SimpleRecord();
        newSimpleRecord.setRecordName("someName inserted");
        newSimpleRecord.setShortName("some shortName inserted");
        simpleRecordService.save(newSimpleRecord);

        List<SimpleRecord> simpleRecordList = simpleRecordService.findAll();
        model.addAttribute("simpleRecordList", simpleRecordList);

        SimpleRecord simpleRecord = simpleRecordService.findById(1);
        model.addAttribute("simpleRecord",simpleRecord);

        return "welcome";
    }

}
