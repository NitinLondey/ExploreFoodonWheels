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

import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class Zipcode
 */
@WebServlet("/Zipcode")
public class Zipcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Zipcode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		String user_name = (String) httpSession.getAttribute("user_name");
		String role = (String) httpSession.getAttribute("role");
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
		
					
			List<User> userList = session.createCriteria(User.class)
						.add(Restrictions.eq("user_name", user_name))
						.list();
			System.out.println("zipcode"+userList.size());
			User loggedin=  userList.get(0);
			request.setAttribute("user", loggedin);
					
			request.getRequestDispatcher("/jsps/zipcodeUpdate.jsp").forward(request, response);
			return; 		
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}

	}

}
