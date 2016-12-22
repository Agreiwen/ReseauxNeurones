package deepLearning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Perceptron{
	
	List<Arete> listeArete;
	List<Noeud> listeNoeud;
	
	public Perceptron() {
		listeArete = new ArrayList<Arete>();
		listeNoeud = new ArrayList<Noeud>();
	}
	
	public void ajouterArete(Arete arete) {
		listeArete.add(arete);
	}

	public void ajouterNoeud(Noeud noeud) {
		listeNoeud.add(noeud);
	}

	public List<Noeud> precedent(Noeud noeud) {
		ArrayList<Noeud> resultat = new ArrayList<>();
		for (Arete clef : listeArete) {
			if(clef.getDestination().equals(noeud))
				resultat.add(clef.getSource());
		}
		return resultat;
	}

	public boolean contientArete(Arete arete) {
		boolean resultat = false;
		for (Arete clef : listeArete) {
			if(clef.equals(arete))
				resultat = true;
		}
		return resultat;
	}

	public boolean contientNoeud(Noeud noeud) {
		boolean resultat = false;
		for (Noeud clef : listeNoeud) {
			if(clef.equals(noeud))
				resultat = true;
		}
		return resultat;
	}

	public List<Arete> getAretes() {
		return listeArete;
	}

	public Noeud getNoeud(int noeudId) {
		Noeud resultat = null;
		for (Noeud clef : listeNoeud) {
			if(clef.getId() == noeudId)
				resultat = clef;
		}
		return resultat;
	}

	public Collection<Noeud> getNoeuds() {
		return listeNoeud;
	}

	public Set<Integer> getNoeudIds() {
		Set<Integer> resultat = new HashSet<Integer>();
		for (Noeud clef : listeNoeud) {
			resultat.add(clef.getId());
		}
		return resultat;
	}

	public int nombreAretes() {
		return listeArete.size();
	}

	public int nombreNoeuds() {
		return listeNoeud.size();
	}

}
