package com.ca.account.manager.common.repos;

import com.ca.account.manager.common.domain.EmployeeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<EmployeeTask, Long> {
}
