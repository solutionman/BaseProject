package com.project.base.dao;

import com.project.base.model.TestRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRecordsDao extends JpaRepository<TestRecords, Long> {
    List<TestRecords> findAll();
}
