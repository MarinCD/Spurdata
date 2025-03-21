package DATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Joueurs {
	
	private String Nom;
	private String Prenom;
	private int Age;
	private int Poid;
	private int Taille;	

	

	public String getNom() {
		return Nom;
	}



	public void setNom(String nom) {
		Nom = nom;
	}



	public String getPrenom() {
		return Prenom;
	}



	public void setPrenom(String prenom) {
		Prenom = prenom;
	}



	public int getAge() {
		return Age;
	}



	public void setAge(int age) {
		Age = age;
	}



	public float getPoid() {
		return Poid;
	}



	public void setPoid(int poid) {
		Poid = poid;
	}



	public int getTaille() {
		return Taille;
	}



	public void setTaille(int taille) {
		Taille = taille;
	}

	

	public Joueurs(String nom, String prenom, int age, int poids, int taille) {
		super();
		Nom = nom;
		Prenom = prenom;
		Age = age;
		Poid = poids;
		Taille = taille;
	}
	
	@Override
    public String toString() {
        return "Joueur [Nom=" + Nom + ", Prénom=" + Prenom + ", Age=" + Age + 
               ", Poids=" + Poid + "kg, Taille=" + Taille + "cm]";
    }

   
    
    
	    

	

}




