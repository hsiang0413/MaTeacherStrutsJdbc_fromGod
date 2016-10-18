package model;

import java.util.Arrays;

public class CustomerBean {

	private String custid;
	private byte[] password;
	private String email;
	private java.util.Date birth;

	@Override
	public String toString() {
		System.out.println("custid = " + custid + ", " + "password = "
				+ (char) Integer.parseInt(Arrays.toString(password).substring(1, 3), 10) + ", email = " + email
				+ ", birth = " + birth);
		return "";
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.util.Date getBirth() {
		return birth;
	}

	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj instanceof CustomerBean)) {
			CustomerBean temp = (CustomerBean) obj;
			if (this.custid == temp.custid){
				return true;
			}
		}
		return false;
	}
}
