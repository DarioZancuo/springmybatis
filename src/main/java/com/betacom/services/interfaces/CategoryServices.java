package com.betacom.services.interfaces;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.betacom.entity.Category;

public interface CategoryServices {
	
    List<Category> getAll() throws Exception;
    List<Category> getAllByCode(String code) throws Exception;
    Category getById(Integer id) throws Exception;
    void create(Category category) throws Exception;
    void delete(Integer id) throws Exception;
    void update(Category category) throws Exception;
    
    //supp
    public void addCategories(ModelAndView mav) throws Exception;
    
}
