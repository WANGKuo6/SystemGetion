package mypackage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.ClassementDao;
import mypackage.dao.JoueurDao;
import mypackage.dao.MatchDao;
import mypackage.model.Classement;
import mypackage.model.Joueur;
import mypackage.model.Match;

/**
 * Servlet implementation class matchJoueurServlet
 */
public class matchJoueurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public matchJoueurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("match");
		Match match = searchMatch(Integer.parseInt(id));
		request.setAttribute("match",match);
		List<Joueur> joueurs = searchJoueurs(Integer.parseInt(id));
		if(joueurs != null) {
			request.setAttribute("joueurss", joueurs);
			request.getRequestDispatcher("view/matchJoueurView.jsp").forward(request,response);
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
	 * les fonctions de traitement des donnes en  utilisant les fonctions dans DAO   
	 **/
	
	private List<Joueur> searchJoueurs(int paramId) {
		JoueurDao joueurDao = new JoueurDao();
		return joueurDao.searchJoueurByMatch(paramId);
	}
	
	private Match searchMatch(int paramId) {
		MatchDao matchDao = new MatchDao();
		return matchDao.searchMatch(paramId);
	}

}
