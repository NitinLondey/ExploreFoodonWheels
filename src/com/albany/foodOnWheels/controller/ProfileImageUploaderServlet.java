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
	private int maxFileSize = 700 * 1024;
	private int maxMemSize = 10 * 1024;
	private File file;
	private File renamefile;
	
	public ProfileImageUploaderServlet() {
		super();

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		String fileName = ImageUpload.imageUpload(request);
		
		HttpSession httpSession = request.getSession();
		String truckowner = (String) httpSession.getAttribute("user_name");
	    SessionFactory sessionFactory = Connection.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<FoodTruckOwner> list = session.createCriteria(FoodTruckOwner.class)
				.add(Restrictions.eq("truck_name", truckowner)).list();
		
		
			FoodTruckOwner foodtruckowner = list.get(0);
			foodtruckowner.setImage_path(fileName);
			session.update(foodtruckowner);
			session.getTransaction().commit();
		 
		request.setAttribute("message", "Image added successfully");
		request.setAttribute("fileName", fileName);
		System.out.println(fileName);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/profile-truckowner.jsp");
		dispatcher.forward(request, response);
		return;

	} 


public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, java.io.IOException {

}

}
