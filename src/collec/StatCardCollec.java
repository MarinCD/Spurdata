package collec;

import java.util.ArrayList;

import DATA.Statcard;

public class StatCardCollec {
	private static ArrayList<Statcard> MesStats = new ArrayList<Statcard>();
	
	

	public static boolean ajouterStats(Statcard stats) {
		if (!(MesStats.contains(stats)))
		{
			MesStats.add(stats);
			return true;
		}
		return false;
	}
	public static ArrayList<Statcard> getStats() {
		return MesStats;
	}
	public static void remplirListe(ArrayList<Statcard> Tempo)
	{
		for (Statcard stats:Tempo)
		{
			if (!(MesStats.contains(stats))) {
				MesStats.add(stats);
			}
		}
	}

	public static Statcard GetUnStat(int getMatchID) {
		for (Statcard match:MesStats) {
			if(match.getMatchID() == (getMatchID)) {
				return match;
			}
		}
		return null;
	}
	
	public static ArrayList<Statcard> GetUnJoueur(String getJoueurNom) {
		ArrayList<Statcard> joueursTrouves = new ArrayList<>();  // Liste pour stocker les joueurs trouvés

	    for (Statcard stats : MesStats) {
	        if (stats.getNom().equals(getJoueurNom)) {  // Comparaison correcte des noms avec equals()
	            joueursTrouves.add(stats);  // Ajouter le joueur trouvé à la liste
	        }
	    }
		return joueursTrouves; 
	}
	
	
	public static Statcard additionnerStatsJoueur(String joueurNom) {
	    // Récupère toutes les stats pour le joueur
	    ArrayList<Statcard> statsJoueur = GetUnJoueur(joueurNom);
	    
	    // Si le joueur n'a pas de statistiques
	    if (statsJoueur.isEmpty()) {
	        return null;
	    }

	    // Crée un objet pour stocker les statistiques totalisées
	    Statcard totalStats = new Statcard("", 0, 0, 0, joueurNom, 0);
	    
	    // Additionne les statistiques
	    for (Statcard stats : statsJoueur) {
	        totalStats.setPoints(totalStats.getPoints() + stats.getPoints());    // Additionne les points
	        totalStats.setRebonds(totalStats.getRebonds() + stats.getRebonds());  // Additionne les rebonds
	        totalStats.setPasse_D(totalStats.getPasse_D() + stats.getPasse_D());   // Additionne les passes décisives
	    }
	    
	    // Retourne l'objet Statcard avec les statistiques totales
	    return totalStats;
	}

		

	}