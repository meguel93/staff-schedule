package com.staff.schedule.app.staffschedule.dao;

import com.staff.schedule.app.staffschedule.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
