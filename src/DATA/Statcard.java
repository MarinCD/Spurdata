package DATA;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Statcard {
	private String TDJeu;
	private int Rebonds;
	private int Points;
	private int MatchID;
	private String Nom;
	private int Passe_D;
	public String getTDJeu() {
		return TDJeu;
	}
	public void setTDJeu(String tDJeu) {
		TDJeu = tDJeu;
	}
	public int getRebonds() {
		return Rebonds;
	}
	public void setRebonds(int rebonds) {
		Rebonds = rebonds;
	}
	public int getPoints() {
		return Points;
	}
	public void setPoints(int points) {
		Points = points;
	}
	public int getMatchID() {
		return MatchID;
	}
	public void setMatchID(int matchID) {
		MatchID = matchID;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public int getPasse_D() {
		return Passe_D;
	}
	public void setPasse_D(int passe_D) {
		Passe_D = passe_D;
	}
	public Statcard(String tDJeu, int rebonds, int points, int matchID, String nom, int passe_D) {
		super();
		TDJeu = tDJeu;
		Rebonds = rebonds;
		Points = points;
		MatchID = matchID;
		Nom = nom;
		Passe_D = passe_D;
	}
	
	public String toString() {
		return "statcard [TDJeu=" + TDJeu + ", Rebonds=" + Rebonds + ", Points=" + Points + ", MatchID=" + MatchID
				+ ", Nom=" + Nom + ", Passe_D=" + Passe_D + "]";
	}}
	