package deepLearning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Perceptron {

	private List<Arete> listeArete;
	private List<Neurone> listeNeurone;

	public Perceptron() {
		this.listeArete = new ArrayList<Arete>();
		this.listeNeurone = new ArrayList<Neurone>();
	}

	public void ajouterArete(Arete arete) {
		listeArete.add(arete);
	}

	public void ajouterNeurone(Neurone noeud) {
		listeNeurone.add(noeud);
	}

	public List<Neurone> precedent(Neurone noeud) {
		ArrayList<Neurone> resultat = new ArrayList<>();
		for (Arete clef : listeArete) {
			if (clef.getDestination().equals(noeud))
				resultat.add(clef.getSource());
		}
		return resultat;
	}

	public List<Neurone> suivant(Neurone noeud) {
		ArrayList<Neurone> resultat = new ArrayList<>();
		for (Arete clef : listeArete) {
			if (clef.getSource().equals(noeud))
				resultat.add(clef.getDestination());
		}
		return resultat;
	}

	public double potentielPostSynaptique(Neurone neurone) {
		double resultat = 0;
		//System.out.println("Je suis : "+neurone.getEtiquette());
		ArrayList<Neurone> precedent = (ArrayList<Neurone>) precedent(neurone);
		for (int i = 0; i < precedent.size(); i++) {
			//System.out.println("Precedent : "+precedent.get(i).getEtiquette());
			//System.out.println("Poids arrete reliant : "+getArete(precedent.get(i), neurone).getPoidsSynaptique());
			//System.out.println("Valeur Synaptique : "+precedent.get(i).getValeurSynaptique());
			resultat += getArete(precedent.get(i), neurone).getPoidsSynaptique()
					* precedent.get(i).getValeurSynaptique();
		}
		//System.out.println("Potentiel post synaptique : "+resultat);
		return resultat;
	}

	public Arete getArete(Neurone source, Neurone destination) {
		Arete resultat = null;
		for (Arete arete : listeArete) {
			if (arete.getSource().equals(source) && arete.getDestination().equals(destination))
				resultat = arete;
		}
		return resultat;
	}

	public double tangenteHyperbolique(double x) {
		return Math.tanh(x);
	}

	public double logistique(double x) {
		return 1.0 / (1.0 + Math.exp(-x));
	}

	public double deriveLogistique(double x) {
		return logistique(x) * (1.0 - logistique(x));
	}
	
	public double relu(double x){
		return Math.log(1 + Math.exp(x));
	}
	
	public double deriveRelu(double x){
		return logistique(x);
	}

	public double fonctionActivation(double x, Neurone neurone) {
		double activation = 0;
		if (neurone.getClass().equals(NeuroneIntermediaire.class)) {
			NeuroneIntermediaire aux = (NeuroneIntermediaire) neurone;
			if(aux.getCouche() == 1 || aux.getCouche() == 2 || aux.getCouche() == 3)
				activation = relu(x);
			if(aux.getCouche() == 4)
				activation = tangenteHyperbolique(x);
		}
		return activation;
	}

	public int nombreCoucheItermediaire() {
		int resultat = 0;
		for (Neurone neurone : listeNeurone) {
			if (neurone.getClass().equals(NeuroneIntermediaire.class)) {
				NeuroneIntermediaire aux = (NeuroneIntermediaire) neurone;
				if (aux.getCouche() > resultat)
					resultat = aux.getCouche();
			}
		}
		return resultat;
	}

	public void miseAJourNeurone(Neurone neurone) {
		double potentielPostSynaptique = potentielPostSynaptique(neurone);
		neurone.setValeurSynaptique(fonctionActivation(potentielPostSynaptique, neurone));
		//System.out.println("Potentiel post synaptique : "+potentielPostSynaptique);
		//System.out.println("Valeur synaptique neurone "+neurone.getEtiquette()+" : "+fonctionActivation(potentielPostSynaptique)+"\n");
	}

	public double numerateurSoftMax(Neurone neurone) {
		return Math.exp(potentielPostSynaptique(neurone));
	}

	public double denominateurSoftMax() {
		double resultat = 0;
		for (Neurone neurone : listeNeurone) {
			if (neurone.getClass().equals(NeuroneSortie.class))
				resultat += Math.exp(potentielPostSynaptique(neurone));
		}
		return resultat;
	}

	public double softMax(Neurone neurone, double denominateur) {
		return numerateurSoftMax(neurone) / denominateur;
	}

	public void propagationActivite() {
		int nombreCoucheItermediaire = nombreCoucheItermediaire();
		for (int i = 1; i <= nombreCoucheItermediaire; i++) {
			for (Neurone neurone : listeNeurone) {
				if (neurone.getClass().equals(NeuroneIntermediaire.class)) {
					NeuroneIntermediaire aux = (NeuroneIntermediaire) neurone;
					if (aux.getCouche() == i)
						miseAJourNeurone(aux);
				}
			}
		}
		double denominateurSoftMax = denominateurSoftMax();
		for (Neurone neurone : listeNeurone) {
			if (neurone.getClass().equals(NeuroneSortie.class))
				//miseAJourNeurone(neurone);
				neurone.setValeurSynaptique(softMax(neurone, denominateurSoftMax));
		}
	}

	public double erreurPropagation() {
		return erreurQuadratique();
	}

	public double entropieRelative() {
		double erreur = 0;
		for (Neurone neurone : listeNeurone) {
			if (neurone.getClass().equals(NeuroneSortie.class)) {
				NeuroneSortie neuroneSortie = (NeuroneSortie) neurone;
				erreur += (-neuroneSortie.getValeurAttendue()
						* Math.log(neuroneSortie.getValeurSynaptique() / neuroneSortie.getValeurAttendue()));
			}
		}
		erreur = erreur * 0.5;
		return erreur;
	}

	public double erreurQuadratique() {
		double erreur = 0;
		for (Neurone neurone : listeNeurone) {
			if (neurone.getClass().equals(NeuroneSortie.class)) {
				NeuroneSortie neuroneSortie = (NeuroneSortie) neurone;
				erreur += (neuroneSortie.getValeurSynaptique() - neuroneSortie.getValeurAttendue())
						* (neuroneSortie.getValeurSynaptique() - neuroneSortie.getValeurAttendue());
			}
		}
		erreur = erreur * 0.5;
		return erreur;
	}

	public void retropropagationErreur() {
		double erreurPropagation = erreurPropagation();
		// System.out.println("Erreur : "+erreurPropagation);
		for (Neurone neurone : listeNeurone) {
			if (neurone.getClass().equals(NeuroneSortie.class)) {
				NeuroneSortie neuroneSortie = (NeuroneSortie) neurone;
				//neuroneSortie.setDelta(neuroneSortie.getValeurAttendue() - erreurPropagation);
				neuroneSortie.setDelta(deriveTanH(potentielPostSynaptique(neuroneSortie))*(neuroneSortie.getValeurAttendue() - neuroneSortie.getValeurSynaptique()));
				// System.out.println("Delta : "+neuroneSortie.getDelta());
			}
		}
		int nombreCoucheItermediaire = nombreCoucheItermediaire();
		for (int i = nombreCoucheItermediaire; i >= 1; i--) {
			for (Neurone neurone : listeNeurone) {
				if (neurone.getClass().equals(NeuroneIntermediaire.class)) {
					NeuroneIntermediaire aux = (NeuroneIntermediaire) neurone;
					if (aux.getCouche() == i)
						miseAJourDelta(aux);
				}
			}
		}

	}

	public void miseAJourDelta(NeuroneIntermediaire aux) {
		double somme = 0;
		double delta = 0;
		ArrayList<Neurone> suivant = (ArrayList<Neurone>) suivant(aux);
		for (int i = 0; i < suivant.size(); i++) {
			somme += getArete(aux, suivant.get(i)).getPoidsSynaptique() * suivant.get(i).getDelta();
		}
		if(aux.getCouche() == 1 || aux.getCouche() == 2 || aux.getCouche() == 3)
			delta = deriveRelu(aux.getValeurSynaptique()) * somme;
		if(aux.getCouche() == 4)
			delta = deriveTanH(aux.getValeurSynaptique()) * somme;
		aux.setDelta(delta);

	}

	public void miseAJourPoids() {
		double pasApp = 0.1;
		for (Arete clef : listeArete) {
			double newPoids = clef.getPoidsSynaptique()
					+ pasApp * clef.getSource().getValeurSynaptique() * clef.getDestination().getDelta();
			clef.setPoidsSynaptique(newPoids);
		}

	}

	public double deriveTanH(double x) {
		return 1.0 - (Math.tanh(x) * Math.tanh(x));
	}

	public void initialisationPoidsAleatoire() {
		for (Arete arete : listeArete) {
			Random r = new Random();
			double randomValue = (0.2 * r.nextDouble()) - 0.1;
			arete.setPoidsSynaptique(randomValue);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(listeNeurone.toString());
		sb.append("\n");
		sb.append(listeArete.toString());
		return sb.toString();
	}
	
	public String affichagePoids(){
		StringBuilder sb = new StringBuilder();
		sb.append("Poids appris par le reseau lors du meilleur apprentissage :\n\n");
		for (int i = 0; i < listeArete.size(); i++) {
			sb.append("Poids-Arrete["+listeArete.get(i).getSource().getEtiquette()+","+listeArete.get(i).getDestination().getEtiquette()+"] : "+listeArete.get(i).getPoidsSynaptique()+"\n");
		}
		return sb.toString();
	}

	/*
	 * -------------------------------------------------------------------------
	 * -----------------------------------
	 */

	public boolean contientArete(Arete arete) {
		boolean resultat = false;
		for (Arete clef : listeArete) {
			if (clef.equals(arete))
				resultat = true;
		}
		return resultat;
	}

	public boolean contientNoeud(Neurone noeud) {
		boolean resultat = false;
		for (Neurone clef : listeNeurone) {
			if (clef.equals(noeud))
				resultat = true;
		}
		return resultat;
	}

	public List<Arete> getAretes() {
		return listeArete;
	}

	public Neurone getNoeud(int noeudId) {
		Neurone resultat = null;
		for (Neurone clef : listeNeurone) {
			if (clef.getId() == noeudId)
				resultat = clef;
		}
		return resultat;
	}

	public Collection<Neurone> getNoeuds() {
		return listeNeurone;
	}

	public Set<Integer> getNoeudIds() {
		Set<Integer> resultat = new HashSet<Integer>();
		for (Neurone clef : listeNeurone) {
			resultat.add(clef.getId());
		}
		return resultat;
	}

	public int nombreAretes() {
		return listeArete.size();
	}

	public int nombreNoeuds() {
		return listeNeurone.size();
	}

	public String prediction() {
		double valeurSynaptique = Double.MIN_VALUE;
		String etiquette = null;
		for (Neurone neurone : listeNeurone) {
			if (neurone.getClass().equals(NeuroneSortie.class)) {
				NeuroneSortie neuroneSortie = (NeuroneSortie) neurone;
				if(neuroneSortie.getValeurSynaptique() > valeurSynaptique){
					valeurSynaptique = neuroneSortie.getValeurSynaptique();
					etiquette = neurone.getEtiquette();
				}
			}
		}
		return etiquette;
	}

}
