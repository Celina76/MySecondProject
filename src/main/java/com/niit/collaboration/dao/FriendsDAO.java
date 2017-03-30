package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Friends;
import com.niit.collaboration.model.User;

@Repository("friendsDAO")
public interface FriendsDAO {
//select * from Friend where userID=? and status='A'
	public List<Friends> getMyFriend(String userID);
	//if yu want to get all detais of yur friend
	//yu can use get(userID) of userDao interface
	public Friends get(String userID, String friendID);
	public Friends get(int id);
	public Friends get(String friendID);
		public boolean save(Friends friends);
		public boolean update(Friends friends);
		public boolean delete(String userID, String friendID);
		//select * from Friend where friendID=? and status='N'
		public List<Friends> getNewFriendRequests(String userID);
		public void setOnline(String userID);
		public void setOffLine(String loggedInUserID);
		//select * from Friend where userID=? status='N'
	public List<Friends> getRequestsSendByMe(String userID);
	
}
