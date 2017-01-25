package deepLearning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Perceptron{
	
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
			if(clef.getDestination().equals(noeud))
				resultat.add(clef.getSource());
		}
		return resultat;
	}
	
	public double potentielPostSynaptique(Neurone neurone){
		double resultat = 0;
		ArrayList<Neurone> precedent = (ArrayList<Neurone>) precedent(neurone);
		for (int i = 0; i < precedent.size(); i++) {
			resultat += getArete(precedent.get(i), neurone).getPoidsSynaptique()*precedent.get(i).getValeurSynaptique();
		}
		return resultat;
	}

	public Arete getArete(Neurone source, Neurone destination){
		Arete resultat = null;
		for (Arete arete : listeArete) {
			if(arete.getSource().equals(source) && arete.getDestination().equals(destination))
				resultat = arete;
		}
		return resultat;
	}
	
	public double tangenteHyperbolique(double x){
		return Math.tanh(x);
	}
	
	public double fonctionActivation(double x){
		return tangenteHyperbolique(x);
	}
	
	public int nombreCoucheItermediaire(){
		int resultat = 0;
		for (Neurone neurone : listeNeurone) {
			if(neurone.getClass().equals(NeuroneIntermediaire.class)){
				NeuroneIntermediaire aux = (NeuroneIntermediaire) neurone;
				if(aux.getCouche() > resultat)
					resultat = aux.getCouche();
			}
		}
		return resultat;
	}
	
	public void miseAJourNeurone(Neurone neurone){
		double potentielPostSynaptique = potentielPostSynaptique(neurone);
		neurone.setValeurSynaptique(fonctionActivation(potentielPostSynaptique));
	}
	
	public double numerateurSoftMax(Neurone neurone){
		return Math.exp(potentielPostSynaptique(neurone));
	}
	
	public double denominateurSoftMax(){
		double resultat = 0;
		for (Neurone neurone : listeNeurone) {
			if(neurone.getClass().equals(NeuroneSortie.class))
				resultat += Math.exp(potentielPostSynaptique(neurone));
		}
		return resultat;
	}
	
	public double softMax(Neurone neurone, double denominateur){
		return numerateurSoftMax(neurone)/denominateur;
	}

	public void propagationActivite(){
		int nombreCoucheItermediaire = nombreCoucheItermediaire();
		for (int i = 1; i <= nombreCoucheItermediaire; i++) {
			for (Neurone neurone : listeNeurone) {
				if(neurone.getClass().equals(NeuroneIntermediaire.class)){
					NeuroneIntermediaire aux = (NeuroneIntermediaire) neurone;
					if(aux.getCouche() == i)
						miseAJourNeurone(aux);
				}
			}
		}
		double denominateurSoftMax = denominateurSoftMax();
		for (Neurone neurone : listeNeurone) {
			if(neurone.getClass().equals(NeuroneSortie.class))
				neurone.setValeurSynaptique(softMax(neurone, denominateurSoftMax));
		}
	}
	
	public double erreurPropagation(){
		return erreurQuadratique();
	}
	
	public double entropieRelative(){
		double erreur = 0;
		for (Neurone neurone : listeNeurone) {
			if(neurone.getClass().equals(NeuroneSortie.class)){
				NeuroneSortie neuroneSortie = (NeuroneSortie) neurone;
				erreur += (-neuroneSortie.getValeurAttendue()*Math.log(neuroneSortie.getValeurSynaptique()/neuroneSortie.getValeurAttendue()));
			}
		}
		erreur = erreur * 0.5;
		return erreur;
	}
	
	public double erreurQuadratique(){
		double erreur = 0;
		for (Neurone neurone : listeNeurone) {
			if(neurone.getClass().equals(NeuroneSortie.class)){
				NeuroneSortie neuroneSortie = (NeuroneSortie) neurone;
				erreur += (neuroneSortie.getValeurSynaptique() - neuroneSortie.getValeurAttendue())*(neuroneSortie.getValeurSynaptique() - neuroneSortie.getValeurAttendue());
			}
		}
		erreur = erreur * 0.5;
		return erreur;
	}
	
	public void retropropagationErreur(){
		double erreurPropagation = erreurPropagation();
		System.out.println(erreurPropagation);
	}

	public void initialisationPoidsAleatoire(){
		for (Arete arete : listeArete) {
			double random = Math.random() - Math.random();
			arete.setPoidsSynaptique(random);
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
	
	/* ------------------------------------------------------------------------------------------------------------ */
	
	public boolean contientArete(Arete arete) {
		boolean resultat = false;
		for (Arete clef : listeArete) {
			if(clef.equals(arete))
				resultat = true;
		}
		return resultat;
	}

	public boolean contientNoeud(Neurone noeud) {
		boolean resultat = false;
		for (Neurone clef : listeNeurone) {
			if(clef.equals(noeud))
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
			if(clef.getId() == noeudId)
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

}
