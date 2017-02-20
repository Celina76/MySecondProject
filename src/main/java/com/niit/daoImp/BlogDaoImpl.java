package com.niit.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.blogDao;
import com.niit.model.Blog;

@Service
@EnableTransactionManagement
public class BlogDaoImpl implements blogDao {
	@Autowired
	SessionFactory ss;
//	/*constructor for session factory**/
//	public BlogDaoImpl(SessionFactory ss)
//	{
//		this.ss=ss;
//	}
@Transactional
	@Override
	public Boolean createBlog(Blog b) {
	try {
		ss.getCurrentSession().save(b);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;

	}
	}
/*
 * (non-Javadoc)
 * @see com.niit.dao.blogDao#getAllBlog(com.niit.model.Blog)
 * it will return all the approved blogs
 */
@Transactional
	@Override
	public List<Blog> getAllBlog() {
		String hql = "from Blog where status='A'";
		Query query = ss.getCurrentSession().createQuery(hql);
		return query.list();
	}
/*
 * (non-Javadoc)
 * @see com.niit.dao.blogDao#getBlogDetails(int)
 */
@Transactional
	@Override
	public Blog getBlogDetails(int blog_id) {
	return (Blog) ss.getCurrentSession().get(Blog.class, blog_id);
	}

/*
 * (non-Javadoc)
 * @see com.niit.dao.blogDao#getMyBlog(java.lang.String)
 * 
 */
@Transactional
	@Override
	public List<Blog> getMyBlog(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Blog> getAllBlog(char status) {
//		// TODO Auto-generated method stub
//		return null;
//	}
@Transactional
	@Override
	public boolean updateBlog(Blog blog) {
		try {
		ss.getCurrentSession().update(blog);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return true;

		}

	}

	}

	

