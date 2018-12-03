package com.albany.foodOnWheels.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.Menu;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;
import com.services.ImageUpload;

import java.io.PrintWriter;
import java.net.URL;

@WebServlet({ "/ProfileImageUploaderServlet", "/ProfileImageUploaderServlet.do" })
@MultipartConfig
public class ProfileImageUploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String directory;
	
	public ProfileImageUploaderServlet() {
		super();

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		try {

			String fileName = ImageUpload.imageUpload(request);
			HttpSession httpSession = request.getSession();
			String truckowner = (String) httpSession.getAttribute("user_name");
			SessionFactory sessionFactory = Connection.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = null;
			tx = session.beginTransaction();
			List<User> userList = session.createCriteria(User.class)
					.add(Restrictions.eq("user_name", truckowner))
					.list();
			User loggedin=  userList.get(0);
			tx.commit();
			
			request.setAttribute("user", loggedin);
			
			FoodTruckOwner truck_owner= new FoodTruckOwner();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<FoodTruckOwner> foodTruckOwnerList = session.createCriteria(FoodTruckOwner.class).list();
			for(FoodTruckOwner foodtruckowner : foodTruckOwnerList) {
				//System.out.println(foodtruckowner.getUser().getUser_name());
				if(foodtruckowner.getUser().getUser_name().equals(loggedin.getUser_name())) {
					System.out.println("truck find");
					truck_owner = foodtruckowner;
				}
			}
			System.out.println(fileName +  "3");
			truck_owner.setImage_path(fileName);
			tx.commit();
			
			request.setAttribute("msg", "Your image is updated successfully!");
			request.setAttribute("truck", truck_owner);
			request.setAttribute("cuisine", truck_owner.getCuisine());
			request.setAttribute("days", truck_owner.getDays());
			request.setAttribute("payments", truck_owner.getAccepted_payments());
			request.setAttribute("fileName", fileName);
			doGet(request, response);

		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/profile-truckowner.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
