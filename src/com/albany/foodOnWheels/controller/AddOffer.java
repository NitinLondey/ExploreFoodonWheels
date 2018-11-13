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

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.Offers;
import com.services.Connection;


/**
 * Servlet implementation of add menu
 */
@WebServlet({ "/AddOffer", "/AddOffer.do" })
public class AddOffer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AddOffer() {
		super();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {


	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {


		HttpSession httpSession = request.getSession();
		System.out.println("inside offers");
		String truckowner = (String) httpSession.getAttribute("user_name");
		System.out.println(truckowner);
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String cuisine = null;
			List<FoodTruckOwner> userList = session.createCriteria(FoodTruckOwner.class).add(Restrictions.eq("truck_name", truckowner)).list();
					FoodTruckOwner fto=userList.get(0);
					if(fto.getCuisine()!=null) {
					cuisine = fto.getCuisine();
				}
			List<String> list = new ArrayList<String>();
			if (cuisine != null) {
				String listofCuisine[] = cuisine.split(",");
				for (int i = 0; i < listofCuisine.length; i++) {
					list.add(listofCuisine[i]);

				}
				int lengthoflist=list.size();
				request.setAttribute("list", list);
			
				request.setAttribute("count", lengthoflist);
			}
				String fileName=null;
				List<Offers> offerdetails = session.createCriteria(Offers.class).add(Restrictions.eq("truck_name", truckowner)).list();;
				if(!offerdetails.isEmpty()) {
					Offers offers=offerdetails.get(0);
				if(offers.getImage_path()!=null) {
					fileName=offers.getImage_path();
			}			
				}
				System.out.println("inside add offers" +fileName);
				request.setAttribute("fileName", fileName);
				httpSession.setAttribute("list", list);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/offers.jsp");
				dispatcher.forward(request, response);
				return;
			

		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
		}

		finally {
			session.close();
		}
	}
}
