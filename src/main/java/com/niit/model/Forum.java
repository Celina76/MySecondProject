package com.niit.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="forum")
public class Forum extends BaseDomain {

	public Forum(){}
	@Id
	@Column
	private int forum_id;
	@Column
	private int user_id;
	@Column
	private String forum_topic;
	@Column
	private Date created_date;
	public int getForum_id() {
		return forum_id;
	}
	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getForum_topic() {
		return forum_topic;
	}
	public void setForum_topic(String forum_topic) {
		this.forum_topic = forum_topic;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

}
