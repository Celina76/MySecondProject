package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;

public interface blogDao {
	

	public Boolean createBlog(Blog b);
	public List<Blog> getAllBlog();
	public Blog getBlogDetails(int blog_id);
	
	public List <Blog> getMyBlog(String user_id);
//	public List <Blog> getAllBlog(char status);
	public boolean updateBlog(Blog blog);
}




//Blog getBlog(int id)
//List<blog> getAllBlogs();
//List<blog> getAllBlogs(String user_id)
//boolean save(blog b) create new blog
//boolean update (blog b) 
//how approve or reject-we can use update