import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import DATA.Joueurs;
import DATA.Match;
import DATA.Statcard;
import collec.JoueursCollec;
import collec.MatchCollec;
import collec.StatCardCollec;

public class Usine {
	
	
	public static void XMLTableJoueurs(String Chemin) {
		
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build(new File (Chemin));
            // Récupération de l'élément racine (dans ce cas <equipe>)
            Element racine = document.getRootElement();

            // Parcourir les éléments <joueur> dans le fichier XML
            List<Element> listeJoueurs = racine.getChildren("joueur");
            for (Element joueurElement : listeJoueurs) {
                // Récupérer les données de chaque joueur
                String nom = joueurElement.getChildText("Nom");
                String prenom = joueurElement.getChildText("Prénom");
                int age = Integer.parseInt(joueurElement.getChildText("Age"));
                int poids = Integer.parseInt(joueurElement.getChildText("Poid"));
                int taille = Integer.parseInt(joueurElement.getChildText("Taille"));

                // Créer un objet JoueurManager et l'ajouter à la liste
                Joueurs joueur = new Joueurs(nom, prenom, age, poids, taille);
                JoueursCollec.ajouterJoueur(joueur);
            }
        }

            catch (Exception e) {
                e.printStackTrace();
            }
        	
        }
	
	public static void XMLTableMatch(String Chemin) {
		 // Initialisation du SAXBuilder pour lire le fichier XML
        SAXBuilder saxBuilder = new SAXBuilder();

		
		try {
           
            // Création du document JDOM depuis le fichier XML
            Document document = saxBuilder.build(new File (Chemin));

            // Récupération de l'élément racine (dans ce cas <equipe>)
            Element racine = document.getRootElement();

            // Parcourir les éléments <joueur> dans le fichier XML
            List<Element> listeMatchs = racine.getChildren("match");
            for (Element joueurElement : listeMatchs) {
                // Récupérer les données de chaque joueur
            	int MatchID = Integer.parseInt(joueurElement.getChildText("MatchID"));
                String Adversaire = joueurElement.getChildText("Adversaire");
                String Date = joueurElement.getChildText("Date");
                String Lieu = joueurElement.getChildText("Lieu");
                String Resultat = joueurElement.getChildText("Resultat");
                String DifScore = joueurElement.getChildText("DifScore");

                // Créer un objet JoueurManager et l'ajouter à la liste
                Match match = new Match (MatchID, Adversaire, Date, Lieu, Resultat,DifScore);
                MatchCollec.ajouterMatch(match);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	public static void XMLTableStats(String Chemin) {
		 // Initialisation du SAXBuilder pour lire le fichier XML
        SAXBuilder saxBuilder = new SAXBuilder();

        try {
        
            // Création du document JDOM depuis le fichier XML
            Document document = saxBuilder.build(new File (Chemin));

            // Récupération de l'élément racine (dans ce cas <equipe>)
            Element racine = document.getRootElement();

            // Parcourir les éléments <joueur> dans le fichier XML
            List<Element> listeStats = racine.getChildren("Stats");
            for (Element statElement : listeStats) {
                // Récupérer les données de chaque joueur
            	String Nom = statElement.getChildText("Nom");
                int MatchID = Integer.parseInt((statElement.getChildText("MatchID")));
                String TDJeu = statElement.getChildText("TDJeu");
                int Points = Integer.parseInt(statElement.getChildText("Points"));
                int Rebonds = Integer.parseInt(statElement.getChildText("Rebonds"));
                int Passe_D = Integer.parseInt(statElement.getChildText("Passe_D"));

                // Créer un objet JoueurManager et l'ajouter à la liste
                Statcard stats = new Statcard (TDJeu, Rebonds, Points, MatchID, Nom,Passe_D);
                StatCardCollec.ajouterStats(stats);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
	}
	
	public static boolean JoueurtoBase(Connection connection) {
        List<Joueurs> joueurs = JoueursCollec.getJoueurs();
        String sql = "INSERT INTO joueurs (Nom, Prenom, Age, Poid, Taille) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Joueurs joueur : joueurs) {
                statement.setString(1, joueur.getNom());
                statement.setString(2, joueur.getPrenom());
                statement.setInt(3, joueur.getAge());
                statement.setInt(4, (int) joueur.getPoid());
                statement.setInt(5, joueur.getTaille());
                statement.executeUpdate();
                System.out.println("Le joueur " + joueur.getNom() + " " + joueur.getPrenom() + " a été inséré dans la base de données.");
                
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public static boolean MatchtoBase(Connection connection) {
        List<Match> matchs = MatchCollec.getMatchs();
        String sql = "INSERT INTO matchs (MatchID, Adversaire, Date, Lieu, Resultat, DifScore) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Match match : matchs) {
                statement.setInt(1, match.getMatchID());
                statement.setString(2, match.getAdversaire());
                statement.setString(3, match.getDate());
                statement.setString(4, match.getLieu());
                statement.setString(5, match.getResultat());
                statement.setString(6, match.getDifScore());
                statement.executeUpdate();
                System.out.println("Le Match " + match.getMatchID() + " du " + match.getDate() + " a été inséré dans la base de données.");
                
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public static boolean StatcardtoBase(Connection connection) {
    	
        List<Statcard> statcards = StatCardCollec.getStats();

    	String sql = "INSERT INTO statcard (TDJeu, Rebonds, Points, MatchID, Nom, Passe_D) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Statcard statcard : statcards) {
            statement.setString(1, statcard.getTDJeu());
            statement.setInt(2, statcard.getRebonds());
            statement.setInt(3, statcard.getPoints());
            statement.setInt(4, statcard.getMatchID());
            statement.setString(5, statcard.getNom());
            statement.setInt(6, statcard.getPasse_D());
            statement.executeUpdate();
            System.out.println("La stat du match " + statcard.getMatchID() + " a été inséré dans la base de données.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


