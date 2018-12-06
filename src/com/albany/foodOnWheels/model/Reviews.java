
package com.albany.foodOnWheels.model;

import java.util.Date;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "reviews")

public class Reviews {
	@Id
	@Column(name = "truck_name")
	private String truck_name;
    
	
	@Column(name = "commented_by")
	private String commented_by;

	@Column(name = "commented_by_email")
	private String commented_by_email;

	@Column(name = "comments")
	private String comments;

	@Column(name = "image_path")
	private String image_path;

	@Column(name = "date_posted")
	private Date date_posted;

	public String getTruck_name() {
		return truck_name;
	}

	public void setTruck_name(String truck_name) {
		this.truck_name = truck_name;
	}

	public String getCommented_by() {
		return commented_by;
	}

	public void setCommented_by(String commented_by) {
		this.commented_by = commented_by;
	}

	public String getCommented_by_email() {
		return commented_by_email;
	}

	public void setCommented_by_email(String commented_by_email) {
		this.commented_by_email = commented_by_email;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public Date getDate_posted() {
		return date_posted;
	}

	public void setDate_posted(Date date_posted) {
		this.date_posted = date_posted;
	}


}