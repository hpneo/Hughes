package com.hughes.servlets.admin;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import com.hughes.models.*;
import com.hughes.beans.*;

/**
 * Servlet implementation class FlightServlet
 */
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String url = "/admin/flights/index.jsp";
	  
	  Collection<Flight> flights = null;
	  
	  if(request.getParameter("id") == null){
	    if(request.getParameter("action") != null && request.getParameter("action").equals("new")){
	      url = "/admin/flights/new.jsp";
	    }
	    else{
	      if(request.getParameter("frequency_id") != null){
	        Frequency frequency = FrequencyBean.get(Integer.parseInt(request.getParameter("frequency_id")));
	        flights = frequency.getFlights();
	      }
	      else {
	        flights = FlightBean.all();
	      }

	      request.getSession().setAttribute("flights", flights);
	    }
	  }
	  else{
	    Flight flight = FlightBean.get(Integer.parseInt(request.getParameter("id")));

	    request.getSession().setAttribute("flight", flight);

	    url = "/admin/flights/show.jsp";
	    if(request.getParameter("action").equals("edit"))
	      url = "/admin/flights/edit.jsp";
	  }
	  request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String code = request.getParameter("code");
	  String flightClass = request.getParameter("flightClass");
      
      BigDecimal price = new BigDecimal(request.getParameter("price"));
      price.setScale(2);
      
      Date outputDate = DateBean.parseDate(request.getParameter("outputDate"));
      
      if(request.getParameter("id") == null){
        Flight flight = new Flight();
        
        Integer availableSeats = Integer.parseInt(request.getParameter("availableSeats"));
        Frequency frequency = FrequencyBean.get(Integer.parseInt(request.getParameter("frequency")));
        
        flight.setCode(code);
        flight.setFlightClass(flightClass);
        flight.setPrice(price);
        flight.setOutputDate(outputDate);
        flight.setAvailableSeats(availableSeats);
        flight.setFrequency(frequency);
        
        FlightBean.save(flight);
      }
      else{
        int id = Integer.parseInt(request.getParameter("id"));
        
        Flight flight = FlightBean.get(id);
        
        flight.setFlightClass(flightClass);
        flight.setPrice(price);
        flight.setOutputDate(outputDate);
        
        FlightBean.update(flight);
      }
      
      response.sendRedirect(request.getContextPath() + "/admin/flights");
	}

}
