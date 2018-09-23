package com.albany.foodOnWheels.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.User;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("here");
		// TODO Auto-generated method stub
		String register = request.getParameter("register");
		User user=new User();
		user.setUser_name(request.getParameter("truck_name"));
		user.setFirstname(request.getParameter("first_name"));
		user.setLastname(request.getParameter("last_name"));
		user.setPassword(request.getParameter("password"));
		user.setAddress_line1(request.getParameter("address1"));
		user.setAddress_line_2(request.getParameter("address2"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
		user.setPhone(request.getParameter("phone"));
		user.setRole("truck_owner");
		user.setEmail("email");
		
		
		if(register.equals("truck")) {
			FoodTruckOwner truck_owner = new FoodTruckOwner();
			truck_owner.setTruck_name(request.getParameter("truck_name"));
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
			truck_owner.setApproved(false);
			truck_owner.setIs_moving(false);
			truck_owner.setAddress_line_1(request.getParameter("address1"));
			truck_owner.setAddress_line_2(request.getParameter("address2"));
			
			Configuration config = new Configuration();
			config.addAnnotatedClass(com.albany.foodOnWheels.model.User.class);
			config.addAnnotatedClass(com.albany.foodOnWheels.model.FoodTruckOwner.class);
			config.configure();
			ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			SessionFactory factory = config.buildSessionFactory(servReg);
			Session session = factory.openSession();
			session.beginTransaction();
			
			session.save(user);
			session.save(truck_owner);
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		
		
	}

}
