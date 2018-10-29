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
import com.services.Connection;

/**
 * Servlet implementation class MovingStatusServlet
 */
@WebServlet({"/MovingStatusServlet","/MovingStatusServlet.do"})
public class MovingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovingStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = null;
		Transaction tx = null;
		boolean status;
		String truckName = request.getParameter("truckName");
		String currentStatus;
		
		if(request.getParameter("movingStatus").equals("true")) {
			status = true;
			currentStatus = "Current Status: <strong>The food truck is moving right now!</Strong>";
		}
		else {
			status = false;
			currentStatus = "Current Status: <strong>The food truck is not moving!</Strong>";
		}
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<FoodTruckOwner> foodTruckOwnerList = session.createCriteria(FoodTruckOwner.class)
					.add(Restrictions.eq("truck_name", truckName))
					.list();
			FoodTruckOwner truck_owner = foodTruckOwnerList.get(0);
			System.out.println(truck_owner.getTruck_name());
			System.out.println(truck_owner.isIs_moving());
			truck_owner.setIs_moving(status);
			System.out.println(truck_owner.isIs_moving()+"22");
			session.update(truck_owner);
			tx.commit();
			
			request.setAttribute("movingStatus", request.getParameter("movingStatus"));
			request.setAttribute("truckName", truck_owner.getTruck_name());
			request.setAttribute("currentStatus", currentStatus);
			request.setAttribute("msg", "Your moving status is changed successfully!");
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
