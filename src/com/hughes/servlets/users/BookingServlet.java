package com.hughes.servlets.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hughes.models.*;
import com.hughes.beans.*;
import com.hughes.servlets.ServletUtilities;

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
	  String current_user_id = ServletUtilities.getCookieValue(request.getCookies(), "current_user");
	  
	  if(current_user_id == null){
	    response.sendRedirect(request.getContextPath());
	  }
	  else {
	    User current_user = UserBean.get(Integer.parseInt(current_user_id));
	    if(current_user == null){
	      response.sendRedirect(request.getContextPath());
	    }
	    else {
	      request.getSession().setAttribute("current_user", current_user);
	      
	      request.getRequestDispatcher("/bookings.jsp").forward(request, response);
	    }
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
