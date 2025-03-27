package com.betacom.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.dao.RoleMapper;
import com.betacom.entity.Role;
import com.betacom.services.interfaces.RoleServices;

@Service
public class RoleImplementation implements RoleServices {
	
	@Autowired
	RoleMapper roleM;

	@Override
	public List<Role> getAll() throws Exception {
		List<Role> roleL = roleM.getAll();
		
		if(roleL==null) 
			throw new Exception("Errore durante il recupero dei ruoli");
		
		return roleL;
	}

	@Override
	public Role getById(Integer id) throws Exception {
		Role role = roleM.getById(id);
		
		if(role==null)
			throw new Exception("Nessun ruolo trovato");
		
		return role;
	}
	
	@Override
	public void create(Role role) throws Exception {
		validatRole(role);
		roleM.create(role);		
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		getById(id);
		roleM.delete(id);	
	}
	
	@Override
	public void update(Role role) throws Exception {
		getById(role.getId());
		validatRole(role);
		roleM.update(role);	
	}
	
	//supp
	@Override
	public void addRoles(ModelAndView mav) throws Exception {
        List<Role> roles = roleM.getAll();
        mav.addObject("roles", roles);	
	}
	
	//supp
	private void validatRole(Role r) throws Exception {		
		if(r.getStipendioMin()==null) 
			throw new Exception("ERRORE: il campo stipendio minimo non può essere vuoto");
		
		
		if(r.getDescrizione()==null || r.getDescrizione().isEmpty()) 
			throw new Exception("ERRORE: il campo descrizione non può essere vuoto");		
	}


}
