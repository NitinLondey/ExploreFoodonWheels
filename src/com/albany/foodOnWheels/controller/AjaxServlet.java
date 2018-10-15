package com.albany.foodOnWheels.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

@WebServlet({ "/AjaxServlet","/AjaxServlet.do"})
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("success");
		System.out.println(request.getParameter("user_name"));
		User user=new User();
		user.setUser_name(request.getParameter("user_name"));
		HttpSession httpSession = request.getSession();
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			List<User> userList = session.createCriteria(User.class)
					.add(Restrictions.eq("user_name", user.getUser_name()))
					.list();
			System.out.println(userList.size());
			if(userList.size()> 0) {
				response.setCharacterEncoding("utf-8");

				String rand = "exist";
				response.getWriter().print(rand);
				return;		
			}
			else {
				String rand = "not exist";
				response.getWriter().print(rand);
				return;
			}		
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

}
