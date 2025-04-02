package com.betacom.controllerrest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.betacom.entity.Employee;
import com.betacom.services.interfaces.EmployeeServices;
import com.betacom.util.SceltaFiltri;

@Controller
@RequestMapping("/rest")
public class EmployeeRest {
	
	@Autowired
	EmployeeServices employeeS;
	
    @RequestMapping("/listEmployees")
    @ResponseBody
    public List<Employee> listEmployees(@RequestParam(name = "input", required = false) String input,
    									@RequestParam(name = "value", required = false) String value) throws Exception {

        if (input != null && !input.isEmpty() && value != null && !value.isEmpty()) {
            SceltaFiltri scelta = new SceltaFiltri();
            scelta.setInput(input);
            scelta.setValue(value);
            return employeeS.getAllEmployeeByFilter(scelta);
        }

        return employeeS.getAll();
    }


}
