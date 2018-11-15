package com.albany.foodOnWheels.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.albany.foodOnWheels.model.Menu;
import com.services.Connection;
import com.services.ImageUpload;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet({ "/ImageUploaderServlet", "/ImageUploaderServlet.do" })
@MultipartConfig
public class ImageUploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ImageUploaderServlet() {
		super();

	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		// using imageupload service 
		
			String fileName = ImageUpload.imageUpload(request);
		
			HttpSession httpSession = request.getSession();
			String truckowner = (String) httpSession.getAttribute("user_name");
		    SessionFactory sessionFactory = Connection.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			List<Menu> menudetails = session.createCriteria(Menu.class)
					.add(Restrictions.eq("truck_name", truckowner)).list();
			
			//update
			if(menudetails.size() > 0) {
				Menu menu = menudetails.get(0);
				menu.setImage_path(fileName);
				session.update(menu);
				session.getTransaction().commit();
			}
			//add  
			else {
				Menu menu = new Menu();
				menu.setTruck_name(truckowner);
				menu.setImage_path(fileName);
				session.save(menu);
				session.getTransaction().commit();
			}
			request.setAttribute("message", "Menu added successfully");
			request.setAttribute("fileName", fileName);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/menuCard.jsp");
			dispatcher.forward(request, response);
			return;

		} 

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

	}
}
