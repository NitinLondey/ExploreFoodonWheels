package com.albany.foodOnWheels.controller;


import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class MovingStatus
 */
@WebServlet("/MovingStatus")
public class MovingStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovingStatus() {
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
		String role = (String) httpSession.getAttribute("role");
		String currentStatus;
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<User> userList = session.createCriteria(User.class)
					.add(Restrictions.eq("user_name", user_name))
					.list();
			
			User loggedin =  userList.get(0);
			tx.commit();
			
			FoodTruckOwner truck_owner = new FoodTruckOwner();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
				List<FoodTruckOwner> foodTruckOwnerList = session.createCriteria(FoodTruckOwner.class)
					.list();
				
			System.out.println("movingStatus  "+foodTruckOwnerList.size());
			for(FoodTruckOwner foodtruckowner : foodTruckOwnerList) {
				//System.out.println(foodtruckowner.getUser().getUser_name());
				if(foodtruckowner.getUser().getUser_name().equals(loggedin.getUser_name())) {
					System.out.println("truck find");
					truck_owner = foodtruckowner;
				}
			}
			tx.commit();
			if(truck_owner.isIs_moving()==true) {
				currentStatus = "<strong>Current Status: The food truck is moving right now!</Strong>";
			}else {
				currentStatus = "<strong>Current Status: The food truck is not moving!</Strong>";
			}
			request.setAttribute("movingStatus", truck_owner.isIs_moving());
			request.setAttribute("truckName", truck_owner.getTruck_name());
			request.setAttribute("currentStatus", currentStatus);
			request.getRequestDispatcher("/jsps/movingStatus.jsp").forward(request, response);
			return; 
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

}
