package com.training.bean;

public class RegisterBean {
	private String email;
	private long phone;
	private String firstName;
	private String lastName;

	
	public RegisterBean() {
	}

	public RegisterBean(String firstName, String lastName, String email,long phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "RegisterBean [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone +"]";
	}

}
