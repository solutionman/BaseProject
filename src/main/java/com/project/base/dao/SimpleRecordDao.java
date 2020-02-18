package com.project.base.dao;

import com.project.base.model.SimpleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SimpleRecordDao extends JpaRepository<SimpleRecord, Long> {
    List<SimpleRecord> findAll();
    SimpleRecord findById(long id);
    void deleteById(long id);
}
