package com.albany.foodOnWheels.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.Menu;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

import java.io.PrintWriter;

/**
 * Servlet implementation class ImageUpload
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
		System.out.println(truckowner);
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String cuisine = null;
			List<FoodTruckOwner> userList = session.createCriteria(FoodTruckOwner.class).list();
			System.out.println(userList.size());
			for (FoodTruckOwner fto : userList) {

				if (fto.getUser().getUser_name().equals(truckowner)) {
					cuisine = fto.getCuisine();
				}
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
				String images=null;
				List<Menu> menudetails = session.createCriteria(Menu.class).list();
				for (Menu m : menudetails) {
					if (m.getTruck_name().equals(truckowner) && !m.getImage_path().isEmpty()) {
			
						images=m.getImage_path();
					}
				}

				session.getTransaction().commit();
				

				List<String> list2=new ArrayList<String>();
				if(images!=null) {
					String s[]=images.split(",");
					for(int i=0;i<s.length;i++) {
						list2.add(s[i]);
					}
				request.setAttribute("listofsavedimages", list2);
				}
				
				
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
