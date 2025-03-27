package com.betacom.controller;

import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.entity.Contratto;
import com.betacom.services.interfaces.ContrattoServices;
import com.betacom.services.interfaces.EmployeeServices;
import com.betacom.services.interfaces.RoleServices;
import com.betacom.services.interfaces.TipologiaServices;

@Controller
public class ContrattoController {

	@Autowired
	ContrattoServices contrattoS;

	@Autowired
	EmployeeServices employeeS;

	@Autowired
	RoleServices roleS;

	@Autowired
	TipologiaServices tipologiaS;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@RequestMapping("/showContratto")
	public ModelAndView showContratto(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView("contratto");

		try {
			Contratto contratto = contrattoS.getById(id);
			mav.addObject("contratto", contratto);
		} catch (Exception e) {
			mav.addObject("error", e.getMessage());
		}

		return mav;
	}

    @RequestMapping("contratto/showFormAddContratto")
    public ModelAndView showFormAddContratto(@RequestParam("idEmployee") Integer idEmployee,
                                             @RequestParam(name = "error", required = false) String error) throws Exception {
    	
        ModelAndView mav = new ModelAndView("add-contratto");     
        mav.addObject("contratto", new Contratto());
        mav.addObject("idEmployee", idEmployee);
        mav.addObject("error", error);
        roleS.addRoles(mav);
        tipologiaS.addTipologie(mav);
        return mav;
    }
	
    @RequestMapping("contratto/showFormUpdateContratto")
    public ModelAndView showFormUpdateContratto(@RequestParam("id") Integer idContratto) throws Exception {
    	
        ModelAndView mav = new ModelAndView("update-contratto");
        Contratto contratto = contrattoS.getById(idContratto);
        mav.addObject("contratto", contratto);
        roleS.addRoles(mav);
        tipologiaS.addTipologie(mav);
        return mav;
    }

    @RequestMapping("contratto/createProcess")
    public ModelAndView createProcess(@ModelAttribute("contratto") Contratto contratto,
                                      @RequestParam("idEmployee") Integer idEmployee) throws Exception {
        try {
            contrattoS.createAndAssign(contratto, idEmployee);
            return new ModelAndView("redirect:/showContratto?id=" + contratto.getId());
            
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("add-contratto");
            mav.addObject("contratto", contratto);
            mav.addObject("error", e.getMessage());
            roleS.addRoles(mav);
            tipologiaS.addTipologie(mav);
            mav.addObject("idEmployee", idEmployee); 
            return mav;
        }
    }

	
	@RequestMapping("contratto/updateProcess")
	public ModelAndView updateProcess(@ModelAttribute("contratto") Contratto contratto) throws Exception {
		
	    try {
	        contrattoS.update(contratto); 
	        return new ModelAndView("redirect:/showContratto?id=" + contratto.getId());
	        
	    } catch (Exception e) {
	        ModelAndView mav = new ModelAndView("update-contratto");
	        mav.addObject("contratto", contratto);
	        mav.addObject("error", e.getMessage());
	        roleS.addRoles(mav);
	        tipologiaS.addTipologie(mav);
	        return mav;
	    }
	}
	
	@RequestMapping("contratto/deleteLogicalProcess")
	public ModelAndView deleteLogicalProcess(@RequestParam("id") Integer id) throws Exception {
		
	    try {
	        contrattoS.deleteLogical(id);
	        return new ModelAndView("redirect:/showContratto?id=" + id);

	    } catch (Exception e) {
	        ModelAndView mav = new ModelAndView("contratto");
	        mav.addObject("error", e.getMessage());
	        return mav;
	    }
	}

}
