package com.kh.controller.login.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.entity.model.UserInfo;

@Repository(value = "loginMgrDao")
public class LoginMgrDaoImpl implements LoginMgrDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserInfo> queryUserIds() {
	Session session = sessionFactory.openSession();
	Query query = session.createQuery("from UserInfo");
	List<UserInfo> list = query.setCacheable(true).list();
	return list;
    }

}
