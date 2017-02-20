package com.niit.dao;

import java.util.List;

import com.niit.model.UserInfo;

public interface UserInfoDao {
	public UserInfo get(String user_id);
	public List<UserInfo> list();
	public UserInfo validate(String  user_id,String password);
	public boolean save (UserInfo u);
	public boolean update(UserInfo u);
}
