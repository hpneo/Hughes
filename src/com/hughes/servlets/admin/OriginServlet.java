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
 * Servlet implementation class OriginServlet
 */
public class OriginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OriginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  if(request.getParameter("id") == null){
        Collection<Origin> origins = OriginBean.all();
        
        request.getSession().setAttribute("origins", origins);
        
        request.getRequestDispatcher("/admin/origins/index.jsp").forward(request, response);
      }
      else{
        String url = "/admin/origins/show.jsp";
        if(request.getParameter("action").equals("edit"))
          url = "/admin/origins/edit.jsp";
        
        Origin origin = OriginBean.get(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("origin", origin);
          
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
        Origin origin = new Origin();
        
        origin.setName(name);
        origin.setLat(lat);
        origin.setLng(lng);
        
        OriginBean.save(origin);
      }
      else{
        Origin origin = OriginBean.get(Integer.parseInt(request.getParameter("id")));
        
        origin.setName(name);
        origin.setLat(lat);
        origin.setLng(lng);
        
        OriginBean.update(origin);
        
        response.sendRedirect(request.getContextPath() + "/admin/origins");
      }
	}

}
