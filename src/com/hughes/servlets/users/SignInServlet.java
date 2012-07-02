package com.hughes.servlets.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.hughes.models.*;
import com.hughes.beans.*;

/**
 * Servlet implementation class SignInServlet
 */
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String referer = request.getHeader("Referer");
	  
	  if(referer == null)
	    referer = request.getContextPath();
	  
	  request.getSession().setAttribute("referer", referer);
	  
	  request.getRequestDispatcher("/sign_in.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	  String email = request.getParameter("email");
  	  String password = request.getParameter("password");
  	  User signed_user = SessionBean.signIn(email, password);
  	  if(signed_user == null){
  	    request.getSession().setAttribute("sign_in_error", "Error al ingresar usuario / contraseña. Intente de nuevo");
  	    request.getRequestDispatcher("/sign_in.jsp").forward(request, response);
  	  }
  	  else {
  	    Cookie user_cookie = new Cookie("current_user", String.valueOf(signed_user.getId()));
  	    user_cookie.setValue(String.valueOf(signed_user.getId()));
  	    user_cookie.setPath(request.getContextPath() + "/");
  	    user_cookie.setMaxAge(60*60*24*365);
  	    
  	    response.addCookie(user_cookie);
  	    response.sendRedirect(request.getSession().getAttribute("referer").toString());
  	  }
	}

}
