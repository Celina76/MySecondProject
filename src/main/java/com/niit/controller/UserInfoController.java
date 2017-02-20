package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserInfoDao;
import com.niit.model.UserInfo;

@RestController
public class UserInfoController {
	@Autowired(required = true)
	private UserInfoDao ui;
	@Autowired
	private HttpSession ss;
	@GetMapping("/")
	public String getIndex()
	{
		return "index";
	}
	@GetMapping("/hello")
	public String getHello()
	{
		return "index";
	}
	
	// Mapping
	// GetMapping->Fetching the data by sending few parameters
	// PostMapping->create a record or save
	// PutMapping->update the record
	// DeleteMapping->delete the record
	// define a simple service and test
	// call the methods of DAO
	// List<UserInfo>-> need to convert json object,so we can use in front end
	// proj
	// ResponseEntity-constructor-you have pass a java object
	// it will return json object
	// How to test?
	// 1.Postman
	// 2.RestClient

	// http://localhost:8080/Collabration/getAllUsers
	@SuppressWarnings("unchecked")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserInfo>> getAllUsers(UserInfo u) {
		List users = ui.list();// getting data from database
		if (users.isEmpty()) {
			u.setErrorCode("100");
			u.setErrorMessage("No Users are available");
			users.add(u);
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		u.setErrorCode("200");
		u.setErrorMessage("Succesfully fetched user");
		return new ResponseEntity<>(users, HttpStatus.OK);// json array
		// HTTP Status
		// errorCode for manipulation and coding eg:404
		// errorResponse error message for user eg: page not found
		// 200 for success
		// 1)add 2 properties(errorCode,errorMessage) in each domain class
		// 2)yu can create a seprate class,other classes can extend this class
		// @Transient
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserInfo> getUser(@PathVariable(name = "user_id") String user_id,UserInfo u) {
		u = ui.get(user_id);
		if (u == null) {
			u = new UserInfo();
			u.setErrorCode("404");
			u.setErrorMessage("no user is found this id" + user_id);

		}
		return new ResponseEntity<UserInfo>(u, HttpStatus.OK);
	}
	//-------------------Retrieve Single User--------------------------------------------------------
    
    @GetMapping(value = "/user/{user_id}")
    public ResponseEntity<UserInfo> getSingleUser(@PathVariable("user_id") String user_id,UserInfo u) {
        System.out.println("Fetching UserInfo with user_id " + user_id);
         u = ui.get(user_id);
        if (u == null) {
            System.out.println("User with user_id " + user_id + " not found");
            return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserInfo>(u, HttpStatus.OK);
    }
  
    //------------------- Update a User --------------------------------------------------------
    
    @PutMapping(value = "/updateuser/{user_id}")
    public ResponseEntity<UserInfo> updateUser(@PathVariable("user_id") String user_id, @RequestBody UserInfo u) {
        System.out.println("Updating User " + user_id);
          
        UserInfo currentUser = ui.get(user_id);
          
        if (currentUser==null) {
            System.out.println("User with id " + user_id + " not found");
            return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
        }
  
        currentUser.setUser_name(u.getUser_name());
                currentUser.setEmail(u.getEmail());
                currentUser.setMobile_no(u.getMobile_no());
                currentUser.setPassword(u.getPassword());


          
        ui.update(currentUser);
        return new ResponseEntity<UserInfo>(currentUser, HttpStatus.OK);
    }
	@GetMapping("/Validate/{id}/{password}")
	public ResponseEntity<UserInfo> validate(@PathVariable("user_id")String user_id,@PathVariable("password") String pwd,UserInfo u)
	{
		u=ui.validate(user_id, pwd);
		if(u==null)
		{
			 u =new UserInfo();
			u.setErrorCode("404");
			u.setErrorMessage("invalid");
		}
		else
		{
			u.setErrorCode("200");
			u.setErrorMessage("success");
			ss.setAttribute("LoggedInUserId",u.getUser_id());
		}
		return new ResponseEntity<UserInfo>(u,HttpStatus.OK);
		}
	@PostMapping("/createUser")
	public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo u)
	{
		if(ui.get(u.getUser_id())== null)
		
		{
			ui.save(u);
			u.setErrorCode("200");
			u.setErrorMessage("you successfully registered");
			
		}
		else
		{
			u.setErrorCode("404");
			u.setErrorMessage("existing:"+u.getUser_id());
		}
		return new ResponseEntity<UserInfo>(u,HttpStatus.OK);
	}
	@GetMapping("/logout")
	public UserInfo logout(UserInfo u)
	{
		ss.invalidate();
	u.setErrorCode("200");
	u.setErrorMessage("logged out successfully");
	return u;
	}
	}


