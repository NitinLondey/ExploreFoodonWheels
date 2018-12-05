package com.albany.foodOnWheels.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table( name = "truck_owner")

public class FoodTruckOwner {
	@Id
	@Column(name="truck_name")
	private String truck_name;
	
	@Column(name="zipcode")
	private int zip_code;
	
	@Column(name="phone", length=15)
	private String phone;
	
	@Column(name="cuisine", length=50)
	private String cuisine;
	
	@Column(name="image_path", length=450)
	private String image_path;
	
	@Column(name="days", length=200)
	private String days;
	
	@Column(name="weekday_time", length=200)
	private String weekday_time;
	
	@Column(name="weekend_time", length=200)
	private String weekend_time;
	
	
	@Column(name="approved")
	private String approved;
	
	@Column(name="accepted_payment", length=200)
	private String accepted_payments;
	
	@Column(name="is_moving")
	private boolean is_moving;
	
	@Column(name="address_line_one", length=200)
	private String address_line_1;
	
	@Column(name="address_line_two", length=200)
	private String address_line_2;
	
	@Column(name="city", length=30)
	private String city;
	
	@Column(name="state", length=30)
	private String state;
	
	
	
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
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTruck_name() {
		return truck_name;
	}
	public void setTruck_name(String truck_name) {
		this.truck_name = truck_name;
	}
	public int getZip_code() {
		return zip_code;
	}
	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getWeekday_time() {
		return weekday_time;
	}
	public void setWeekday_time(String weekday_time) {
		this.weekday_time = weekday_time;
	}
	public String getWeekend_time() {
		return weekend_time;
	}
	public void setWeekend_time(String weekend_time) {
		this.weekend_time = weekend_time;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getAccepted_payments() {
		return accepted_payments;
	}
	public void setAccepted_payments(String accepted_payments) {
		this.accepted_payments = accepted_payments;
	}
	public boolean isIs_moving() {
		return is_moving;
	}
	public void setIs_moving(boolean is_moving) {
		this.is_moving = is_moving;
	}
	public String getAddress_line_1() {
		return address_line_1;
	}
	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}
	public String getAddress_line_2() {
		return address_line_2;
	}
	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}
	
	
	
	
}
