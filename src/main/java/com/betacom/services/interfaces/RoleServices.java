package com.betacom.services.interfaces;

import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.entity.Role;

public interface RoleServices {
	
    List<Role> getAll() throws Exception;
    Role getById(Integer id) throws Exception;
    void create(Role role) throws Exception;
    void delete(Integer id) throws Exception;
    void update(Role role) throws Exception;
    
    //supp
    public void addRoles(ModelAndView mav) throws Exception;

}
