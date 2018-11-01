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
			
				
				user.setFirstname(request.getParameter("first_name"));
				user.setLastname(request.getParameter("last_name"));
				user.setAddress_line1(request.getParameter("address1"));
				user.setAddress_line_2(request.getParameter("address2"));
				user.setCity(request.getParameter("city"));
				user.setState(request.getParameter("state"));
				if(register.equals("user")) {
					user.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
					httpSession.setAttribute("zipcode", Integer.parseInt(request.getParameter("zipcode")));	
				}
				user.setPhone(request.getParameter("phone"));
				session.update(user);
				tx.commit();
				
				
				

				
				if(register.equals("truck")) {
					tx = session.beginTransaction();
					FoodTruckOwner truck_owner = new FoodTruckOwner();
					List<FoodTruckOwner> foodTruckOwnerList = session.createCriteria(FoodTruckOwner.class)
						.list();
					for(FoodTruckOwner foodtruckowner : foodTruckOwnerList) {
						//System.out.println(foodtruckowner.getUser().getUser_name());
						if(foodtruckowner.getUser().getUser_name().equals(user.getUser_name())) {
							//System.out.println("truck find");
							truck_owner = foodtruckowner;							}
					}
					//System.out.println("profile2"+userList.size());
					session.persist(user);
				
					
					truck_owner.setPhone(request.getParameter("phone"));
					
					
					String[] cuisine = request.getParameterValues("cuisine"); 
					truck_owner.setCuisine(String.join(",",cuisine ));
					
					String[] days = request.getParameterValues("days");
					truck_owner.setDays(String.join(",",days ));
					truck_owner.setWeekday_time( request.getParameter("week_day"));
					truck_owner.setWeekend_time( request.getParameter("week_end"));
					String[] payment = request.getParameterValues("payment");
					
					truck_owner.setAccepted_payments(String.join(",",payment ));
					truck_owner.setAddress_line_1(request.getParameter("address1"));
					truck_owner.setAddress_line_2(request.getParameter("address2"));
					truck_owner.setCity(request.getParameter("city"));
					truck_owner.setState(request.getParameter("state"));
					session.update(truck_owner);
					session.getTransaction().commit();
					
					System.out.println("Go to  truck profile");
					request.setAttribute("user", user);
					request.setAttribute("msg", "Your profile is updated successfully!");
					request.setAttribute("truck", truck_owner);
					request.setAttribute("cuisine", truck_owner.getCuisine());
					request.setAttribute("days", truck_owner.getDays());
					request.setAttribute("payments", truck_owner.getAccepted_payments());
					request.setAttribute("fileName", truck_owner.getImage_path());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/profile-truckowner.jsp");
					dispatcher.forward(request, response);
					return;	
					
				}
				else {
				System.out.println("Go to  user profile");
				request.setAttribute("user", user);
				request.setAttribute("msg", "Your profile is updated successfully!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/profile.jsp");
				dispatcher.forward(request, response);
				return;	
				}
				
		} catch (Exception e) {
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
		
	}

}
