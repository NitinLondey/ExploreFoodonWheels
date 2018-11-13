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

import com.albany.foodOnWheels.model.Menu;
import com.services.Connection;

/**
 * Servlet implementation class DisplayMenu
 */
@WebServlet({"/Displaymenu","/Displaymenu.do"})
public class DisplayMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
	
		HttpSession httpSession = request.getSession();
		String truckowner = request.getParameter("truckname");
		SessionFactory sessionFactory = Connection.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.beginTransaction();

			List<Menu> listofmenu = session.createCriteria(Menu.class).add(Restrictions.eq("truck_name", truckowner)).list();;
			if(listofmenu.size()>0) {
			request.setAttribute("listofmenu", listofmenu);
			}else {
				System.out.println("no images");
				request.setAttribute("message","Will be updated Shortly " );
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/listMenu.jsp");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
