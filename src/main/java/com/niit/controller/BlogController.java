package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.blogDao;
import com.niit.model.Blog;

@RestController
public class BlogController {
	@Autowired(required = true)
	private blogDao bd;
	@Autowired
	private Blog b;
	@Autowired
	HttpSession session;

	@GetMapping("/getAllBlog")
	public List<Blog> getAllBlog() {
		return bd.getAllBlog();
	}

	@PutMapping("/approve/{blog_id}")
	public Blog approveBlog(@PathVariable("blog_id") int blog_id) {
		// get blog based on id
		// if blog is nt exist->cannot approve
		// if blog already approved->cnt approve again

		if (session.getAttribute("LoggedInUserId") == null) {
			b.setErrorMessage("please login to approve");
			b.setErrorCode("404");
			return b;
		}
		if (!session.getAttribute("LoggedInUserId").equals("ROLE_ADMIN")) {
			b.setErrorMessage("not authorised");
			b.setErrorCode("200");
			return b;

		}
		if (b == null)

			b = new Blog();
		b.setErrorMessage("no blog is exist");
		{
			b.setErrorCode("404");

		}
		if (b.getStatus().equals("A")) {
			b.setErrorCode("404");
			b.setErrorMessage("already exist");
			return b;
		}

		b = bd.getBlogDetails(blog_id);
		b.setStatus("A");
		if (bd.updateBlog(b)) {
			b.setErrorCode("200");
			b.setErrorMessage("approved");
		} else {
			b.setErrorCode("404");
			b.setErrorMessage("not able to approved");
		}
		return b;
	}

	// @GetMapping("/getAllBlog")
	// public ResponseEntity<List<Blog>> getBlog(Blog b) {
	// List<Blog> blog = bd.getAllBlog(b);// getting data from database
	// if (blog.isEmpty()) {
	// b.setErrorCode("100");
	// b.setErrorMessage("No blogs are available");
	// blog.add(b);
	// return new ResponseEntity<>(blog, HttpStatus.OK);
	// }
	// b.setErrorCode("200");
	// b.setErrorMessage("Succesfully fetched user");
	// return new ResponseEntity<>(blog, HttpStatus.OK);
	// }
	/*-------getting a single blog using blog_id-----------------*/

	@GetMapping("/getBlog/{id}")
	public ResponseEntity<Blog> getBlogDetails(@PathVariable(name = "blog_id") int blog_id, Blog b) {
		b = bd.getBlogDetails(blog_id);
		if (b == null) {
			b = new Blog();
			b.setErrorCode("404");
			b.setErrorMessage("no user is found this id" + blog_id);

		}
		return new ResponseEntity<Blog>(b, HttpStatus.OK);
	}
/*-------------------------for creating blog----------------------------------------*/
	@PostMapping("/createBlog")
	public Blog createBlog(@RequestBody Blog b) {
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		if (loggedInUser == null) {

			b.setErrorCode("404");
			b.setErrorMessage("you have to loggin to craete blog");
			return b;

		}
		b.setStatus("N");
		b.setUser_id(loggedInUser);

		if (bd.getBlogDetails(b.getBlog_id()) == null)

		{
			bd.createBlog(b);
			b.setErrorCode("200");
			b.setErrorMessage("blog is created");

		} else {
			b.setErrorCode("404");
			b.setErrorMessage("existing:" + b.getBlog_id());
		}
		return b;
	}
/**-----------------------------for rejecting the blog-----------------------*/
	@PutMapping("/rejectBlog/{id}/{reason}")
	public Blog rejectBlog(@PathVariable("id") Integer id, @PathVariable("reason ") String reason) {
		if (session.getAttribute("LoggedInUserId") == null) {
			b.setErrorMessage("please login to approve");
			b.setErrorCode("404");
			return b;
		}
		if (!session.getAttribute("LoggedInUserId").equals("ROLE_ADMIN")) {
			b.setErrorMessage("not authorised");
			b.setErrorCode("200");
			return b;

		}
		b = bd.getBlogDetails(id);
		if (b == null)

			b = new Blog();
		b.setErrorMessage("no blog is exist");
		{
			b.setErrorCode("404");

		}
		if (b.getStatus().equals("A")) {
			b.setErrorCode("404");
			b.setErrorMessage("already exist");
			return b;
		}
		b = bd.getBlogDetails(id);
		b.setStatus("A");
		if (bd.updateBlog(b)) {
			b.setErrorCode("200");
			b.setErrorMessage("approved");
		} else {
			b.setErrorCode("404");
			b.setErrorMessage("not able to approved");
		}
		return b;
	}
}