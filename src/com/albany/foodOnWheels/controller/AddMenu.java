package com.albany.foodOnWheels.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.albany.foodOnWheels.constants.Constant;
import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.Menu;
import com.services.Connection;


/**
 * Servlet implementation of add menu
 */
@WebServlet({ "/AddMenu", "/AddMenu.do" })
public class AddMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AddMenu() {
		super();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {


	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		HttpSession httpSession = request.getSession();
		String truckowner = (String) httpSession.getAttribute("user_name");
		
		if(request.getParameter("truckname") != null) {
			truckowner =  request.getParameter("truckname");
			httpSession.setAttribute("truckname", truckowner);
			String role = (String)httpSession.getAttribute("role");
			if(role == "user") {
				httpSession.setAttribute("isUser", "user");
			}
		}
		else {
			httpSession.setAttribute("isUser", "truck");
		}
		
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String cuisine = null;
			List<FoodTruckOwner> userList = session.createCriteria(FoodTruckOwner.class)
					.add(Restrictions.eq("truck_name", truckowner)).list();
			
			for (FoodTruckOwner fto : userList) {
					cuisine = fto.getCuisine();
			}
			if (cuisine != null) {
				List<String> list = new ArrayList<String>();
				String listofCuisine[] = cuisine.split(",");
				for (int i = 0; i < listofCuisine.length; i++) {
					list.add(listofCuisine[i]);

				}
				int lengthoflist=list.size();
				request.setAttribute("list", list);
				
				request.setAttribute("count", lengthoflist);
				System.out.println("**************************");
				String fileName="";

				List<Menu> menudetails = session.createCriteria(Menu.class)
						.add(Restrictions.eq("truck_name", truckowner)).list();
				for (Menu m : menudetails) {
						fileName=m.getImage_path();
				}
				System.out.println("add menu"+fileName);
				request.setAttribute("fileName", fileName);
				httpSession.setAttribute("list", list);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/menuCard.jsp");
				dispatcher.forward(request, response);
				return;
			}

		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
		}

		finally {
			session.close();
		}
	}
}
