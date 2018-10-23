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

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet({"/EditProfileServlet","/EditProfileServlet.do"})
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/jsps/index.jsp").forward(request, response);
		return;

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String register = request.getParameter("register");
		HttpSession httpSession = request.getSession();
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		try {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				
				
				List<User> userList = session.createCriteria(User.class)
						.add(Restrictions.eq("user_name",httpSession.getAttribute("user_name")))
						.list();
				System.out.println("profile" +userList.size());
				User user=  userList.get(0);
			
				user.setUser_name(request.getParameter("user_name"));
				user.setFirstname(request.getParameter("first_name"));
				user.setLastname(request.getParameter("last_name"));
				user.setAddress_line1(request.getParameter("address1"));
				user.setAddress_line_2(request.getParameter("address2"));
				user.setCity(request.getParameter("city"));
				user.setState(request.getParameter("state"));
				user.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
				user.setPhone(request.getParameter("phone"));
				user.setEmail(request.getParameter("email"));
				tx.commit();
				
				

				
				if(register.equals("truck")) {
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					
						List<FoodTruckOwner> foodTruckOwnerList = session.createCriteria(FoodTruckOwner.class)
							.add(Restrictions.eq("user", user))
							.list();
					System.out.println("profile2"+userList.size());
					session.persist(user);
					FoodTruckOwner truck_owner =  foodTruckOwnerList.get(0);
				
					truck_owner.setTruck_name(request.getParameter("user_name"));
					truck_owner.setZip_code(Integer.parseInt(request.getParameter("zipcode")));
					truck_owner.setPhone(request.getParameter("phone"));
					
					
					String[] cuisine = request.getParameterValues("cuisine"); 
					truck_owner.setCuisine(String.join(",",cuisine ));
					
					String[] days = request.getParameterValues("days");
					truck_owner.setDays(String.join(",",days ));
					truck_owner.setWeekday_time( request.getParameter("week_day"));
					truck_owner.setWeekend_time( request.getParameter("week_end"));
					String[] payment = request.getParameterValues("payment");
					
					truck_owner.setAccepted_payments(String.join(",",payment ));
					truck_owner.setIs_moving(false);
					truck_owner.setAddress_line_1(request.getParameter("address1"));
					truck_owner.setAddress_line_2(request.getParameter("address2"));
					truck_owner.setCity(request.getParameter("city"));
					truck_owner.setState(request.getParameter("state"));
					session.update(truck_owner);
					session.getTransaction().commit();
					
				}
				System.out.println("Go to profile");
				request.setAttribute("user", user);
				doGet(request, response);
					
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
			
		}
		
	}

}
