package com.albany.foodOnWheels.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.TrustStoreCallback;
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
 * Servlet implementation class Login
 */
@WebServlet({ "/Request_center", "/Request_center.do" })
public class RequestAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();

		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			HttpSession httpsession = request.getSession();
			String role = (String) httpsession.getAttribute("role");
			
				List<FoodTruckOwner> truckowner = session.createCriteria(FoodTruckOwner.class)
						.add(Restrictions.eq("approved", false)).list();
				System.out.println("^^^^" + truckowner.size());
				System.out.println("truckowner" + truckowner);
				request.setAttribute("list", truckowner);
				RequestDispatcher rd = request.getRequestDispatcher("/jsps/admin_request.jsp");
				rd.forward(request, response);

			

			

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
	}

}
