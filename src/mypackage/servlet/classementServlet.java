package mypackage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypackage.dao.ClassementDao;
import mypackage.model.Classement;
/**
 * Servlet implementation class classementServlet
 */
public class classementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public classementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("classement");
		List<Classement> classements = searchClassements(Integer.parseInt(id));
		if(classements != null) {
			request.setAttribute("classements", classements);
			request.getRequestDispatcher("view/classementView.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * trouver les classements de certaine equipe
	 * @param paramId id d'equipe
	 * @return list de classements
	 */
	private List<Classement> searchClassements(int paramId) {
		ClassementDao classementDao = new ClassementDao();
		return classementDao.searchClassements(paramId);
	}

}
