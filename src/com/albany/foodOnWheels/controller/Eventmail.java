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
import com.services.EmailService;

/**
 * Servlet implementation class Eventmail
 */
@WebServlet("/Eventmail")
public class Eventmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eventmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside email servlet");
		String eventid=request.getParameter("event_id");
//		int i=Integer.parseInt(eventid);
		System.out.println(eventid);
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
			//String role = (String) httpsession.getAttribute("role");
			
				List<FoodFestival> eventlist = session.createCriteria(FoodFestival.class)
						.add(Restrictions.eq("id", Integer.parseInt(request.getParameter("event_id")))).list();
				
				FoodFestival e1=eventlist.get(0);
				if(approve.equals("approved")){
					e1.setApproved("approved");
				}
				else {
					System.out.println("event rejected");
					e1.setApproved("rejected");
				}
				
				EmailService es=new EmailService();
				String truckname=e1.getTruck_name();
				session.update(e1);
				tx.commit();
				
				tx = session.beginTransaction();
				List<User> user = session.createCriteria(User.class)
						.add(Restrictions.eq("user_name", truckname)).list();
				User fto=user.get(0);
				
				String s[]=fto.getEmail().split("@");
				if(!s[1].equals("gmail.com")) {
					System.out.println("email id not correct");
				}
				else {
				es.send(fto.getEmail(),approve, "event");
			
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ViewEventsServlet");
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
