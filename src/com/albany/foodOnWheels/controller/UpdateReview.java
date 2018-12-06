package com.albany.foodOnWheels.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.albany.foodOnWheels.model.Reviews;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class Listen
 */
@WebServlet({ "/UpdateReview", "/UpdateReview.do" })
public class UpdateReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateReview() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

 		HttpSession httpSession = request.getSession();
		String user_name = (String) httpSession.getAttribute("user_name");
		String email = "";
		String truck_name = (String) httpSession.getAttribute("truckName");
		System.out.println("truckname in update" + truck_name);
		//String comments = request.getParameter("comments");
		
		//System.out.println(comments);
		//String date_posted = request.getParameter(date_posted);
		Session session = null;
		Transaction tx = null;

		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<User> userList = session.createCriteria(User.class).add(Restrictions.eq("user_name", user_name)).list();
			User user=userList.get(0);
			
			
			
			
			
				email = user.getEmail();
				System.out.println(email);

			
			List<Reviews> reviewList = session.createCriteria(Reviews.class).add(Restrictions.eq("truck_name", truck_name )).list();
			String comments = request.getParameter("comments");
			String [] commentsList = comments.split( ",");
			for(int i =0; i<commentsList.length;i++) {
				commentsList[i] = commentsList[i] +1;
				System.out.println(commentsList[i]);
		}
			List<Reviews> list = new ArrayList<Reviews>();	
			
			if(reviewList.size() > 0) {
				Reviews rev = reviewList.get(0);
				
				rev.setComments(rev.getComments() + "," + comments);
				rev.setTruck_name(truck_name);
				//rev.setComments(comments);	
				rev.setCommented_by(user_name);
				
				session.update(rev);
				session.getTransaction().commit();
				
//				request.setAttribute("reviewList", reviewList);
				list.add(rev);
			}

	  
			else {
			Reviews rev = new Reviews();
			rev.setCommented_by(user_name);
			rev.setTruck_name(truck_name);
			rev.setCommented_by_email(email);
			rev.setComments(comments);
			//rev.setDate_posted(date_posted);
		
		
			session.save(rev);
		session.getTransaction().commit();
		list.add(rev);
	
			}
       //request.setAttribute("truckname", truck_name);					

			/*RequestDispatcher dispatcher =request.getRequestDispatcher("/jsps/reviews.jsp");
			dispatcher.forward(request, response);
			request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
			*/
            
			
			request.setAttribute("reviewList", list);	
			//request.setAttribute("reviewList",reviewList);

			
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

}




