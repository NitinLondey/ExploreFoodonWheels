package com.albany.foodOnWheels.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table( name = "offers")

public class Offers {
	@Id
	@Column(name="truck_name")
	private String truck_name;
	
	@Column(name="offer_on")
	private String offer_on;
	
	@Column(name="cuisine_type")
	private String cuisine_type;
	
	@Column(name="percent_off")
	private int percent_off;

	public String getTruck_name() {
		return truck_name;
	}

	@Column(name="image_path")
	private String image_path;
	
	

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public void setTruck_name(String truck_name) {
		this.truck_name = truck_name;
	}

	public String getOffer_on() {
		return offer_on;
	}

	public void setOffer_on(String offer_on) {
		this.offer_on = offer_on;
	}

	public String getCuisine_type() {
		return cuisine_type;
	}

	public void setCuisine_type(String cuisine_type) {
		this.cuisine_type = cuisine_type;
	}

	public int getPercent_off() {
		return percent_off;
	}

	public void setPercent_off(int percent_off) {
		this.percent_off = percent_off;
	}
	
	
	
	
}
