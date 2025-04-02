package com.betacom.services.implementation;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.dao.ContrattoMapper;
import com.betacom.dao.EmployeeMapper;
import com.betacom.dao.RoleMapper;
import com.betacom.dao.TipologiaMapper;
import com.betacom.entity.Contratto;
import com.betacom.entity.Role;
import com.betacom.entity.Tipologia;
import com.betacom.services.interfaces.ContrattoServices;
import com.betacom.util.MyBatisUtil;

@Service
public class ContrattoImplementation implements ContrattoServices{
	
	@Autowired
	ContrattoMapper contrattoM;
	
	@Autowired
	EmployeeMapper employeeM;
	
	@Autowired
	RoleMapper roleM;
	
	@Autowired
	TipologiaMapper tipologiaM;
	
	@Override
	public Contratto getById(Integer id) throws Exception {
		Contratto contratto = contrattoM.getById(id);
		
		if(contratto==null)
			throw new Exception("Nessun contratto trovato");
		
		return contratto;
	}	
	
	//transaction
	@Override
	public void createAndAssign(Contratto contratto, Integer idEmployee) throws Exception {
	    SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(false);

	    try {
	        Role role = roleM.getById(session, contratto.getRole().getId());
	        Tipologia tipologia = tipologiaM.getById(session, contratto.getTipologia().getId());
	        contratto.setRole(role);
	        contratto.setTipologia(tipologia);
	        validateContratto(contratto);
	        contrattoM.create(session, contratto);
	        contrattoM.assignContrattoToEmployee(session, contratto.getId(), idEmployee);
	        session.commit();
	        
	    } catch (Exception e) {
	    	System.out.println("Errore nella creazione del contratto: " + e.getMessage());
	        session.rollback();
	        throw e;
	    } finally {
	        session.close();
	    }
	}

	//session supp
	@Override
	public void assignContrattoToEmployee(SqlSession session, Integer idContratto, Integer idEmployee) throws Exception {
		employeeM.getById(session, idEmployee);
		contrattoM.assignContrattoToEmployee(session, idContratto, idEmployee);
	}

	@Override
	public void deleteLogical(Integer id) throws Exception {
		Contratto contratto = contrattoM.getById(id);	    
	    contratto.setStatus(false);
	    contratto.setDataDimissione(new java.sql.Date(System.currentTimeMillis()));
	    contrattoM.update(contratto);
	}

	@Override
	public void update(Contratto contratto) throws Exception {
	    SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(false);
	    
	    try {
	        Role role = roleM.getById(session, contratto.getRole().getId());
	        Tipologia tipologia = tipologiaM.getById(session, contratto.getTipologia().getId());
	        contratto.setRole(role);
	        contratto.setTipologia(tipologia);
	        contrattoM.getById(session, contratto.getId());
	        validateContratto(contratto);
	        contrattoM.update(session, contratto);
	        session.commit();
	        
	    } catch (Exception e) {
	    	System.out.println("errore nell'update del contratto: " + e.getMessage());
	        session.rollback();
	        throw e;
	    } finally {
	        session.close();
	    }
	}
	
	//supp
	private void validateContratto(Contratto c) throws Exception {
		
	    if (c.getDataAssunzione() == null) 
	        throw new Exception("ERRORE: Il campo 'Data Assunzione' non può essere vuoto.");	    

	    if (c.getStipendio() == null || c.getStipendio().isNaN()) 
	        throw new Exception("ERRORE: Il campo 'Stipendio' non può essere vuoto o non valido.");	
	    
	    //stipendioMin
	    Role r = c.getRole();
	    if(c.getStipendio() < r.getStipendioMin())
	        throw new Exception("ERRORE: Lo stipendio non può essere inferiore al minimo previsto per il ruolo: " + r.getStipendioMin() + " €.");

	    if (c.getRole() == null || c.getRole().getId() == null) 
	        throw new Exception("ERRORE: Devi selezionare un ruolo valido.");	    

	    if (c.getTipologia() == null || c.getTipologia().getId() == null) 
	        throw new Exception("ERRORE: Devi selezionare una categoria valida.");    

	    if (c.getStatus() == null) 
	        throw new Exception("ERRORE: Devi specificare lo stato del contratto.");	   
	}
	
}
