package com.kh.controller.login.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.entity.model.KhUserInfo;

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
	public List<KhUserInfo> queryUserIds() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from KhUserInfo");
		List<KhUserInfo> list = query.setCacheable(true).list();
		return list;
	}

	@Override
	public KhUserInfo queryUserInfoById(int id) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from KhUserInfo where id=?0")
				.setParameter(0, id);
		KhUserInfo user = (KhUserInfo) query.setCacheable(true).uniqueResult();
		return user;
	}

	@Override
	public KhUserInfo queryUserInfoByUsernameAndPwd(String username,
			String password) {
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery(
						"from KhUserInfo where username=:username and password=:password")
				.setParameter("username", username)
				.setParameter("password", password);
		KhUserInfo user = (KhUserInfo) query.setCacheable(true).uniqueResult();
		return user;
	}

}
