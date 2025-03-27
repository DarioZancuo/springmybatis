package com.betacom.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.betacom.entity.Role;
import com.betacom.util.MyBatisUtil;

@SuppressWarnings("unchecked")
@Repository
public class RoleMapper {
	
	public List<Role> getAll() {	
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Role> roleL = session.selectList("getAllRoles");	
		session.commit();
		session.close();			
		return roleL;
	}
	
	public Role getById(Integer id) {
	    SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
	    Role role = (Role) session.selectOne("getByIdRole", id);
	    session.commit();
	    session.close();
	    return role;
	}
	
	//session
	public Role getById(SqlSession session, Integer id) {
	    return (Role) session.selectOne("getByIdRole", id);
	}
	
	public void create(Role r) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("insertRole", r);	
		session.commit();
		session.close();
	}
	
	public void update(Role r) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(); 
		session.update("updateRole", r);        
		session.commit();
		session.close();
	}
	
	public void delete(Integer id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.delete("deleteRole", id);		
		session.commit();
		session.close();
	}

}
