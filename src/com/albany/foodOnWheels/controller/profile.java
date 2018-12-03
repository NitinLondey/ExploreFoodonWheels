package com.albany.foodOnWheels.controller;

import java.io.IOException;
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
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class profile
 */
@WebServlet("/profile")
public class profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		String user_name = (String) httpSession.getAttribute("user_name");
		System.out.println(user_name);
		String role = (String) httpSession.getAttribute("role");
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<User> userList = session.createCriteria(User.class)
					.add(Restrictions.eq("user_name", user_name))
					.list();
			System.out.println(userList.size());
			User loggedin=  userList.get(0);
			tx.commit();
			request.setAttribute("user", loggedin);
			
			if(role.equals("user")) {
						request.getRequestDispatcher("/jsps/profile.jsp").forward(request, response);
						return; 
			}
			else {
				FoodTruckOwner truck_owner = new FoodTruckOwner();
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				
					List<FoodTruckOwner> foodTruckOwnerList = session.createCriteria(FoodTruckOwner.class)
						.list();
					
				System.out.println("profile2  "+foodTruckOwnerList.size());
				for(FoodTruckOwner foodtruckowner : foodTruckOwnerList) {
					//System.out.println(foodtruckowner.getUser().getUser_name());
					if(foodtruckowner.getUser().getUser_name().equals(loggedin.getUser_name())) {
						System.out.println("truck find");
						truck_owner = foodtruckowner;
					}
				}
				tx.commit();
				System.out.println(truck_owner.getCuisine());
				request.setAttribute("truck", truck_owner);
				request.setAttribute("cuisine", truck_owner.getCuisine());
				request.setAttribute("days", truck_owner.getDays());
				request.setAttribute("payments", truck_owner.getAccepted_payments());
				request.setAttribute("fileName", truck_owner.getImage_path());
				request.getRequestDispatcher("/jsps/profile-truckowner.jsp").forward(request, response);
				return; 
			}
					
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
