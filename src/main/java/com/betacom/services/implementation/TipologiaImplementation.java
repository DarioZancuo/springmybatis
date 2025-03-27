package com.betacom.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.betacom.dao.TipologiaMapper;
import com.betacom.entity.Tipologia;
import com.betacom.services.interfaces.TipologiaServices;

@Service
public class TipologiaImplementation implements TipologiaServices {
	
	@Autowired
	TipologiaMapper tipologiaM;
	
	@Override
	public List<Tipologia> getAll() throws Exception {
		List<Tipologia> tipologiaL = tipologiaM.getAll();
		
		if(tipologiaL==null) 
			throw new Exception("Errore durante il recupero delle tipologie");
		
		return tipologiaL;
	}

	@Override
	public Tipologia getById(Integer id) throws Exception {
		Tipologia tipologia = tipologiaM.getById(id);
		
		if(tipologia==null)
			throw new Exception("Nessuna tipologia trovata");
		
		return tipologia;
	}
	
	//supp
	@Override
	public void addTipologie(ModelAndView mav) throws Exception {
        List<Tipologia> tipologie = tipologiaM.getAll();
        mav.addObject("tipologie", tipologie);
		
	}

}
