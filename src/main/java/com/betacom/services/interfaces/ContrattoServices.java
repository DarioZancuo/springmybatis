package com.betacom.services.interfaces;

import org.apache.ibatis.session.SqlSession;

import com.betacom.entity.Contratto;

public interface ContrattoServices {
	
    Contratto getById(Integer id) throws Exception;
    void createAndAssign(Contratto contratto, Integer idEmployee) throws Exception;
    void deleteLogical(Integer id) throws Exception;
    void update(Contratto contratto) throws Exception;
	void assignContrattoToEmployee(SqlSession session, Integer idContratto, Integer idEmployee) throws Exception;

}
