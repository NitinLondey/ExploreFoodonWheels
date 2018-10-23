package com.albany.foodOnWheels.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet({ "/FbLogin" })
public class FbLogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user=new User();
		user.setFirstname(request.getParameter("user_first_name"));
		user.setLastname(request.getParameter("user_last_name"));
		user.setEmail(request.getParameter("user_email"));
		String loginType =(String) request.getParameter("type");
		
		HttpSession httpSession = request.getSession();
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			
			List<User> userList = session.createCriteria(User.class)
					.add(Restrictions.eq("email", user.getEmail()))
					.list();
			
			System.out.println(userList.size());
			System.out.println(request.getParameter("user_email"));
			
			if(userList.size()> 0) {
				User loggedin=  userList.get(0);
				request.setAttribute("user", loggedin);
				
				if(loginType.equals("Fb")) {
					request.setAttribute("msg", "Facebook login successfully!<br/>  Please log in by Clicking the button.");
					request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
				}else {
					request.setAttribute("msg", "Gmail login successfully!<br/>  Please log in by Clicking the button.");
					request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
				}
			}
			else {
				/**request.setAttribute("FBname", ("Welcome! "+request.getParameter("user_name")));
				request.setAttribute("msg", "Your Facebook is not registered! Please finish signup by filling in other information");
				request.setAttribute("user", user);
				request.getRequestDispatcher("/jsps/signup.jsp").forward(request, response); */
				if(loginType.equals("Fb")) {
				response.getWriter().print("<h1>Your Facebook is not registered!</h1><a href='"+request.getContextPath()+"/jsps/signup.jsp"
					+ "'>Click here to signup as a food truck ower</a><br/><a href='"+request.getContextPath()+"/jsps/signup-user.jsp"
					+ "'>Click here to signup as a user</a>");
				}
				else {
					response.getWriter().print("<h1>Your Gmail is not registered!</h1><a href='"+request.getContextPath()+"/jsps/signup.jsp"
							+ "'>Click here to signup as a food truck ower</a><br/><a href='"+request.getContextPath()+"/jsps/signup-user.jsp"
							+ "'>Click here to signup as a user</a>");
				}
				
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

}
