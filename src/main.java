import java.sql.Connection;
    	import java.util.List;

import DATA.DBConnection;
import DATA.Joueurs;
    	import DATA.Match;
    	import DATA.Statcard;
import collec.JoueursCollec;
import collec.MatchCollec;
import collec.StatCardCollec;
    	
public class main {
    public static void main(String[] args) {
    	
    	
   	
    			
//    			Interface1.setVisible(true);
    	
    	        // Charger les données depuis les fichiers XML en utilisant la classe Usine
    	        Usine.XMLTableJoueurs("Joueurs.xml");
    	        Usine.XMLTableMatch("Matchs.xml");
    	        Usine.XMLTableStats("Heat.xml");
    	        Usine.XMLTableStats("Rockets.xml");
    	        Usine.XMLTableStats("Thunder.xml");
    	        
    	   
    	       
    	        
    	       
    	           
    	        
    	        Interface Interface1 = new Interface();

    	      
    	        
      
    	        
    }}
    	      
    	        
    	        
