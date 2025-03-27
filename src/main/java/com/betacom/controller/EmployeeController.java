package com.betacom.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.entity.Employee;
import com.betacom.services.interfaces.CategoryServices;
import com.betacom.services.interfaces.EmployeeServices;
import com.betacom.util.SceltaFiltri;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeServices employeeS;

    @Autowired
    CategoryServices categoryS;
    
    
    @RequestMapping("/showEmployees")
    public ModelAndView index(@RequestParam(name = "error", required = false) String error,
                              @RequestParam(name = "input", required = false) String input,
                              @RequestParam(name = "value", required = false) String value) {

        ModelAndView mav = new ModelAndView("list-employee");
        List<Employee> employees;

        try {
            if (input != null && !input.isEmpty() && value != null && !value.isEmpty()) {
                SceltaFiltri scelta = new SceltaFiltri();
                scelta.setInput(input);
                scelta.setValue(value);
                employees = employeeS.getAllEmployeeByFilter(scelta); 
            } else {
                employees = employeeS.getAll();
            }

            mav.addObject("listEmployees", employees);

        } catch (Exception e) {
            mav.addObject("error", e.getMessage());
        }

        mav.addObject("scelta", new SceltaFiltri());
        return mav;
    }

	
	@RequestMapping("filterEmployee")
	public String filter(@ModelAttribute("scelta") SceltaFiltri scelta) {
		
		return "redirect:/showEmployees?input=" + scelta.getInput() + "&value=" + scelta.getValue();
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
