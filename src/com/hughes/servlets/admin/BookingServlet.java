package com.hughes.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import com.hughes.models.*;
import com.hughes.beans.*;

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
	  Collection<Booking> bookings;
	  
	  if(request.getParameter("flight_id") == null)
	    bookings = BookingBean.all();
	  else{
	    Flight flight = FlightBean.get(Integer.parseInt(request.getParameter("flight_id")));
	    bookings = flight.getBookings();
	  }
	  
	  request.getSession().setAttribute("bookings", bookings);
	  
	  request.getRequestDispatcher("/admin/bookings/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
