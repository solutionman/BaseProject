package com.project.base.controller;

import com.project.base.helpers.Company;
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
import java.util.stream.Collectors;

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
    public Map<String, Object> testRecords(@RequestParam Map<String, Object> requestParams){

        String length = requestParams.get("length").toString();
        String start = requestParams.get("start").toString();
        String search = requestParams.get("search[value]").toString();
        String orderColumn = requestParams.get("order[0][column]").toString();
        String orderDir = requestParams.get("order[0][dir]").toString();
        List<TestRecords> testRecords = testRecordsService.findAll();
        testRecords = sortByFieldName( testRecords, orderColumn, orderDir );
        Map<String, Object> result = new HashMap<>();
        result.put( "recordsTotal", testRecords.size() );
        if(search==null || search.equals("")){
            result.put("recordsFiltered", testRecords.size());
            testRecords.subList(0, Integer.parseInt(start)).clear();
            if( testRecords.size() > Integer.parseInt(length) ) {
                testRecords.subList(Integer.parseInt(length), testRecords.size()).clear();
            }
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
            if( searchResult.size() > Integer.parseInt(length) ){
                searchResult.subList(Integer.parseInt(length), searchResult.size()).clear();
            }
            result.put("data", searchResult);
        }
        int draw = Integer.parseInt(requestParams.get("draw").toString());
        result.put( "draw", draw );
        return result;
    }

    private List<TestRecords> sortByFieldName(List<TestRecords> list, String fieldName, String direction) {

        return list.stream()
                .sorted((first, second) -> {
                    try {
                        String a;
                        String b;
                        if( fieldName.equals("0") ){
                            a = first.getUsername();
                            b = second.getUsername();
                        } else if( fieldName.equals("1")  ){
                            a = first.getFirstname();
                            b = second.getFirstname();
                        } else {
                            a = first.getLastname();
                            b = second.getLastname();
                        }

                        if(direction.equals("asc") ){
                            assert a != null;
                            assert b != null;
                            return a.compareTo(b);
                        } else {
                            assert b != null;
                            assert a != null;
                            return b.compareTo(a);
                        }
                    } catch ( Exception e ) {
                        throw new RuntimeException("Error", e);
                    }
                })
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/getcompany", method = RequestMethod.GET)
    public Map< String, Object > getcompany(@RequestParam Map<String, String> data) {

        Map<String, Object> result = new HashMap<>();

        Integer draw = Integer.parseInt(data.get("draw"));

        ArrayList<Object> youObjectsSelected = new ArrayList<>(); // put your selected objects here
        int start = Integer.parseInt(data.get("start"));
        int length = Integer.parseInt(data.get("length"));
        for( int j = 0; j < 50; j++ ){
            Company company = new Company();
            company.setId(j);
            company.setName("someName");
            company.setShortname("shortName");
            youObjectsSelected.add(company);
        }
        ArrayList<Object> yourSObjectsForPage = new ArrayList<>(); // get only objects for displaying on page
        for( int k = start; k < start + length; k++ ){
            yourSObjectsForPage.add( youObjectsSelected.get(k) );
        }

        result.put("draw", draw);
        result.put("recordsTotal", 100);
        result.put("recordsFiltered", youObjectsSelected.size());
        result.put("data", yourSObjectsForPage);

        return result;
    }
}
