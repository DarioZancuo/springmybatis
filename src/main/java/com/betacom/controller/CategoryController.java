package com.betacom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.entity.Category;
import com.betacom.services.interfaces.CategoryServices;

@Controller
public class CategoryController {
	
    @Autowired
    CategoryServices categoryS;
    
    @RequestMapping("/showCategories")
    public String showCategories() {
        return "list-categories-ajax";
    }
	
	@RequestMapping("category/showFormAddCategory")
	public ModelAndView showFormAddCategory(
	        @RequestParam("code") String code,
	        @RequestParam(name = "error", required = false) String error) {

	    ModelAndView mav = new ModelAndView("add-category");
	    Category newCategory = new Category();
	    newCategory.setCode(code);
	    mav.addObject("category", newCategory);
	    mav.addObject("error", error);
	    return mav;
	}

	@RequestMapping("category/showFormUpdateCategory")
	public ModelAndView showFormUpdateCategory(@RequestParam("id") int id) throws Exception {  	
		ModelAndView mav = new ModelAndView("update-category");        
        Category categoryUpdate = categoryS.getById(id);
        mav.addObject("category", categoryUpdate);
        categoryS.addCategories(mav);		
		return mav;
	}

	@RequestMapping("category/createProcess")
	public ModelAndView createProcess(@ModelAttribute("category") Category category) throws Exception {
		try {
			categoryS.create(category);
    		return new ModelAndView("redirect:/showCategories?code=" + category.getCode());
		} catch (Exception e) {
    		ModelAndView mav = new ModelAndView("add-category");
    		mav.addObject("category", category);
    		mav.addObject("error", e.getMessage());
    		categoryS.addCategories(mav);
    		return mav;
		}      	
	}

	@RequestMapping("category/updateProcess")
	public ModelAndView updateProcess(@ModelAttribute("category") Category category) throws Exception {
		try {
			categoryS.update(category);
    		return new ModelAndView("redirect:/showCategories?code=" + category.getCode());
		} catch (Exception e) {
    		ModelAndView mav = new ModelAndView("update-category");
    		mav.addObject("category", category);
    		mav.addObject("error", e.getMessage());
    		categoryS.addCategories(mav);
    		return mav;
		} 
	}
	
	@RequestMapping("category/deleteProcess")
	public ModelAndView deleteProcess(@RequestParam("id") Integer id, @RequestParam("code") String code) {
		try {
			categoryS.delete(id);
			return new ModelAndView("redirect:/showCategories?code=" + code);
		} catch (Exception e) {
            return new ModelAndView("redirect:/showCategories?error=" + e.getMessage());
		}	    		
	}

}
