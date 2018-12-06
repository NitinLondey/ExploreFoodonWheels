package com.albany.foodOnWheels.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import com.albany.foodOnWheels.model.FoodFestival;
import com.albany.foodOnWheels.model.Menu;
import com.services.Connection;
import com.services.ImageUpload;

/**
 * Servlet implementation class AddEventsServlet
 */
@WebServlet({"/AddEventsServlet","/AddEventsServlet.do"})
@MultipartConfig
public class AddEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//String fileName = ImageUpload.imageUpload(request);
		
		HttpSession httpSession = request.getSession();
		
		String username = (String) httpSession.getAttribute("user_name");
		String name = request.getParameter("festival_name");
		String sDate = request.getParameter("start_date");
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		Date startDate = null;
		try {
			startDate = (Date) formatter.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String eDate = request.getParameter("end_date");
		Date endDate = null;
		try {
			endDate = (Date) formatter.parse(eDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String address = request.getParameter("address");
		System.out.println(name);
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(address);
		
		FoodFestival foodfestival = new FoodFestival();
		foodfestival.setTruck_name(username);
		foodfestival.setFestival_name(name);
		foodfestival.setStartDate(startDate);
		foodfestival.setEndDate(endDate);
		foodfestival.setAddress(address);
		foodfestival.setHours(request.getParameter("hours"));
		foodfestival.setCity(request.getParameter("city"));
		foodfestival.setState(request.getParameter("state"));
		foodfestival.setImage_path(null);
		foodfestival.setZip_code(Integer.parseInt(request.getParameter("zipcode")));
		foodfestival.setApproved("processing");
		
		SessionFactory sessionFactory = Connection.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(foodfestival);
		session.getTransaction().commit();
		session.close();
		System.out.println("add events ");
		
		request.setAttribute("message", "Food Festival added successfully");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/showFoodFestivals");
		dispatcher.forward(request, response);
		return;

	}

}
