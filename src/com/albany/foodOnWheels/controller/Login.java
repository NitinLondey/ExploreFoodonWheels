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

import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/Login", "/Login.do" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		user.setUser_name(request.getParameter("user_name"));
		user.setPassword(request.getParameter("password"));
		HttpSession httpSession = request.getSession();
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			
			List<User> userList = session.createCriteria(User.class)
					.add(Restrictions.eq("user_name", user.getUser_name()))
					.add(Restrictions.eq("password", user.getPassword()))
					.list();
			System.out.println(userList.size());
			if(userList.size()> 0) {
				User loggedin=  userList.get(0);
				
				httpSession.setAttribute("user_name", loggedin.getUser_name());
				httpSession.setAttribute("role", loggedin.getRole());
				httpSession.setAttribute("zipcode", loggedin.getZipcode());
				request.setAttribute("message", "Login successful ! Welcome "+ loggedin.getUser_name());

				
				if(loggedin.getRole().equals("user")) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SearchFoodTruck.do");
					
					dispatcher.forward(request, response);
				}
				else if(loggedin.getRole().equals("truck_owner")) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/HomePage");
					dispatcher.forward(request, response);
				}
				else {
					//admin
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Request_center.do");
					dispatcher.forward(request, response);
				}

				
				return;	
				
				
			}
			else {
				
				request.setAttribute("message", "Login failed ! Incorrect User name or Password");
				request.getRequestDispatcher("/jsps/login.jsp").include(request, response);
				
			}
			
					
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

}
