package com.betacom.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.dao.ContrattoMapper;
import com.betacom.dao.EmployeeMapper;
import com.betacom.entity.Employee;
import com.betacom.services.interfaces.EmployeeServices;
import com.betacom.util.SceltaFiltri;

@Service
public class EmployeeImplementation implements EmployeeServices {
	
	@Autowired
	EmployeeMapper employeeM;
	
	@Autowired
	ContrattoMapper contrattoM;

	@Override
	public List<Employee> getAll() throws Exception {
		List<Employee> employeeL = employeeM.getAll();
		
		if(employeeL==null) 
			throw new Exception("Errore durante il recupero dei dipendenti");
		
		return employeeL;
	}
	
	@Override
	public List<Employee> getAllEmployeeByFilter(SceltaFiltri scelta) throws Exception {
		List<Employee> employeeL = employeeM.getAllByFilter(scelta);
		
		if(employeeL==null)
			throw new Exception("Errore durante il recupero dei dipendenti.");
		
		return employeeL;		
	}

	@Override
	public Employee getById(Integer id) throws Exception {
		Employee employee = employeeM.getById(id);
		
		if(employee==null) 
			throw new Exception("Nessun dipendente trovato");
		
		return employee;
	}
	
	@Override
	public void create(Employee employee) throws Exception {
		validateEmployee(employee);
		employeeM.create(employee);	
	}

	@Override
	public void update(Employee employee) throws Exception {
		getById(employee.getId());	
		validateEmployee(employee);
		employeeM.update(employee);	
	}
	
	@Override
	public void delete(Integer id) throws Exception {	
	    getById(id); 	
		employeeM.delete(id);	
	}
	
	//supp
	private void validateEmployee(Employee e) throws Exception {			
		if(e.getFullname()==null || e.getFullname().trim().isEmpty()) 
			throw new Exception("ERRORE: il campo fullname non può essere vuoto.");
			
		if(e.getEmail()==null || e.getEmail().trim().isEmpty()) 
			throw new Exception("ERRORE: il campo email non può essere vuoto.");
				
		if(e.getGender()==null || e.getGender().trim().isEmpty()) 
			throw new Exception("ERRORE: il campo gender non può essere vuoto.");
				
		if(e.getHobbies()==null || e.getHobbies().trim().isEmpty()) 
			throw new Exception("ERRORE: il campo hobbies non può essere vuoto.");
				
		if(e.getCountry()==null || e.getCountry().trim().isEmpty()) 
			throw new Exception("ERRORE: il campo country non può essere vuoto.");
				
		if(e.getAddress()==null || e.getAddress().trim().isEmpty()) 
			throw new Exception("ERRORE: il campo indirizzo non può essere vuoto.");				
	}


}
