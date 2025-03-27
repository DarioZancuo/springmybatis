package com.betacom.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.betacom.entity.Contratto;
import com.betacom.util.MyBatisUtil;

@Repository
public class ContrattoMapper {
	
	public Contratto getById(Integer id) {
	    SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
	    Contratto contratto = (Contratto) session.selectOne("getByIdContratto", id);
	    session.commit();
	    session.close();
	    return contratto;
	}
	
	//session
	public Contratto getById(SqlSession session, Integer id) {
	    return (Contratto) session.selectOne("getByIdContratto", id);
	}
		
	//transaction
	public void create(SqlSession session, Contratto c) {
		session.insert("insertContratto", c);	
	}
	
	//supp
	public void assignContrattoToEmployee(SqlSession session, Integer idContratto, Integer idEmployee) {
		java.util.Map<String, Object> params = new java.util.HashMap<>();
		params.put("idContratto", idContratto);
		params.put("idEmployee", idEmployee);
		session.update("assignContrattoToEmployee", params);
	}
	
	public void update(Contratto c) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(); 
		session.update("updateContratto", c);        
		session.commit();
		session.close();
	}
	
	//transaction
	public void update(SqlSession session, Contratto c) {
	    session.update("updateContratto", c);
	}
	
	public void delete(Contratto id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.delete("deleteContratto", id);		
		session.commit();
		session.close();
	}

}
