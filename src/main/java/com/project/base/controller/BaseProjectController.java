package com.project.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BaseProjectController {

    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping("/welcome")
    String welcome(){
        return "welcome";
    }

}
