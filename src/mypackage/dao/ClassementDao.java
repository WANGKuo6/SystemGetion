package mypackage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mypackage.model.Classement;
import mypackage.model.Equipe;

public class ClassementDao extends BaseDao{

	/**
	 * rechercher les classements de certain equipe
	 * @param paramId id d'equipe
	 * @return list de classement
	 */
	public List<Classement> searchClassements(int paramId) {
		String sql = "select * from classement where Equipe_idEquipe = "+ paramId;
		ResultSet rs = query(sql);
		List<Classement> classements = new ArrayList<Classement>();
		try {
			while(rs.next()) {
				Classement classement = new Classement();
				classement.setIdClassement(rs.getInt("idclassement"));
				classement.setIdEquipe(rs.getInt("Equipe_idEquipe"));
				classement.setAnnee(rs.getInt("annee"));
				classement.setPosition(rs.getInt("position"));
				classements.add(classement);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return classements;
	}
	

		
}
