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

import java.util.ArrayList;
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

        Map<String, Object> result = new HashMap<>();
        List<TestRecords> testRecords = testRecordsService.findAll();
        String length = requestParams.get("length");
        String start = requestParams.get("start");
        String search = requestParams.get("search[value]");
        if(search==null || search.equals("")){
            result.put("recordsFiltered", testRecords.size());
            testRecords.subList(0, Integer.parseInt(start)).clear();
            testRecords.subList(Integer.parseInt(length), testRecords.size()).clear();
            result.put("data", testRecords);
        }else {
            List<TestRecords> searchResult = new ArrayList<>();
            for ( TestRecords tr : testRecords ){
                if( tr.getFirstname().contains(search) || tr.getLastname().contains(search) || tr.getUsername().contains(search) ){
                    searchResult.add(tr);
                }
            }
            result.put( "recordsFiltered", searchResult.size() );
            searchResult.subList(0, Integer.parseInt(start)).clear();
            searchResult.subList(Integer.parseInt(length), searchResult.size()).clear();
            result.put("data", searchResult);
        }
        int draw = Integer.parseInt(requestParams.get("draw"));
        result.put( "draw", draw );
        result.put( "recordsTotal", testRecords.size() );
        return result;
    }

}
