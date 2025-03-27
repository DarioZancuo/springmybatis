package com.betacom.services.interfaces;

import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.entity.Tipologia;

public interface TipologiaServices {
	
    List<Tipologia> getAll() throws Exception;
    Tipologia getById(Integer id) throws Exception;
    
    //supp
    public void addTipologie(ModelAndView mav) throws Exception;

}
