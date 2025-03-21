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

public class Match {
	
	private int MatchID;
	private String Adversaire;
	private String Date;
	private String Lieu;
	private String Resultat;
	private String DifScore;
	
	public int getMatchID() {
		return MatchID;
	}
	public void setMatchID(int matchID) {
		MatchID = matchID;
	}
	public String getAdversaire() {
		return Adversaire;
	}
	public void setAdversaire(String adversaire) {
		Adversaire = adversaire;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getLieu() {
		return Lieu;
	}
	public void setLieu(String lieu) {
		Lieu = lieu;
	}
	public String getResultat() {
		return Resultat;
	}
	public void setResultat(String resultat) {
		Resultat = resultat;
	}
	public String getDifScore() {
		return DifScore;
	}
	public void setDifScore(String difScore) {
		DifScore = difScore;
	}
	public Match(int matchID, String adversaire, String date, String lieu, String resultat, String difScore) {
		super();
		MatchID = matchID;
		Adversaire = adversaire;
		Date = date;
		Lieu = lieu;
		Resultat = resultat;
		DifScore = difScore;
	}
	@Override
	public String toString() {
		return "Match [MatchID=" + MatchID + ", Adversaire=" + Adversaire + ", Date=" + Date + ", Lieu=" + Lieu
				+ ", Resultat=" + Resultat + ", DifScore=" + DifScore + "]";
	}
	
    
    
   
	
	

}
