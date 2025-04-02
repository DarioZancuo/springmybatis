package com.betacom.controllerrest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.betacom.entity.Category;
import com.betacom.services.interfaces.CategoryServices;

@Controller
@RequestMapping("/rest")
public class CategoryRest {

    @Autowired
    CategoryServices categoryS;

    @RequestMapping("/listCategories")
    @ResponseBody
    public List<Category> listByCode(@RequestParam("code") String code) throws Exception {
        return categoryS.getAllByCode(code);
    }
    
}
