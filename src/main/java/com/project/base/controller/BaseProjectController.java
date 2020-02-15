package com.project.base.controller;

import com.project.base.model.SimpleRecord;
import com.project.base.service.SimpleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class BaseProjectController {

    @Autowired
    private SimpleRecordService simpleRecordService;

    @Value("injected message.")
    String message;

    @RequestMapping("/")
    String index(Model model){
//        String message = "Index page";
        model.addAttribute("message",message);
        return "index";
    }

    @RequestMapping("/welcome")
    String welcome(Model model){
        String welcomePage = "Welcome page";
        model.addAttribute("welcomePage",welcomePage);

        List<SimpleRecord> simpleRecordList = simpleRecordService.findAll();
        SimpleRecord simpleRecord = simpleRecordService.findById(1);
        model.addAttribute("simpleRecord",simpleRecord);

        SimpleRecord newSimpleRecord = new SimpleRecord();
        newSimpleRecord.setRecordName("someName");
        simpleRecordService.save(newSimpleRecord);

        return "welcome";
    }

}
