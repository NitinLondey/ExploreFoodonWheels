package com.albany.foodOnWheels.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.albany.foodOnWheels.model.Menu;
import com.albany.foodOnWheels.model.Offers;
import com.services.Connection;
import com.services.ImageUpload;

import java.io.PrintWriter;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet({ "/OfferImageUploaderServlet", "/OfferImageUploaderServlet.do" })
@MultipartConfig
public class OfferImageUploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public OfferImageUploaderServlet() {
		super();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		String fileName = ImageUpload.imageUpload(request);
		System.out.println(fileName);
		HttpSession httpSession = request.getSession();
		String truckowner = (String) httpSession.getAttribute("user_name");
		SessionFactory sessionFactory = Connection.getSessionFactory();
		Session session=null;
		try {
			 session = sessionFactory.openSession();
			session.beginTransaction();
			List<Offers> offerdetails = session.createCriteria(Offers.class)
					.add(Restrictions.eq("truck_name", truckowner)).list();

			// update
			if (offerdetails.size() > 0) {
				Offers offers = offerdetails.get(0);
				offers.setImage_path(fileName);
				session.update(offers);
				session.getTransaction().commit();
			}
			// add
			else {
				Offers offers = new Offers();
				offers.setTruck_name(truckowner);
				offers.setImage_path(fileName);
				session.save(offers);
				session.getTransaction().commit();
			}
			request.setAttribute("message", "Offers added successfully");
			request.setAttribute("fileName", fileName);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/offers.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			session.close();

		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

	}
}
