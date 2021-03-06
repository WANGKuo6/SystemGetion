package mypackage.model;

public class Joueur {
	private int idjoueur;
	private String nomJoueur;
	private int but;
	private String role;
	private float notePrese;
	private int Equipe_idEquipe;
	public int getIdjoueur() {
		return idjoueur;
	}
	public void setIdjoueur(int idjoueur) {
		this.idjoueur = idjoueur;
	}
	public String getNomJoueur() {
		return nomJoueur;
	}
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
	public int getBut() {
		return but;
	}
	public void setBut(int but) {
		this.but = but;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public float getNotePrese() {
		return notePrese;
	}
	public void setNotePrese(float notePrese) {
		this.notePrese = notePrese;
	}
	public int getEquipe_idEquipe() {
		return Equipe_idEquipe;
	}
	public void setEquipe_idEquipe(int equipe_idEquipe) {
		Equipe_idEquipe = equipe_idEquipe;
	}
	
}
