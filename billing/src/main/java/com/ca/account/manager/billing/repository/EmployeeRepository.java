package com.ca.account.manager.billing.repository;



import com.ca.account.manager.billing.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}