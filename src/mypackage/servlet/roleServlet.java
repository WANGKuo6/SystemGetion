package mypackage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.EquipeDao;
import mypackage.model.Equipe;

/**
 * Servlet implementation class roleServlet
 */
public class roleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public roleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("role");
		if("admin".equals(role)) {
			Cookie cookie = new Cookie("role","admin");
			cookie.setPath("/");
			response.addCookie(cookie);
			request.getRequestDispatcher("view/login.jsp").forward(request,response);
		}else if("visiter".equals(role)) {
			Cookie cookie = new Cookie("role","visiter");
			cookie.setPath("/");
			response.addCookie(cookie);
			request.getRequestDispatcher("view/welcome.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
