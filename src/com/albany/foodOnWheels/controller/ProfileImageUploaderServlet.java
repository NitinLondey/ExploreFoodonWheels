package com.albany.foodOnWheels.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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

import java.io.PrintWriter;
import java.net.URL;

@WebServlet({ "/ProfileImageUploaderServlet", "/ProfileImageUploaderServlet.do" })
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

		try {

			String cuisine = request.getParameter("cuisinename");
			System.out.println(cuisine);
			HttpSession httpSession = request.getSession();
			String truckowner = (String) httpSession.getAttribute("user_name");

			directory = request.getSession().getServletContext().getRealPath("/") + "images\\";
			System.out.println("menu upload" + directory);
			ServletFileUpload.isMultipartContent(request);
			response.setContentType("image");

			PrintWriter out = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			// factory.setRepository(new File("c:\\temp"));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(maxFileSize);
			String filepath = "";
			String fileName = "";
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					fileName = fi.getName();
					
					System.out.println(fileName);
					System.out.println("file apth" + filepath);
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(directory + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(directory + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					fi.write(file);

				}

			}
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
			System.out.println(directory);
			System.out.println(filepath);
			System.out.println(fileName);
			truck_owner.setImage_path(fileName);
			tx.commit();
			
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
