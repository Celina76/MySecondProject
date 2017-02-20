package com.niit.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserInfoDao;
import com.niit.model.UserInfo;

@Service
@EnableTransactionManagement
public class UserInfoDaoImpl implements UserInfoDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public UserInfo get(String user_id) {
		return (UserInfo) sessionFactory.getCurrentSession().get(UserInfo.class, user_id);

	}

	@Transactional
	// @SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> list() {
		String hql = "from UserInfo";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	@Override
	public UserInfo validate(String user_id, String password) {
		// TODO Auto-generated method stub
		String hql = "from UserInfo where user_id='" + user_id + "'and password='" + password + "'";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return (UserInfo) q.uniqueResult();
	}

	@Transactional
	@Override
	public boolean save(UserInfo u) {
		try {
			sessionFactory.getCurrentSession().save(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	@Transactional
	@Override
	public boolean update(UserInfo u) {
		try {
			sessionFactory.getCurrentSession().update(u);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}
}
