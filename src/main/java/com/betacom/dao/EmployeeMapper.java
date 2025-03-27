package com.betacom.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.betacom.entity.Employee;
import com.betacom.util.MyBatisUtil;
import com.betacom.util.SceltaFiltri;

@SuppressWarnings("unchecked")
@Repository
public class EmployeeMapper {
	
	public List<Employee> getAll() {	
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Employee> employeeList = session.selectList("getAllEmployees");	
		session.commit();
		session.close();				
		return employeeList;
	}
	
	public Employee getById(Integer id) {
	    SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
	    Employee employee = (Employee) session.selectOne("getByIdEmployee", id);
	    session.commit();
	    session.close();
	    return employee;
	}
	
	//session
	public Employee getById(SqlSession session, Integer id) {
	    return (Employee) session.selectOne("getByIdEmployee", id);
	}
	
	public List<Employee> getAllByFilter(SceltaFiltri scelta) {	
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();	
		List<Employee> employeeListByFilter = session.selectList("getEmployeeByFilter", scelta);	
		session.commit();
		session.close();
		
		return employeeListByFilter;
	}
	
	public void create(Employee e) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("insertEmployee", e);	
		session.commit();
		session.close();
	}
	
	public void update(Employee e) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(); 
		session.update("updateEmployee", e);        
		session.commit();
		session.close();
	}
	
	public void delete(Integer id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.delete("deleteEmployee", id);		
		session.commit();
		session.close();
	}
		
}
