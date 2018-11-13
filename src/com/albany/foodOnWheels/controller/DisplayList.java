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
import org.hibernate.criterion.Restrictions;

import com.albany.foodOnWheels.model.Offers;
import com.services.Connection;

/**
 * Servlet implementation class DisplayList
 */
@WebServlet({"/DisplayList","/DisplayList.do"})
public class DisplayList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayList() {
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
		String truckowner = (String) httpSession.getAttribute("user_name");
		SessionFactory sessionFactory = Connection.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.beginTransaction();

			List<Offers> offersdetails = session.createCriteria(Offers.class).list();

			request.setAttribute("listofoffers", offersdetails);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/listOffers.jsp");
			dispatcher.forward(request, response);
			return;
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
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
