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
 * Servlet implementation class RouteServlet
 */
public class RouteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RouteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String url = "/admin/routes/index.jsp";
	  if(request.getParameter("id") == null){
        if(request.getParameter("action") != null && request.getParameter("action").equals("new")){
          url = "/admin/routes/new.jsp";
        }
        else{
          Collection<Route> routes = RouteBean.all();
          request.getSession().setAttribute("routes", routes);
        }
      }
      else{
        url = "/admin/routes/show.jsp";
        if(request.getParameter("action").equals("edit"))
          url = "/admin/routes/edit.jsp";
        
        Route route = RouteBean.get(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("route", route);
      }
	  request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  Origin origin = OriginBean.get(Integer.parseInt(request.getParameter("origin")));
	  Destination destination = DestinationBean.get(Integer.parseInt(request.getParameter("destination")));
	  
	  if(request.getParameter("id") == null){
	    Route route = new Route();
	    
	    route.setOrigin(origin);
	    route.setDestination(destination);
	    
	    RouteBean.save(route);
	  }
	  else{
	    Route route = RouteBean.get(Integer.parseInt(request.getParameter("id")));
	    
	    route.setOrigin(origin);
        route.setDestination(destination);
        
        RouteBean.update(route);
	  }
	  response.sendRedirect(request.getContextPath() + "/admin/routes");
	}

}
