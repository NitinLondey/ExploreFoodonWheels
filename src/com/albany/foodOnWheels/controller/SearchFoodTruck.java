package com.albany.foodOnWheels.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxyHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.albany.foodOnWheels.constants.Constant;
import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.User;
import com.services.Connection;

/**
 * Servlet implementation class SearchFoodTruck
 */
@WebServlet({ "/SearchFoodTruck", "/SearchFoodTruck.do" })
public class SearchFoodTruck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFoodTruck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int zipcode = (Integer) request.getSession().getAttribute("zipcode");
		String api_key = Constant.api_key;
		String url ="https://www.zipcodeapi.com/rest/" + api_key + "/radius.json/"+zipcode+"/10/mile?minimal";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer urlResponse = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	    	 urlResponse.append(inputLine);
	     }
	     in.close();
	     //print in String
	     
	     //Read JSON response and print
	     JSONArray jsonArray;
	     Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = Connection.getSessionFactory();
		HttpSession httpSession = request.getSession();
		try {
			JSONObject obj1 = new JSONObject(urlResponse.toString());
			
			jsonArray = new JSONArray(obj1.get("zip_codes").toString());
			ArrayList<Integer> zipcodeNearUser = new ArrayList<Integer>();
			if (jsonArray != null) {

				for (int i = 0; i < jsonArray.length(); i++) {
					zipcodeNearUser.add(Integer.parseInt((String)jsonArray.get(i)));
				}
			}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			
		Query query	= session.createQuery("select food_truck from FoodTruckOwner as food_truck where food_truck.zip_code in (:zipcodes)").setParameterList("zipcodes", zipcodeNearUser);
		
		List<FoodTruckOwner> food_truck = query.list();
		food_truck.removeIf(s -> s.isIs_moving());
		
		httpSession.setAttribute("foodTruckList", food_truck);
		request.setAttribute("search_message", "Showing results for FoodTruck within 10 miles of "+zipcode +" ... ");
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/searchFoodTruck.jsp");
		dispatcher.forward(request, response);
		return;
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
