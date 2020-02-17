package com.project.base.service;

import com.project.base.dao.SimpleRecordDao;
import com.project.base.model.SimpleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleRecordServiceImpl implements SimpleRecordService {

    @Autowired
    public SimpleRecordDao simpleRecordDao;

    @Override
    public List<SimpleRecord> findAll() {
        return simpleRecordDao.findAll();
    }

    @Override
    public SimpleRecord findById(long id) {
        return simpleRecordDao.findById(id);
    }

    @Override
    public void save(SimpleRecord simpleRecord) {
        simpleRecordDao.save(simpleRecord);
    }

    @Override
    public SimpleRecord deleteById(long id) {
        return simpleRecordDao.deleteById(id);
    }
}
