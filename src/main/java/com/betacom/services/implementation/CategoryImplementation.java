package com.betacom.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.dao.CategoryMapper;
import com.betacom.entity.Category;
import com.betacom.services.interfaces.CategoryServices;

@Service
public class CategoryImplementation implements CategoryServices {
	
	@Autowired
	CategoryMapper categoryM;

	@Override
	public List<Category> getAll() throws Exception {	
		List<Category> categoryL = categoryM.getAll();
		
		if(categoryL==null || categoryL.isEmpty()) 
			throw new Exception("Nessuna categoria trovata");
		
		return categoryL;
	}

	@Override
	public List<Category> getAllByCode(String code) throws Exception {		
		List<Category> categoryByCode = categoryM.getAllByCode(code);
		
		if(categoryByCode==null || categoryByCode.isEmpty()) 
			throw new Exception("Nessuna categoria trovata");
		
		return categoryByCode;
	}

	@Override
	public Category getById(Integer id) throws Exception {
		Category category = categoryM.getById(id);
		
		if(category==null)
			throw new Exception("Nessuna categoria trovata");
		
		return category;
	}
		
	@Override
	public void create(Category category) throws Exception {	
		validateCategory(category);
		categoryM.create(category);		
	}

	@Override
	public void update(Category category) throws Exception {
		getById(category.getId());
		validateCategory(category);
		categoryM.update(category);		
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		getById(id);		
		categoryM.delete(id);	
	}
	
	//supp
	private void validateCategory(Category c) throws Exception {		
		if(c.getCode()==null || c.getCode().trim().isEmpty()) 
			throw new Exception("ERRORE: il campo code non può essere vuoto");
		
		
		if(c.getDescrizione()==null || c.getDescrizione().trim().isEmpty()) 
			throw new Exception("ERRORE: il campo descrizione non può essere vuoto");		
	}
	
    //supp
    public void addCategories(ModelAndView mav) throws Exception {
        List<Category> countries = categoryM.getAllByCode("country");
        List<Category> hobbies = categoryM.getAllByCode("hobbies");

        mav.addObject("countries", countries);
        mav.addObject("hobbies", hobbies);
    }

}
