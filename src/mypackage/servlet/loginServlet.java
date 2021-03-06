package mypackage.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.LoginDao;
import mypackage.model.Login;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 String name = request.getParameter("login");
		 String pwd = request.getParameter("password");
		 
		 Login login = new Login(name, pwd);
		 LoginDao loginDao = new LoginDao();
		 int result = loginDao.login(login);
		 if(result > 0 ) {
			 Cookie cookie1 = new Cookie("login",name);
			 cookie1.setPath("/");
			 response.addCookie(cookie1);
			 Cookie cookie2 = new Cookie("password",pwd);
			 cookie2.setPath("/");
			 response.addCookie(cookie2);
			 request.getRequestDispatcher("view/welcome.jsp").forward(request,response);
		 }else {
			 request.getRequestDispatcher("view/404.jsp").forward(request,response);
		 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
