package collec;

import java.util.ArrayList;

import DATA.Joueurs;


public class JoueursCollec {
	private static ArrayList<Joueurs> MesJoueurs = new ArrayList<Joueurs>();
		
	
public static boolean ajouterJoueur(Joueurs joueur) {
	if (!(MesJoueurs.contains(joueur)))
	{
		MesJoueurs.add(joueur);
		return true;
	}
	return false;
}

public static ArrayList<Joueurs> getJoueurs() {
	return MesJoueurs;
}

public static void remplirListe(ArrayList<Joueurs> Tempo)
{
	for (Joueurs joueur:Tempo)
	{
		if (!(MesJoueurs.contains(joueur))) {
			MesJoueurs.add(joueur);
		}
	}
}

public static Joueurs GetUnJoueurs(String nom) {
	for (Joueurs joueur:MesJoueurs) {
		if(joueur.getNom().equals(nom)) {
			return joueur;
		}
	}
	return null;
}



}