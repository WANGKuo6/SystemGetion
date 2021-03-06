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
 * Servlet implementation class joueurServlet
 */
public class joueurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joueurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomJoueur = request.getParameter("searchInfo");
		String button = request.getParameter("button");
		Joueur joueur = null;
		if(nomJoueur != null && "search".equals(button)) {
			joueur = searchJoueur(nomJoueur);
			Equipe equipe = null;
			if(joueur!=null) {
				equipe =	searchEquipe(joueur.getEquipe_idEquipe());
			}		
			request.setAttribute("equipe",equipe );
			request.setAttribute("joueurInfo", joueur);
			request.getRequestDispatcher("view/joueurView.jsp").forward(request,response);
		}else if(request.getParameter("chk") != null && "delete".equals(button)) {
			int delInfo = Integer.parseInt(request.getParameter("chk"));  
			if(deleteJoueur(delInfo)) {
				request.setAttribute("message", "delSuccess");
				request.getRequestDispatcher("SystemServlet?element=joueur").forward(request,response);
			}else {
				request.setAttribute("message", "delFailed");
				request.getRequestDispatcher("view/equipeView.jsp").forward(request,response);
			}
		}else if("add".equals(button)) {
			request.setAttribute("showAdd", "showAdd");
			request.getRequestDispatcher("view/joueurView.jsp").forward(request,response);
		}else if("submitAdd".equals(button)) {
			int idJoueurAdd = Integer.parseInt(request.getParameter("idJoueur"));
			String nomJoueurAdd = request.getParameter("Nomjoueur");
			int butAdd = Integer.parseInt(request.getParameter("But"));
			String roleAdd = request.getParameter("Role");
			Float notePreseAdd = Float.parseFloat(request.getParameter("NotePrese"));
			String nomEquipeAdd = request.getParameter("idEquipe");
			int idEquipeAdd = searchIdBynom(nomEquipeAdd);
			joueur = new Joueur();
			joueur.setIdjoueur(idJoueurAdd);
			joueur.setNomJoueur(nomJoueurAdd);
			joueur.setBut(butAdd);
			joueur.setRole(roleAdd);
			joueur.setNotePrese(notePreseAdd);
			joueur.setEquipe_idEquipe(idEquipeAdd);
			if(addJoueur(joueur)) {
				request.getRequestDispatcher("SystemServlet?element=joueur").forward(request,response);
			}
		}else if("edit".equals(button)) {
			String edit = request.getParameter("chk");
			if(edit == null) {
				request.getRequestDispatcher("view/wrong.jsp").forward(request,response);
			}else {
				Joueur joueurEdit = searchJoueur(Integer.parseInt(edit));
				String nomEquipeEdit = searchNomById(joueurEdit.getEquipe_idEquipe());
				request.setAttribute("joueurEdit", joueurEdit);
				request.setAttribute("nomEquipeEdit", nomEquipeEdit);
				request.getRequestDispatcher("view/joueurView.jsp").forward(request,response);
			}
		}else if("submitedit".equals(button)) {
			int editIdJoueur = Integer.parseInt(request.getParameter("editIdJoueur"));
			String editNomjoueur = request.getParameter("editNomjoueur");
			int editBut = Integer.parseInt(request.getParameter("editBut"));
			String editRole = request.getParameter("editRole");
			Float editNotePrese = Float.parseFloat(request.getParameter("editNotePrese"));
			String editNomEquipe = request.getParameter("editNomEquipe");
			int editIdEquipe = searchIdBynom(editNomEquipe);
			joueur = new Joueur();
			joueur.setIdjoueur(editIdJoueur);
			joueur.setNomJoueur(editNomjoueur);
			joueur.setBut(editBut);
			joueur.setRole(editRole);
			joueur.setNotePrese(editNotePrese);
			joueur.setEquipe_idEquipe(editIdEquipe);
			if(editJoueur(joueur)) {
				request.setAttribute("message", "editSuccess");
				request.getRequestDispatcher("SystemServlet?element=joueur").forward(request,response);
			}
		}else if("ListAll".equals(button)) {
			//JoueurDao joueurDao = new JoueurDao();
			//List<Joueur> joueurs = joueurDao.getJoueurs();
			//request.setAttribute("joueurs", joueurs);
			request.getRequestDispatcher("SystemServlet?element=joueur").forward(request,response);
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
	
	private boolean addJoueur(Joueur joueurAdd) {
		JoueurDao joueurDao = new JoueurDao();
		return joueurDao.addJoueur(joueurAdd);
	}
	
	private Joueur searchJoueur(String nomJoueur) {
		JoueurDao joueurDao = new JoueurDao();
		Joueur joueur = joueurDao.searchJoueur(nomJoueur);
		return joueur;
	} 
	
	private Joueur searchJoueur(int idJoueur) {
		JoueurDao joueurDao = new JoueurDao();
		Joueur joueur = joueurDao.searchJoueur(idJoueur);
		return joueur;
	}
	
	private boolean deleteJoueur(int paramId) {
		JoueurDao joueurDao = new JoueurDao();
		return joueurDao.deleteJoueur(paramId);
	}
	
	private boolean editJoueur(Joueur joueur) {
		JoueurDao joueurDao = new JoueurDao();
		return joueurDao.editJoueur(joueur);
	}
	
	private int searchIdBynom(String paramNom) {
		JoueurDao joueurDao = new JoueurDao();
		return joueurDao.searchIdBynom(paramNom);
	}
	
	private String searchNomById(int paramId) {
		JoueurDao joueurDao = new JoueurDao();
		return joueurDao.searcheEquipeByid(paramId);
	}
	
	private Equipe searchEquipe(int idEquipe) {
		EquipeDao equipeDao = new EquipeDao();
		Equipe equipe = equipeDao.searchEquipe(idEquipe);
		return equipe;
	}
	
}
