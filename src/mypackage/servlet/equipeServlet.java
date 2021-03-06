package mypackage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypackage.dao.EquipeDao;
import mypackage.dao.JoueurDao;
import mypackage.model.Equipe;
import mypackage.model.Joueur;

/**
 * Servlet implementation class equipeServlet
 */
public class equipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public equipeServlet() {
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
		Equipe equipe = null;
		if(nomEquipe != null && "search".equals(button)) {
			equipe = searchEquipe(nomEquipe);
			request.setAttribute("equipeInfo", equipe);
			request.getRequestDispatcher("view/equipeView.jsp").forward(request,response);
		}else if(request.getParameter("chk") != null && "delete".equals(button)) {
			int delInfo = Integer.parseInt(request.getParameter("chk"));  
			if(deleteEquipe(delInfo)) {
				request.setAttribute("message", "delSuccess");
				request.getRequestDispatcher("SystemServlet?element=equipe").forward(request,response);
			}else {
				request.setAttribute("message", "delFailed");
				request.getRequestDispatcher("view/equipeView.jsp").forward(request,response);
			}
		}else if("add".equals(button)) {
			request.setAttribute("showAdd", "showAdd");
			request.getRequestDispatcher("view/equipeView.jsp").forward(request,response);
		}else if("submitAdd".equals(button)) {
			int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
			String nomEquipeAdd = request.getParameter("nomEquipe");
			int nbParticipation = Integer.parseInt(request.getParameter("nbParticipation"));
			int nbVictoire = Integer.parseInt(request.getParameter("nbVictoire"));
			equipe = new Equipe();
			equipe.setIdEquipe(idEquipe);
			equipe.setNomEquipe(nomEquipeAdd);
			equipe.setNbParticipation(nbParticipation);
			equipe.setNbVictore(nbVictoire);
			addEquipe(equipe);
			request.getRequestDispatcher("SystemServlet?element=equipe").forward(request,response);
		}else if("edit".equals(button)&& request.getParameter("chk") != null) {
			String edit = request.getParameter("chk");
				request.setAttribute("equipeEdit", "alertVide");	
			Equipe equipeEdit = searchEquipe(Integer.parseInt(edit));
			request.setAttribute("equipeEdit", equipeEdit);	
			request.getRequestDispatcher("view/equipeView.jsp").forward(request,response);
		}else if("submitEdit".equals(button)) {
			String editIdequipe = request.getParameter("editIdEquipe");
			String editNomEquipe = request.getParameter("editNomEquipe");
			String editNbParticipation = request.getParameter("editNbParticipation");
			String editNbVictoire = request.getParameter("editNbVictoire");
			equipe = new Equipe();
			equipe.setIdEquipe(Integer.parseInt(editIdequipe));
			equipe.setNomEquipe(editNomEquipe);
			equipe.setNbParticipation(Integer.parseInt(editNbParticipation));
			equipe.setNbVictore(Integer.parseInt(editNbVictoire));
			if(editEquipe(equipe)) {
				request.setAttribute("message", "editSuccess");
				request.getRequestDispatcher("SystemServlet?element=equipe").forward(request,response);
			}
			
		}else if("ListAll".equals(button)) {
			EquipeDao equipeDao = new EquipeDao();
			List<Equipe> equipes = equipeDao.getEquipes();
			request.setAttribute("equipes", equipes);
			request.getRequestDispatcher("view/equipeView.jsp").forward(request,response);
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
	
	private Equipe searchEquipe(String nomEquipe) {
		EquipeDao equipeDao = new EquipeDao();
		Equipe equipe = equipeDao.searchEquipe(nomEquipe);
		return equipe;
	}
	
	private Equipe searchEquipe(int idEquipe) {
		EquipeDao equipeDao = new EquipeDao();
		Equipe equipe = equipeDao.searchEquipe(idEquipe);
		return equipe;
	}
	
	private boolean deleteEquipe(int paramId) {
		EquipeDao equipeDao = new EquipeDao();
		return equipeDao.deleteEquipe(paramId);
	}
	
	private boolean addEquipe(Equipe equipe) {
		EquipeDao equipeDao = new EquipeDao();
		return equipeDao.addEquipe(equipe);
	}
	
	private boolean editEquipe(Equipe equipe) {
		EquipeDao  equipeDao = new EquipeDao();
		return equipeDao.editEquipe(equipe);
	}

}
