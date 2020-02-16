package com.project.base.controller;

import com.project.base.model.SimpleRecord;
import com.project.base.model.User;
import com.project.base.service.SimpleRecordService;
import com.project.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class BaseProjectController {

    @Autowired
    private SimpleRecordService simpleRecordService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String getLogin(Model model){

        String debug = "debug";

        return "secured";
    }

    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    String secured(Model model){
        String debug = "debug";

        return "secured";
    }

    @Value("injected message.")
    String message;

    @RequestMapping("/")
    String index(Model model){
//        String message = "Index page";
        model.addAttribute("message",message);

//        User user = userService.findByUsername("user");

//        User newUser = new User();
//        newUser.setPassword("123");
//        newUser.setUsername("user");
//        userService.save(newUser);

//        User anotherUser = new User();
//        anotherUser.setUsername("Operator");
//        anotherUser.setPassword("12345678");
//        userService.save(anotherUser);

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
