package com.hughes.servlets.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import com.hughes.models.*;
import com.hughes.beans.*;

/**
 * Servlet implementation class DestinationServlet
 */
public class DestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  if(request.getParameter("id") == null){
        Collection<Destination> destinations = DestinationBean.all();
        
        request.getSession().setAttribute("destinations", destinations);
        
	    request.getRequestDispatcher("/admin/destinations/index.jsp").forward(request, response);
	  }
	  else{
	    String url = "/admin/destinations/show.jsp";
	    if(request.getParameter("action").equals("edit"))
	      url = "/admin/destinations/edit.jsp";
	    
	    Destination destination = DestinationBean.get(Integer.parseInt(request.getParameter("id")));
	    request.getSession().setAttribute("destination", destination);
	      
	    request.getRequestDispatcher(url).forward(request, response);
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String name = request.getParameter("name");
      BigDecimal lat = new BigDecimal(request.getParameter("lat"));
      lat.setScale(8, RoundingMode.CEILING);
      BigDecimal lng = new BigDecimal(request.getParameter("lng"));
      lat.setScale(8, RoundingMode.CEILING);
	  
	  if(request.getParameter("id") == null){
        Destination destination = new Destination();
        
        destination.setName(name);
        destination.setLat(lat);
        destination.setLng(lng);
        
        DestinationBean.save(destination);
      }
      else{
        Destination destination = DestinationBean.get(Integer.parseInt(request.getParameter("id")));
        
        destination.setName(name);
        destination.setLat(lat);
        destination.setLng(lng);
        
        DestinationBean.update(destination);
        
        response.sendRedirect(request.getContextPath() + "/admin/destinations");
      }
	}

}
