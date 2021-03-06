package mypackage.model;

import java.sql.Date;

public class Match {
	private int idMatch;
	private String nomMatch;
	private Date Date;
	private String ville;
	private String stade;
	private int idEquipe1;
	private int idEquipe2;
	private int pointEquipe1;
	private int pointEquipe2;
	public int getIdMatch() {
		return idMatch;
	}
	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}
	public String getNomMatch() {
		return nomMatch;
	}
	public void setNomMatch(String nomMatch) {
		this.nomMatch = nomMatch;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getStade() {
		return stade;
	}
	public void setStade(String stade) {
		this.stade = stade;
	}
	public int getIdEquipe1() {
		return idEquipe1;
	}
	public void setIdEquipe1(int idEquipe1) {
		this.idEquipe1 = idEquipe1;
	}
	public int getIdEquipe2() {
		return idEquipe2;
	}
	public void setIdEquipe2(int idEquipe2) {
		this.idEquipe2 = idEquipe2;
	}
	public int getPointEquipe1() {
		return pointEquipe1;
	}
	public void setPointEquipe1(int pointEquipe1) {
		this.pointEquipe1 = pointEquipe1;
	}
	public int getPointEquipe2() {
		return pointEquipe2;
	}
	public void setPointEquipe2(int pointEquipe2) {
		this.pointEquipe2 = pointEquipe2;
	}
	
}
