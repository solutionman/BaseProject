package com.project.base.controller;

import com.project.base.model.SimpleRecord;
import com.project.base.model.TestRecords;
import com.project.base.service.SimpleRecordService;
import com.project.base.service.TestRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BaseProjectRestController {

    @Autowired
    private SimpleRecordService simpleRecordService;

    @Autowired
    private TestRecordsService testRecordsService;

    @RequestMapping(path = "simpleRecords", method = RequestMethod.GET)
    public List<SimpleRecord> showAllRecords(){
        String debug = "";
        List<SimpleRecord> simpleRecords = simpleRecordService.findAll();
        return simpleRecords;
    }

//    @RequestMapping(path = "testRecords", method = RequestMethod.GET)
//    public List<TestRecords> testRecords(){
//        String debug = "";
//        List<TestRecords> testRecords = testRecordsService.findAll();
//        return testRecords;
//    }

    @RequestMapping(path = "testRecords", method = RequestMethod.GET)
    public Map<String, Object> testRecords(@RequestParam Map<String, String> requestParams){
        String debug = "";
        Map<String, Object> result = new HashMap<>();
        result.put("draw", 10);
        result.put("recordsTotal", 1000);
        result.put("recordsFiltered", 1000);
        List<TestRecords> testRecords = testRecordsService.findAll();
        result.put("data", testRecords);

        return result;
    }

}
