package com.hughes.servlets.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hughes.beans.UserBean;
import com.hughes.models.User;
import com.hughes.servlets.ServletUtilities;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.getSession().setAttribute("user_profile_message", null);
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
          request.getRequestDispatcher("/edit_profile.jsp").forward(request, response);
        }
      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String first_name = request.getParameter("first_name");
	  String last_name = request.getParameter("last_name");
	  String dni = request.getParameter("dni");
	  
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
          
          current_user.setFirstName(first_name);
          current_user.setLastName(last_name);
          current_user.setDni(dni);
          
          if(UserBean.update(current_user))
            request.getSession().setAttribute("user_profile_message", "Perfil editado con exito");
          else
            request.getSession().setAttribute("user_profile_message", "No se pudo editar el perfil");
          
          request.getRequestDispatcher("/edit_profile.jsp").forward(request, response);
        }
      }
	}

}
