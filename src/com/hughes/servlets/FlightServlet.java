package com.hughes.servlets;

import java.io.IOException;

import com.hughes.beans.*;
import com.hughes.models.*;

import java.text.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FlightServlet
 */
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FlightServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  searchFlights(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  searchFlights(request, response);
	}
	
	private void searchFlights(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
      
      String flight_type = request.getParameter("flight_type");
      Date output_date = new Date();
      Date arrival_date = new Date();
      
      try {
        output_date = date_format.parse(request.getParameter("output_date"));
        arrival_date = date_format.parse(request.getParameter("arrival_date"));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      
      Integer origin_id = Integer.parseInt(request.getParameter("origin"));
      Integer destination_id = Integer.parseInt(request.getParameter("destination"));
      
      String flight_class = request.getParameter("flight_class");

      Integer adult_passages_count = Integer.parseInt(request.getParameter("adult_passages_count"));
      Integer child_passages_count = Integer.parseInt(request.getParameter("child_passages_count"));
      
      Integer passages_count = adult_passages_count + child_passages_count;
      
      Collection<Flight> results = (Vector<Flight>)FlightBean.search(flight_type, origin_id, destination_id, output_date, arrival_date, flight_class, passages_count);

      Origin origin = OriginBean.get(origin_id);
      Destination destination = DestinationBean.get(destination_id);
      
      request.getSession().setAttribute("flight_results", results);
      request.getSession().setAttribute("search_origin", origin);
      request.getSession().setAttribute("search_destination", destination);
      request.getSession().setAttribute("adult_passages_count", adult_passages_count);
      request.getSession().setAttribute("child_passages_count", child_passages_count);

      request.getRequestDispatcher("/flight_results.jsp").forward(request, response);
	}

}
