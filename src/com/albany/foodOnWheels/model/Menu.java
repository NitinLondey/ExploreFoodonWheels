package com.albany.foodOnWheels.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table( name = "menu")

public class Menu {
	@Id
	@Column(name="truck_name")
	private String truck_name;
	
	@Column(name="item_name")
	private String item_name;
	
	@Column(name="item_cuisine")
	private String item_cuisine;
	
	@Column(name="price")
	private int price;
	
	@Column(name="item_description")
	private String item_description;
	
	@Column(name="calories")
	private String calories;
	
	@Column(name="food_type")
	@Type(type = "com.albany.foodOnWheels.dao.StringArrayType")
	private String[] foodtype;

	@Column(name="recommended_by_owner")
	private boolean recommended_by_owner;
	
	@Column(name="is_part_of_food_festival")
	private boolean is_part_of_food_festival;
	
	@Column(name="image_path")
	private String image_path;

	
	public String getTruck_name() {
		return truck_name;
	}

	public void setTruck_name(String truck_name) {
		this.truck_name = truck_name;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_cuisine() {
		return item_cuisine;
	}

	public void setItem_cuisine(String item_cuisine) {
		this.item_cuisine = item_cuisine;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String[] getFoodtype() {
		return foodtype;
	}

	public void setFoodtype(String[] foodtype) {
		this.foodtype = foodtype;
	}

	public boolean isRecommended_by_owner() {
		return recommended_by_owner;
	}

	public void setRecommended_by_owner(boolean recommended_by_owner) {
		this.recommended_by_owner = recommended_by_owner;
	}

	public boolean isIs_part_of_food_festival() {
		return is_part_of_food_festival;
	}

	public void setIs_part_of_food_festival(boolean is_part_of_food_festival) {
		this.is_part_of_food_festival = is_part_of_food_festival;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	
	
	
	
	
	
	
}
