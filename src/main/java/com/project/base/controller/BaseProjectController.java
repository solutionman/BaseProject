package com.project.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseProjectController {

    @RequestMapping("/")
    String welcome(){
        return "welcome page in tomcat";
    }

}
