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
import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class ViewEventsServlet
 */
@WebServlet({"/ViewEventsServlet","/ViewEventsServlet.do"})
public class ViewEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			System.out.println("View Event");
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			HttpSession httpsession = request.getSession();
			//String role = (String) httpsession.getAttribute("role");
			
				List<FoodFestival> truckowner = session.createCriteria(FoodFestival.class)
						.add(Restrictions.or(Restrictions.eq("approved", "approved"), Restrictions.eq("approved", "processing")))
						.list();
				
				System.out.println("^^^^" + truckowner.size());
				System.out.println("truckowner" + truckowner);
				request.setAttribute("list", truckowner);
				RequestDispatcher rd = request.getRequestDispatcher("/jsps/eventCenter.jsp");
				rd.forward(request, response);


		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//User user = new User();
//		doPost(request, response);
//	}

}
