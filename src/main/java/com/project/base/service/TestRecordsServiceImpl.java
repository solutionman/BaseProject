package com.project.base.service;

import com.project.base.dao.TestRecordsDao;
import com.project.base.model.TestRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestRecordsServiceImpl implements TestRecordsService {

    @Autowired
    public TestRecordsDao testRecordsDao;

    @Override
    public List<TestRecords> findAll() {
        return testRecordsDao.findAll();
    }
}
