package com.betacom.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.betacom.entity.Category;
import com.betacom.util.MyBatisUtil;

@SuppressWarnings("unchecked")
@Repository
public class CategoryMapper {
	
	public List<Category> getAll() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Category> categoryList = session.selectList("getAllCategories");
		session.commit();
		session.close();	
		return categoryList;
	}
	
	public List<Category> getAllByCode(String code) {	
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();	
		List<Category> categoryListByCode = session.selectList("getAllByCode", code);	
		session.commit();
		session.close();	
		return categoryListByCode;
	}
		
	public Category getById(Integer id) {
	    SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
	    Category category = (Category) session.selectOne("getByIdCategory", id);
	    session.commit();
	    session.close();
	    return category;
	}
	
	public void create(Category c) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("insertCategory", c);	
		session.commit();
		session.close();
	}
	
	public void update(Category c) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(); 
		session.update("updateCategory", c);        
		session.commit();
		session.close();
	}
	
	public void delete(Integer id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.delete("deleteCategory", id);		
		session.commit();
		session.close();
	}
	
}
