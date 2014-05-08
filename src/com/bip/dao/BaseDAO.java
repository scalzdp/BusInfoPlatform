package com.bip.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class BaseDAO implements IBaseDAO {
	
	@Resource 
	private SessionFactory sessionFactory;

	public <T> void save(T t) {
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		this.sessionFactory.getCurrentSession().save(t);
		tx.commit();
	}

	public <T> T get(T t, int id) {
		return (T)this.sessionFactory.getCurrentSession().get(t.getClass(), id);
	}

	public <T> List<T> querySelf(T t, String hql) {
		return this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(t.getClass()).list();
	}

	public <T> void delete(T t, int id) {
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		T tmp = this.get(t, id);
		this.sessionFactory.getCurrentSession().delete(tmp);
		tx.commit();
	}

	public <T> void update(T t) {
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		this.sessionFactory.getCurrentSession().update(t);		
		tx.commit();
	}

	public <T> int queryEmailRepeatTimes(T t, String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> List<T> getAllSelf(T t, String tablename) {
		String hql = "select * from "+tablename;
		//List<SecurityQuestions> tt= (List<SecurityQuestions>)this.sessionFactory.getCurrentSession().createQuery(hql).list();
		List<T> temp = (List<T>)this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(t.getClass()).list();
		return temp;
	}

	public <T> List<T> queryFactory(T t, String tablename, String whereClause) {
		String hql = "select * from "+tablename;
		if(!whereClause.equals("")&&whereClause!=null){
			hql += " where 1=1 "+ whereClause;
		}
		List<T> temp = (List<T>)this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(t.getClass()).list();
		return temp;
	}

	public <T> List<T> queryFactory(T t, String hql) {
		// TODO Auto-generated method stub
		List<T> temp = (List<T>)this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(t.getClass()).list();
		return temp;
	}

	public <T> List<T> queryListPageAndRows(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

}
