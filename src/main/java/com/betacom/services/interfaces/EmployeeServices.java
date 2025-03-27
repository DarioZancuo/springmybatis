package com.betacom.services.interfaces;

import java.util.List;
import com.betacom.entity.Employee;
import com.betacom.util.SceltaFiltri;

public interface EmployeeServices {
	
    List<Employee> getAll() throws Exception;
    List<Employee> getAllEmployeeByFilter(SceltaFiltri scelta) throws Exception;
    Employee getById(Integer id) throws Exception;
    void create(Employee employee) throws Exception;
    void update(Employee employee) throws Exception;
    void delete(Integer id) throws Exception;
    
}
