package com.albany.foodOnWheels.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class Signup
 */
@WebServlet({ "/Signup", "/Signup.do" })
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String register = request.getParameter("register");
		User user=new User();
		user.setUser_name(request.getParameter("user_name"));
		user.setFirstname(request.getParameter("first_name"));
		user.setLastname(request.getParameter("last_name"));
		user.setPassword(request.getParameter("password"));
		user.setAddress_line1(request.getParameter("address1"));
		user.setAddress_line_2(request.getParameter("address2"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
		user.setPhone(request.getParameter("phone"));
		
		user.setEmail(request.getParameter("email"));
		
		
		if(register.equals("truck")) {
			user.setRole("truck_owner");
			SessionFactory sessionFactory = Connection.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(user);
			FoodTruckOwner truck_owner = new FoodTruckOwner();
			truck_owner.setTruck_name(request.getParameter("user_name"));
			truck_owner.setZip_code(Integer.parseInt(request.getParameter("zipcode")));
			truck_owner.setPhone(request.getParameter("phone"));
			
			
			String[] cuisine = request.getParameterValues("cuisine"); 
			truck_owner.setCuisine(String.join(",",cuisine ));
			
			String[] days = request.getParameterValues("days");
			truck_owner.setDays(String.join(",",days ));
			truck_owner.setWeekday_time( request.getParameter("week_day"));
			truck_owner.setWeekend_time( request.getParameter("week_end"));
			String[] payment = request.getParameterValues("payment");
			
			truck_owner.setAccepted_payments(String.join(",",payment ));
			truck_owner.setApproved("Pending");
			truck_owner.setIs_moving(false);
			truck_owner.setAddress_line_1(request.getParameter("address1"));
			truck_owner.setAddress_line_2(request.getParameter("address2"));
			truck_owner.setCity(request.getParameter("city"));
			truck_owner.setState(request.getParameter("state"));
			session.save(truck_owner);
			session.getTransaction().commit();
			session.close();
			
		}
		else if(register.equals("user")) {
			user.setRole("user");
			SessionFactory sessionFactory = Connection.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(user);
			session.getTransaction().commit();
			session.close();
			
		}
		request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		
		
	}

}
