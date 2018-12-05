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

import com.albany.foodOnWheels.model.FoodFestival;
import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;
import com.services.EmailService;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/EmailServlet", "/EmailServlet.do" })
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *///	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside email servlet");
		String user=request.getParameter("user_name");
//		int i=Integer.parseInt(eventid);
		System.out.println(user);
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			String approve=null;
			if(request.getParameter("approval")==null) {
				approve =request.getParameter("reject");
			}else {
				approve =request.getParameter("approval");
			}
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			HttpSession httpsession = request.getSession();
			//String ok="";
			
				List<FoodTruckOwner> ftlist=session.createCriteria(FoodTruckOwner.class)
					.add(Restrictions.eq("truck_name", user)).list();
				
				FoodTruckOwner foodtruckown=ftlist.get(0);
				
				if(approve.equals("approved")){
					foodtruckown.setApproved("approved");
				}
				else {
					System.out.println("event rejected");
					foodtruckown.setApproved("rejected");
				}
				
				
				
				//EmailService es=new EmailService(); System.out.println(u1.getEmail());//	String currentstatus="";
				String truckname=foodtruckown.getTruck_name();
				session.update(foodtruckown);
				tx.commit();
				
				tx = session.beginTransaction();
				List<User> userlist = session.createCriteria(User.class)
					.add(Restrictions.eq("user_name", user)).list();
				User u1=userlist.get(0);	
				EmailService es=new EmailService(); System.out.println(u1.getEmail());//	String currentstatus="";

				String s[]=u1.getEmail().split("@");
				if(!s[1].equals("gmail.com")) {
				System.out.println("email id not correct");
				}
				else {
				es.send(u1.getEmail(),approve, "foodtruck");
					System.out.println("email sent successfully");
				//es.send(fto.getEmail(),approve, "event");
				}
				session.update(u1);
				tx.commit();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Request_center");
				dispatcher.forward(request, response);
					
		}
		catch (Exception e) {
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

