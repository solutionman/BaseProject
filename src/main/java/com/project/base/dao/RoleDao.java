package com.project.base.dao;

import com.project.base.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleDao extends JpaRepository<Role, Long> {
}
