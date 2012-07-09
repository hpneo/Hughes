package com.hughes.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.sql.Time;

import com.hughes.models.*;
import com.hughes.beans.*;

/**
 * Servlet implementation class FrequencyServlet
 */
public class FrequencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrequencyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String url = "/admin/frequencies/index.jsp";
      
	  Collection<Frequency> frequencies = null;
	  
	  if(request.getParameter("id") == null){
	    if(request.getParameter("action") != null && request.getParameter("action").equals("new")){
	      url = "/admin/frequencies/new.jsp";
	    }
	    else{
	      if(request.getParameter("route_id") != null){
	        Route route = RouteBean.get(Integer.parseInt(request.getParameter("route_id")));
	        frequencies = route.getFrequencies();
	      }
	      else{
	        frequencies = FrequencyBean.all();
	      }

	      request.getSession().setAttribute("frequencies", frequencies);
	    }
      }
      else{
        Frequency frequency = FrequencyBean.get(Integer.parseInt(request.getParameter("id")));
        
        request.getSession().setAttribute("frequency", frequency);
        
        url = "/admin/frequencies/show.jsp";
        if(request.getParameter("action").equals("edit"))
          url = "/admin/frequencies/edit.jsp";
      }
	  request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  int duration = Integer.parseInt(request.getParameter("duration"));

      Time output = DateBean.parseTime(request.getParameter("output"));
      Time arrival = DateBean.parseTime(request.getParameter("arrival"));
      
      if(request.getParameter("id") == null){
        Frequency frequency = new Frequency();

        Route route = RouteBean.get(Integer.parseInt(request.getParameter("route")));
        Aircraft aircraft = AircraftBean.get(Integer.parseInt(request.getParameter("aircraft")));
        
        frequency.setDuration(duration);
        frequency.setOutput(output);
        frequency.setArrival(arrival);
        frequency.setRoute(route);
        frequency.setAircraft(aircraft);
        
        FrequencyBean.save(frequency);
      }
      else{
        int id = Integer.parseInt(request.getParameter("id"));
        
        Frequency frequency = FrequencyBean.get(id);
        
        frequency.setDuration(duration);
        frequency.setOutput(output);
        frequency.setArrival(arrival);
        
        FrequencyBean.update(frequency);
      }
      
      response.sendRedirect(request.getContextPath() + "/admin/frequencies");
	}

}
