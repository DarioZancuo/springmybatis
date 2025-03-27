package com.betacom.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.betacom.entity.Tipologia;
import com.betacom.util.MyBatisUtil;

@SuppressWarnings("unchecked")
@Repository
public class TipologiaMapper {
	
	public List<Tipologia> getAll() {	
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Tipologia> tipologiaL = session.selectList("getAllTipologie");	
		session.commit();
		session.close();		
		
		return tipologiaL;
	}
	
	public Tipologia getById(Integer id) {
	    SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
	    Tipologia tipologia = (Tipologia) session.selectOne("getByIdTipologia", id);
	    session.commit();
	    session.close();
	    return tipologia;
	}
	
	//session
	public Tipologia getById(SqlSession session, Integer id) {
	    return (Tipologia) session.selectOne("getByIdTipologia", id);
	}
	
	public void create(Tipologia t) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("insertTipologia", t);	
		session.commit();
		session.close();
	}
	
	public void update(Tipologia t) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(); 
		session.update("updateTipologia", t);        
		session.commit();
		session.close();
	}
	
	public void delete(Integer id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.delete("deleteTipologia", id);		
		session.commit();
		session.close();
	}


}
