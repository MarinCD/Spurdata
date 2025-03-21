package collec;

import java.util.ArrayList;

import DATA.Match;

public class MatchCollec {
	private static ArrayList<Match> MesMatchs = new ArrayList<Match>();
	
	

	public static boolean ajouterMatch(Match match) {
		if (!(MesMatchs.contains(match)))
		{
			MesMatchs.add(match);
			return true;
		}
		return false;
	}
	public static ArrayList<Match> getMatchs() {
		return MesMatchs;
	}
	public static void remplirListe(ArrayList<Match> Tempo)
	{
		for (Match match:Tempo)
		{
			if (!(MesMatchs.contains(match))) {
				MesMatchs.add(match);
			}
		}
	}

	public static Match GetUnMatchs(int getMatchID) {
		for (Match match:MesMatchs) {
			if(match.getMatchID() == (getMatchID)) {
				return match;
			}
		}
		return null;
	}

	}