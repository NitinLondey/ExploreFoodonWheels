package com.albany.foodOnWheels.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "user_master")
public class User {
	
	@Id
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="first_name", length=50)
	private String firstname;
	
	@Column(name="last_name", length=50 )
	private String lastname;
	
	@Column(name="role", length=50)
	private String role;
	
	@Column(name="password", length=50)
	private String password;
	
	@Column(name="email", length=60)
	private String email;
	
	@Column(name="zipcode")
	private int zipcode;
	
	@Column(name="image_path", length=250)
	private String image_path;
	
	@Column(name="address_line_one", length=50)
	private String address_line1;
	
	@Column(name="address_line_two", length=50)
	private String address_line_2;
	
	@Column(name="city", length=30)
	private String city;
	
	@Column(name="state", length=30)
	private String state;
	
	@Column(name="phone", length=15)
	private String phone;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line_2() {
		return address_line_2;
	}
	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
}
