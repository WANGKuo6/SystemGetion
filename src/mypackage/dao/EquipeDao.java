package mypackage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import mypackage.model.Equipe;

public class EquipeDao extends BaseDao{

	/**
	 * obtenir tous les equipes
	 * @return list des equipe
	 */
	public List<Equipe> getEquipes(){
		String sql = "select * from equipe";
		ResultSet rs = query(sql); 
		List<Equipe> equipes = new ArrayList<Equipe>();
		try {
			while(rs.next()) {
				Equipe equipe = new Equipe();
				equipe.setIdEquipe(rs.getInt("idEquipe"));
				equipe.setNomEquipe(rs.getString("nomEquipe"));
				equipe.setNbParticipation(rs.getInt("nbParticipation"));
				equipe.setNbVictore(rs.getInt("nbVictoire"));
				equipes.add(equipe);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return equipes;
	}
	
	/**
	 * ajouter un equipe dans BD
	 * @param equipe 
	 * @return true or false 
	 */
	public boolean addEquipe(Equipe equipe) {
		String sql = "insert into equipe values(" + equipe.getIdEquipe();
		sql += ",'"+equipe.getNomEquipe()+"'"; 
		sql += ","+equipe.getNbParticipation();
		sql += ","+ equipe.getNbVictore();
		sql += ")";
		return update(sql);
	}
	
	/**
	 * supprimer un equipe par id d'equipe
	 * @param paramId id d'equipe
	 * @return true or false
	 */
	public boolean deleteEquipe(int paramId) {
		// TODO Auto-generated method stub
		String sql = "delete from equipe where idEquipe =" + paramId; 
		return update(sql);
	}
	
	/**
	 * modifier les informations d'equipe
	 * @param equipe
	 * @return true or false
	 */
	public boolean editEquipe(Equipe equipe) {
		int id = equipe.getIdEquipe();
		String nomEquipe = equipe.getNomEquipe();
		int nbParticipation = equipe.getNbParticipation();
		int nbVictoire = equipe.getNbVictore();
		String sql = "update equipe set idEquipe = "+id;
		sql += ",nomEquipe = '"+ nomEquipe+"'";
		sql += ",nbParticipation = "+ nbParticipation;
		sql += ",nbVictoire = "+ nbVictoire;
		sql += " where idEquipe = " + id;
		System.out.println(sql);
		return update(sql);
	}
	
	/**
	 * rechercher un equipe par nom d'equipe
	 * @param ParamNom nom d'equipe
	 * @return equipe
	 */
	public Equipe searchEquipe(String ParamNom) {
		String sql = "select * from equipe where nomEquipe = '" + ParamNom +"';";
		Equipe equipe = null;
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				equipe = new Equipe();
				equipe.setIdEquipe(resultSet.getInt("idEquipe"));
				equipe.setNomEquipe(resultSet.getString("nomEquipe"));
				equipe.setNbParticipation(resultSet.getInt("nbParticipation"));
				equipe.setNbVictore(resultSet.getInt("nbVictoire"));
				return equipe;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipe;
	}
	
	/**
	 * rechercher un equipe par id d'equipe
	 * @param ParamId id d'equipe
	 * @return equipe
	 */
	public Equipe searchEquipe(int ParamId) {
		String sql = "select * from equipe where idEquipe = '" + ParamId +"';";
		Equipe equipe = null;
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				equipe = new Equipe();
				equipe.setIdEquipe(resultSet.getInt("idEquipe"));
				equipe.setNomEquipe(resultSet.getString("nomEquipe"));
				equipe.setNbParticipation(resultSet.getInt("nbParticipation"));
				equipe.setNbVictore(resultSet.getInt("nbVictoire"));
				return equipe;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipe;
	}
	
}
