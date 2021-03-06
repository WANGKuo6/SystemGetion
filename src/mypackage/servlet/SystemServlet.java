package mypackage.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.EquipeDao;
import mypackage.model.Equipe;
import mypackage.dao.JoueurDao;
import mypackage.dao.MatchDao;
import mypackage.model.Joueur;
import mypackage.model.Match;

/**
 * Servlet implementation class SystemServlet
 */
public class SystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SystemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String element = request.getParameter("element");
		if("equipe".equals(element)) {
			EquipeDao equipeDao = new EquipeDao();
			List<Equipe> equipes = equipeDao.getEquipes();
			request.setAttribute("equipes", equipes);
			request.getRequestDispatcher("view/equipeView.jsp").forward(request,response);
		}else if("joueur".equals(element)) {
			JoueurDao joueurDao = new JoueurDao();
			List<Joueur> joueurs = joueurDao.getJoueurs();
			List<String> nomEquipes = joueurDao.getNomEquipes();
			request.setAttribute("nomEquipes", nomEquipes);
			request.setAttribute("joueurs", joueurs);
			request.getRequestDispatcher("view/joueurView.jsp").forward(request,response);
		}else if("match".equals(element)){
			MatchDao matchDao = new MatchDao();
			List<Match> matchs = matchDao.getMatchs();
			List<String> nomsEquipes1 = matchDao.getNomsEquipes(0);
			List<String> nomsEquipes2 = matchDao.getNomsEquipes(1);
			request.setAttribute("matchs", matchs);
			request.setAttribute("nomsEquipes1", nomsEquipes1);
			request.setAttribute("nomsEquipes2", nomsEquipes2);
			request.getRequestDispatcher("view/matchView.jsp").forward(request,response);		
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
