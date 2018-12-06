package com.albany.foodOnWheels.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.Reviews;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class AddReview
 */
@WebServlet({ "/AddReview", "/AddReview.do" })
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession httpSession = request.getSession();
		String user = (String) httpSession.getAttribute("user_name");
		String truck_name = request.getParameter("truckname");
		System.out.println("jjj" + user);
		System.out.println("jjj" + truck_name);
		httpSession.setAttribute("truckName", truck_name);

		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();

		String truckimage = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<Reviews> reviews = session.createCriteria(Reviews.class).add(Restrictions.eq("truck_name", truck_name )).list();
			request.setAttribute("reviewList", reviews);
			request.setAttribute("truckname", truck_name);

		
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/reviews.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
