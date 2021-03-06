package mypackage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import mypackage.model.Equipe;
import mypackage.model.Match;

public class MatchDao extends BaseDao{
	
	/**
	 * obtenir tous les matchs
	 * @return list des matchs
	 */
	public List<Match> getMatchs(){
		String sql = "select * from matchinfo";
		ResultSet rs = query(sql); 
		List<Match> matchs = new ArrayList<Match>();
		try {
			while(rs.next()) {
				Match match = new Match();
				match.setIdMatch(rs.getInt("idmatch"));
				match.setNomMatch(rs.getString("nomMatch"));
				match.setDate(rs.getDate("date"));
				match.setVille(rs.getString("ville"));
				match.setStade(rs.getString("stade"));
				match.setIdEquipe1(rs.getInt("Equipe_idEquipe1"));
				match.setIdEquipe2(rs.getInt("Equipe_idEquipe2"));
				match.setPointEquipe1(rs.getInt("pointEquipe1"));
				match.setPointEquipe2(rs.getInt("pointEquipe2"));
				matchs.add(match);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return matchs;
	}
	
	/**
	 * ajouter un match
	 * @param match
	 * @return true or false
	 */
	public boolean addMatch(Match match) {
		String sql = "insert into matchinfo values(" + match.getIdMatch();
		sql += ",'"+match.getNomMatch()+"'"; 
		sql += ",'"+match.getDate()+"'"; 
		sql += ",'"+match.getVille()+"'"; 
		sql += ",'"+match.getStade()+"'"; 
		sql += ","+match.getIdEquipe1(); 
		sql += ","+match.getIdEquipe2(); 
		sql += ","+match.getPointEquipe1();
		sql += ","+match.getPointEquipe2();
		sql += ")";
		return update(sql);
	}
	
	/**
	 * supprimer un match par id
	 * @param paramId id de match
	 * @return true or false
	 */
	public boolean deleteMatch(int paramId) {
		// TODO Auto-generated method stub
		String sql = "delete from matchinfo where idmatch =" + paramId; 
		return update(sql);
	}
	
	/**
	 * modifier les informations de match
	 * @param match
	 * @return true or false
	 */
	public boolean editMatch(Match match) {
		int id = match.getIdMatch();
		String nomMatch = match.getNomMatch();
		Date Date = match.getDate();
		String ville = match.getVille();
		String stade = match.getStade();
		int idEquipe1 = match.getIdEquipe1();
		int idEquipe2 = match.getIdEquipe2();
		int pointEquipe1 = match.getPointEquipe1();
		int pointEquipe2 = match.getPointEquipe2();
		
	
		String sql = "update matchinfo set idmatch = "+id;
		sql += ",nomMatch = '"+ nomMatch+"'";
		sql += ",date = '"+ Date+"'";
		sql += ",ville = '"+ ville+"'";
		sql += ",stade = '"+ stade+"'";
		sql += ",Equipe_idEquipe1 = "+ idEquipe1;
		sql += ",Equipe_idEquipe2 = "+ idEquipe2;
		sql += ",pointEquipe1 = "+ pointEquipe1;
		sql += ",pointEquipe2 = "+ pointEquipe2;
		sql += " where idmatch = " + id;
		//System.out.println(sql);
		return update(sql);
	}
	
	/**
	 * rechercher un match par id de match
	 * @param idMatch id de match
	 * @return match
	 */
	public Match searchMatch(int idMatch){
		String sql = "select * from matchinfo where idmatch = '" + idMatch +"';";
		ResultSet rs = query(sql); 
		Match match = null;
	
		try {
			while(rs.next()) {
				match = new Match();
				match.setIdMatch(rs.getInt("idmatch"));
				match.setNomMatch(rs.getString("nomMatch"));
				match.setDate(rs.getDate("date"));
				match.setVille(rs.getString("ville"));
				match.setStade(rs.getString("stade"));
				match.setIdEquipe1(rs.getInt("Equipe_idEquipe1"));
				match.setIdEquipe2(rs.getInt("Equipe_idEquipe2"));
				match.setPointEquipe1(rs.getInt("pointEquipe1"));
				match.setPointEquipe2(rs.getInt("pointEquipe2"));
				return match;
				
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return match;
	}
	
	/**
	 * rechercher un match par nom de match
	 * @param idMatch nom de match
	 * @return match
	 */
	public Match searchMatch(String nomMatch){
		String sql = "select * from matchinfo where nomMatch = '" + nomMatch +"';";
		ResultSet rs = query(sql); 
		Match match = null;
		
		try {
			while(rs.next()) {
				match = new Match();
				match.setIdMatch(rs.getInt("idmatch"));
				match.setNomMatch(rs.getString("nomMatch"));
				match.setDate(rs.getDate("date"));
				match.setVille(rs.getString("ville"));
				match.setStade(rs.getString("stade"));
				match.setIdEquipe1(rs.getInt("Equipe_idEquipe1"));
				match.setIdEquipe2(rs.getInt("Equipe_idEquipe2"));
				match.setPointEquipe1(rs.getInt("pointEquipe1"));
				match.setPointEquipe2(rs.getInt("pointEquipe2"));
				return match;
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return match;
	}
	
	
	/**
	 * rechercher les matchs que certain equipe a participe
	 * @param nomEquipe nom d'equipe
	 * @return list de matchs
	 */
	public List<Match> searchMatchsByEquipe(String nomEquipe){
		List<Match> matchs = new ArrayList<Match>();
		EquipeDao equipeDao = new EquipeDao();
		Equipe equipe = equipeDao.searchEquipe(nomEquipe);
		if (equipe!=null) {
			String sql = "select * from matchinfo where Equipe_idEquipe1 = " + equipe.getIdEquipe() + "  OR Equipe_idEquipe2 = '" + equipe.getIdEquipe() +"';";
			ResultSet rs = query(sql); 
			try {
				while(rs.next()) {
					Match match = new Match();
					match.setIdMatch(rs.getInt("idmatch"));
					match.setNomMatch(rs.getString("nomMatch"));
					match.setDate(rs.getDate("date"));
					match.setVille(rs.getString("ville"));
					match.setStade(rs.getString("stade"));
					match.setIdEquipe1(rs.getInt("Equipe_idEquipe1"));
					match.setIdEquipe2(rs.getInt("Equipe_idEquipe2"));
					match.setPointEquipe1(rs.getInt("pointEquipe1"));
					match.setPointEquipe2(rs.getInt("pointEquipe2"));
					matchs.add(match);
				}
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}
		
		return matchs;
	}
	
	/**
	 * obtenir les noms des equipes
	 * @param m 0 or 1
	 * @param nomEquipe nom de equipe
	 * @return list des nom
	 */
	public List<String> getNomsEquipesSearch(int m, String nomEquipe){
		EquipeDao equipeDao = new EquipeDao();
		Equipe equipe = equipeDao.searchEquipe(nomEquipe);
		List<String> nomsEquipes = new ArrayList<String>();
		if(equipe!=null) {
			String sql = "select * from matchinfo where Equipe_idEquipe1 = " + equipe.getIdEquipe() + " OR Equipe_idEquipe2 = " + equipe.getIdEquipe() +";";
			ResultSet rs = query(sql); 
			try {
				while(rs.next()) {
					equipeDao = new EquipeDao();
					nomEquipe = "";
					if(m==0) {
						nomEquipe = equipeDao.searchEquipe(rs.getInt("Equipe_idEquipe1")).getNomEquipe();

					}else if(m==1) {
						nomEquipe = equipeDao.searchEquipe(rs.getInt("Equipe_idEquipe2")).getNomEquipe();

					}
					//String nomEquipe = searcheEquipeByid(rs.getInt("Equipe_idEquipe")); 
					nomsEquipes.add(nomEquipe);
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return nomsEquipes;
	}
	
	/**
	 * obtenir tous les nom des equipes
	 * @param m 0 or 1
	 * @return list des noms des equipes
	 */
	public List<String> getNomsEquipes(int m) {
		String sql = "select * from matchinfo";
		ResultSet rs = query(sql); 
		List<String> nomsEquipes = new ArrayList<String>();
		//List<String> nomEquipes = new ArrayList<String>();
		try {
			while(rs.next()) {
				EquipeDao equipeDao = new EquipeDao();
				String nomEquipe = "";
				if(m==0) {
					nomEquipe = equipeDao.searchEquipe(rs.getInt("Equipe_idEquipe1")).getNomEquipe();

				}else if(m==1) {
					nomEquipe = equipeDao.searchEquipe(rs.getInt("Equipe_idEquipe2")).getNomEquipe();

				}
				//String nomEquipe = searcheEquipeByid(rs.getInt("Equipe_idEquipe")); 
				nomsEquipes.add(nomEquipe);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return nomsEquipes;
	}

	
	
}
