package com.betacom.controllerrest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.betacom.entity.Role;
import com.betacom.services.interfaces.RoleServices;

@Controller
@RequestMapping("/rest")
public class RoleRest {
	
	@Autowired
	RoleServices roleS;
	
    @RequestMapping("/listRoles")
    @ResponseBody
	public List<Role> listRoles() throws Exception{
		return roleS.getAll();
	}

}
