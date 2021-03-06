package mypackage.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.EquipeDao;
import mypackage.dao.MatchDao;
import mypackage.model.Equipe;
import mypackage.model.Match;

/**
 * Servlet implementation class matchServlet
 */
public class matchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public matchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomEquipe = request.getParameter("searchInfo");
		String button = request.getParameter("button");
		List<Match> matchs = null;
		//Match match;
		Match match = null;
		if(nomEquipe != null && "search".equals(button)) {
			MatchDao matchDao = new MatchDao();
			matchs = searchMarchByEN(nomEquipe);
			if(matchs!=null) {
				List<String> nomsEquipes1 = matchDao.getNomsEquipesSearch(0,nomEquipe);
				List<String> nomsEquipes2 = matchDao.getNomsEquipesSearch(1,nomEquipe);
				request.setAttribute("equipes1",nomsEquipes1 );
				request.setAttribute("equipes2",nomsEquipes2 );
				request.setAttribute("matchsInfo", matchs);
			}
			request.getRequestDispatcher("view/matchView.jsp").forward(request,response);
		}else if(request.getParameter("chk") != null && "delete".equals(button)) {
			int delInfo = Integer.parseInt(request.getParameter("chk"));  
			if(deleteMatch(delInfo)) {
				request.setAttribute("message", "delSuccess");
				request.getRequestDispatcher("SystemServlet?element=match").forward(request,response);
			}else {
				request.setAttribute("message", "delFailed");
				request.getRequestDispatcher("view/matchView.jsp").forward(request,response);
			}
		}else if("add".equals(button)) {
			request.setAttribute("showAdd", "showAdd");
			request.getRequestDispatcher("view/matchView.jsp").forward(request,response);
		}else if("submitAdd".equals(button)) {
			int idMatch = Integer.parseInt(request.getParameter("idmatch"));
			String nomMatchAdd = request.getParameter("nomMatch");
			Date utilDate = null;
			try {
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			String ville = request.getParameter("ville");
			String stade = request.getParameter("stade");
			String nomEquipe1 = request.getParameter("equipe1");
			String nomEquipe2 = request.getParameter("equipe2");
			EquipeDao equipeDao = new EquipeDao();
			Equipe equipe1 = equipeDao.searchEquipe(nomEquipe1);
			int idEquipe1 = equipe1.getIdEquipe();
			Equipe equipe2 = equipeDao.searchEquipe(nomEquipe2);
			int idEquipe2 = equipe2.getIdEquipe();
			int pointEquipe1 = Integer.parseInt(request.getParameter("point1"));
			int pointEquipe2 = Integer.parseInt(request.getParameter("point2"));
			
			match = new Match();
			match.setIdMatch(idMatch);
			match.setNomMatch(nomMatchAdd);
			match.setDate(date);
			match.setVille(ville);
			match.setStade(stade);
			match.setIdEquipe1(idEquipe1);
			match.setIdEquipe2(idEquipe2);
			match.setPointEquipe1(pointEquipe1);
			match.setPointEquipe2(pointEquipe2);
			addMatch(match);
			request.getRequestDispatcher("SystemServlet?element=match").forward(request,response);
		}else if("edit".equals(button)&& request.getParameter("chk") != null) {
			String edit = request.getParameter("chk");
				request.setAttribute("matchEdit", "alertVide");	
			Match matchEdit = searchMatch(Integer.parseInt(edit));
			Equipe equipe1 = searchEquipe(matchEdit.getIdEquipe1());
			Equipe equipe2 = searchEquipe(matchEdit.getIdEquipe2());
			request.setAttribute("equipe1",equipe1 );
			request.setAttribute("equipe2",equipe2 );
			request.setAttribute("matchEdit", matchEdit);	
			request.getRequestDispatcher("view/matchView.jsp").forward(request,response);
		}else if("submitEdit".equals(button)) {
			String editIdMatch = request.getParameter("editIdMatch");
			String editNomMatch = request.getParameter("editNomMatch");
			String date = request.getParameter("editDate");
			String editVille = request.getParameter("editVille");
			String editStade = request.getParameter("editStade");
			String nomEquipe1 = request.getParameter("editEquipe1");
			String nomEquipe2 = request.getParameter("editEquipe2");
			EquipeDao equipeDao = new EquipeDao();
			Equipe equipe1 = equipeDao.searchEquipe(nomEquipe1);
			int idEquipe1 = equipe1.getIdEquipe();
			Equipe equipe2 = equipeDao.searchEquipe(nomEquipe2);
			int idEquipe2 = equipe2.getIdEquipe();
			String editPoint1 = request.getParameter("editPoint1");
			String editPoint2 = request.getParameter("editPoint2");

			Date utilDate = null;
			try {
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date editdate = new java.sql.Date(utilDate.getTime());
			match = new Match();
			match.setIdMatch(Integer.parseInt(editIdMatch));
			match.setNomMatch(editNomMatch);
			match.setDate(editdate);
			match.setVille(editVille);
			match.setStade(editStade);
			match.setIdEquipe1(idEquipe1);
			match.setIdEquipe2(idEquipe2);
			match.setPointEquipe1(Integer.parseInt(editPoint1));
			match.setPointEquipe2(Integer.parseInt(editPoint2));
			
			if(editMatch(match)) {
				request.setAttribute("message", "editSuccess");
				request.getRequestDispatcher("SystemServlet?element=match").forward(request,response);
			}
			
		}else if("ListAll".equals(button)) {
			request.getRequestDispatcher("SystemServlet?element=match").forward(request,response);
		}

		if("edit".equals(button) && request.getParameter("chk") == null) {
			request.getRequestDispatcher("view/wrong.jsp").forward(request,response);
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
	
	private Match searchMatch(int idMatch) {
		MatchDao matchDao = new MatchDao();
		Match match = matchDao.searchMatch(idMatch);
		return match;
	}
	
	private Match searchMatch(String nomMatch) {
		MatchDao matchDao = new MatchDao();
		Match match = matchDao.searchMatch(nomMatch);
		return match;
	}
	

	private boolean deleteMatch(int paramId) {
		MatchDao matchDao = new MatchDao();
		return matchDao.deleteMatch(paramId);
	}
	
	private boolean addMatch(Match match) {
		MatchDao matchDao = new MatchDao();
		return matchDao.addMatch(match);
	}
	
	private boolean editMatch(Match match) {
		MatchDao  matchDao = new MatchDao();
		return matchDao.editMatch(match);
	}
	
	private Equipe searchEquipe(int idEquipe) {
		EquipeDao equipeDao = new EquipeDao();
		Equipe equipe = equipeDao.searchEquipe(idEquipe);
		return equipe;
	}
	
	private List<Match> searchMarchByEN(String nomEquipe){
		MatchDao matchDao = new MatchDao();
		List<Match> matchs = matchDao.searchMatchsByEquipe(nomEquipe);
		return matchs;
	}


}
