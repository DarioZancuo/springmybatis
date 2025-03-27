package com.betacom.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.entity.Role;
import com.betacom.services.interfaces.RoleServices;

@Controller
public class RoleController {
	
	@Autowired
	RoleServices roleS;
	
	@RequestMapping("/showRoles")
	public ModelAndView index() {
	    ModelAndView mav = new ModelAndView("list-roles");

	    try {
	        List<Role> roles = roleS.getAll();
	        mav.addObject("roles", roles);
	    } catch (Exception e) {
	        mav.addObject("error", e.getMessage());
	    }

	    return mav;
	}
	
    @RequestMapping("role/showFormAddRole")
    public ModelAndView showFormAddRole(@RequestParam(name = "error", required = false) String error) throws Exception {
        ModelAndView mav = new ModelAndView("add-role");   
        mav.addObject("role", new Role());
        mav.addObject("error", error);       
        return mav;
    }

    @RequestMapping("role/showFormUpdateRole")
    public ModelAndView showFormUpdateRole(@RequestParam("id") int id) throws Exception {
        ModelAndView mav = new ModelAndView("update-role");        
        Role roleUpdate = roleS.getById(id);
        mav.addObject("role", roleUpdate);        
        return mav;
    }

    @RequestMapping("role/createProcess")
    public ModelAndView createProcess(@ModelAttribute("role") Role role) throws Exception {
    	try {
    		roleS.create(role);
    		return new ModelAndView("redirect:/showRoles");
    	} catch (Exception e) {
    		ModelAndView mav = new ModelAndView("add-role");
    		mav.addObject("role", role);
    		mav.addObject("error", e.getMessage());
    		return mav;
    	}
    }

    @RequestMapping("role/updateProcess")
    public ModelAndView updateProcess(@ModelAttribute("role") Role role) throws Exception {
        try {
            roleS.update(role);
            return new ModelAndView("redirect:/showRoles");
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("update-role");
            mav.addObject("role", role);
            mav.addObject("error", e.getMessage());
            return mav;
        }
    }

    @RequestMapping("role/deleteProcess")
    public ModelAndView deleteProcess(@RequestParam("id") Integer id) {
        try {
            roleS.delete(id);
            return new ModelAndView("redirect:/showRoles");
        } catch (Exception e) {
            return new ModelAndView("redirect:/showRoles?error=" + e.getMessage());
        }
    }

}
