package com.hughes.servlets;

import java.io.IOException;

import com.hughes.beans.*;
import com.hughes.models.*;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookingServlet
 */
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  if(ServletUtilities.getCookieValue(request.getCookies(), "current_user") == null){
	    response.sendRedirect("users/sign_in");
	  }
	  else {
	    String[] flight_ids = request.getParameterValues("flight_ids[]");
	    
	    if(flight_ids.length == 0){
	      response.sendRedirect(request.getHeader("Referer"));
	    }
	    else{
	      Collection<Flight> flights = FlightBean.get(flight_ids);
	      request.getSession().setAttribute("flights", flights);
	        
	      if(request.getParameterValues("flight_passenger_names[]") == null) {
	        request.getRequestDispatcher("/new_booking.jsp").forward(request, response);
	      }
	      else {
	        String[] flight_passenger_names = request.getParameterValues("flight_passenger_names[]");
	        
	        Flight[] flight_array = flights.toArray(new Flight[0]);
	        
	        String current_user_id = ServletUtilities.getCookieValue(request.getCookies(), "current_user");
	        
	        User current_user = UserBean.get(Integer.parseInt(current_user_id));
	        
	        for(int i=0; i< flight_passenger_names.length; i++){
	          Flight flight = flight_array[i];
	          Booking booking = new Booking();
	          booking.setFlight(flight);
	          booking.setUser(current_user);
	          booking.setPassengerName(flight_passenger_names[i]);
	          
	          BookingBean.save(booking);
	        }
	        
	        response.sendRedirect(request.getContextPath() + "/panel/bookings");
	      }
	    }
	  }
	}

}
