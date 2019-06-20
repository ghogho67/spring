package kr.or.ddit.user.model;

import java.util.Date;

public class UserVo {

	private String userId;
	private String name;
	private String alias;
	private Date birth;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public UserVo() {
	
	}
	public UserVo(String userId, String name, String alias, Date birth) {
		super();
		this.userId = userId;
		this.name = name;
		this.alias = alias;
		this.birth = birth;
	}
	
	
	
	
}