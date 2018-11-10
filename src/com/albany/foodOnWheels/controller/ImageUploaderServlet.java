package com.albany.foodOnWheels.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
import com.services.Connection;

import java.io.PrintWriter;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet({ "/ImageUploaderServlet", "/ImageUploaderServlet.do" })
public class ImageUploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String directory;
	private int maxFileSize = 700 * 1024;
	private int maxMemSize = 10 * 1024;
	private File file;

	public ImageUploaderServlet() {
		super();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		SessionFactory sessionFactory = Connection.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {

			String cuisine = request.getParameter("cuisinename");
			System.out.println("cuisine is" + cuisine);
			HttpSession httpSession = request.getSession();
			String truckowner = (String) httpSession.getAttribute("user_name");
			directory = request.getSession().getServletContext().getRealPath("/") + "images\\";
			String s = request.getContextPath();
			System.out.println(s);
			ServletFileUpload.isMultipartContent(request);
			response.setContentType("image");
			//PrintWriter out = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			// factory.setRepository(new File("c:\\temp"));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(maxFileSize);
			String filepath = "";
			String fileName = "";
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					fileName = fi.getName();
					System.out.println("fileName" + fileName);
					System.out.println("file path" + filepath);
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(directory + fileName.substring(fileName.lastIndexOf("\\")));

					} else {
						file = new File(directory + fileName.substring(fileName.lastIndexOf("\\") + 1));

					}
					fi.write(file);

				}

			}

			List<Menu> menudetails = session.createCriteria(Menu.class).list();
			List<String> truckownerlistpresent = new ArrayList<>();
			boolean flag = false;
			boolean commitedonce = false;
			if (!menudetails.isEmpty()) {
				for (Menu menudata : menudetails) {
					truckownerlistpresent.add(menudata.getTruck_name());
				}
			}

			if (menudetails.isEmpty()) {
				Menu menu = new Menu();
				menu.setTruck_name(truckowner);
				menu.setImage_path(fileName);
				session.save(menu);
				session.getTransaction().commit();
				session.close();
			} else {
				for (Menu m : menudetails) {

					if (truckowner != null) {
						if (m.getTruck_name().equals(truckowner)) {
							if (m.getImage_path().equals(fileName)) {
								// do nothing

								System.out.println("same file");
							} else {
								// update
								System.out.println("update existing");

								Criteria criteria = session.createCriteria(Menu.class);
								criteria.add(Restrictions.eq("truck_name", truckowner));
								Menu menu1 = (Menu) criteria.uniqueResult();
								menu1.setImage_path(fileName);
								session.update(menu1);
								session.getTransaction().commit();
							}
						}

						else {
							if (!commitedonce) {

								if (truckownerlistpresent.contains(truckowner)) {
									flag = false;
									System.out.println("check truckowner false" + truckowner);
								} else {
									flag = true;
									System.out.println("inside true" + truckowner);
								}
							}
							if (flag) {

								flag = false;
								commitedonce = true;
								System.out.println("new creation for new truck owner");
								Menu menu = new Menu();
								menu.setTruck_name(truckowner);
								menu.setImage_path(fileName);
								session.save(menu);
								session.getTransaction().commit();
							}
						}
					}
				}
			}
			// request.setAttribute("message", "Menu added");
			List<String> list = (List<String>) httpSession.getAttribute("list");
			request.setAttribute("list", list);

			request.setAttribute("fileName", fileName);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/menuCard.jsp");
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
