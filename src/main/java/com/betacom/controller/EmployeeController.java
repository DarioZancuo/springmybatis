package com.betacom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.entity.Employee;
import com.betacom.services.interfaces.CategoryServices;
import com.betacom.services.interfaces.EmployeeServices;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeServices employeeS;

    @Autowired
    CategoryServices categoryS;
    
    
    @RequestMapping("/showEmployees")
    public String showEmployees() {
        return "list-employee-ajax";
    }

    @RequestMapping("employee/showFormAddEmployee")
    public ModelAndView showFormAddEmployee(@RequestParam(name = "error", required = false) String error) throws Exception {
        ModelAndView mav = new ModelAndView("add-employee");
        
        mav.addObject("employee", new Employee());
        categoryS.addCategories(mav);
        mav.addObject("error", error);
        
        return mav;
    }

    @RequestMapping("employee/showFormUpdateEmployee")
    public ModelAndView showFormUpdateEmployee(@RequestParam("id") int id) throws Exception {
        ModelAndView mav = new ModelAndView("update-employee");
        
        Employee employeeUpdate = employeeS.getById(id);
        mav.addObject("employee", employeeUpdate);
        categoryS.addCategories(mav);
        
        return mav;
    }

    @RequestMapping("employee/createProcess")
    public ModelAndView createProcess(@ModelAttribute("employee") Employee employee) throws Exception {
    	try {
    		employeeS.create(employee);
    		return new ModelAndView("redirect:/showEmployees");
    	} catch (Exception e) {
    		ModelAndView mav = new ModelAndView("add-employee");
    		mav.addObject("employee", employee);
    		mav.addObject("error", e.getMessage());
    		categoryS.addCategories(mav);
    		return mav;
    	}
    }
    
    @RequestMapping("employee/updateProcess")
    public ModelAndView updateProcess(@ModelAttribute("employee") Employee employee) throws Exception {
        try {
            employeeS.update(employee);
            return new ModelAndView("redirect:/showEmployees");
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("update-employee");
            mav.addObject("employee", employee);
            mav.addObject("error", e.getMessage());
            categoryS.addCategories(mav);
            return mav;
        }
    }

    @RequestMapping("employee/deleteProcess")
    public ModelAndView deleteProcess(@RequestParam("id") Integer id) {
        try {
            employeeS.delete(id);
            return new ModelAndView("redirect:/showEmployees");
        } catch (Exception e) {
            return new ModelAndView("redirect:/showEmployees?error=" + e.getMessage());
        }
    }

}
