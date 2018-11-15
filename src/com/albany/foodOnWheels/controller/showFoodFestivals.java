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

import com.albany.foodOnWheels.model.FoodFestival;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class showFoodFestivals
 */
@WebServlet("/showFoodFestivals")
public class showFoodFestivals extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showFoodFestivals() {
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
		if(request.getParameter("truckname") != null) {
			user_name =  request.getParameter("truckname");
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
			List<FoodFestival> festivalList = session.createCriteria(FoodFestival.class)
					.add(Restrictions.eq("truck_name", user_name))
					.list();
			if(request.getParameter("truckname") != null) {
				festivalList.removeIf(s -> s.getApproved().equals("rejected") || s.getApproved().equals("processing") );
			}
			request.setAttribute("list", festivalList);
			RequestDispatcher rd = request.getRequestDispatcher("/jsps/myFoodFestival.jsp");
			rd.forward(request, response);
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
