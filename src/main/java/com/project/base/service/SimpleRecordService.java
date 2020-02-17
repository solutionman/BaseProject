package com.project.base.service;

import com.project.base.model.SimpleRecord;

import java.util.List;

public interface SimpleRecordService {
    List<SimpleRecord> findAll();
    SimpleRecord findById(long id);
    void save(SimpleRecord simpleRecord);
    void deleteById(long id);
}
