package com.testbar.android.util;

import cn.bmob.v3.BmobObject;

public class LoginCommit extends BmobObject{
	private String Name;
	private String Password;
	

	public String getName() {
		return Name;
	}

	public void setName(String mName) {
		this.Name = Name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String mPassword) {
		this.Password = Password;
	}
	
	
}
