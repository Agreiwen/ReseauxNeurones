package deepLearning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

public class MainClass {

	private ArrayList<Fleur> fleurs;
	private Perceptron perceptron;
	private double tauxBonneReco = 0;
	final static int NB_FLEURS_BASE_APP = 50; // le reste est dans la base de test
	final static int NB_APPRENTISSAGE = 10;
	final static double CRITERE_ARRET = 0.025;
	
	public MainClass() {
		/*
		 * Lecture du fichier IRIS
		 */
		fleurs = new ArrayList<>();
		try {
			fleurs = lectureFichier("src/iris.data.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * Construction du reseau
		 */
		perceptron = new Perceptron();
		constructionReseau();
		/*
		 * Traitement post-apprentissage
		 */
		traitementPostApprentissage();
		/*
		 * Apprentissage du reseau
		 */
		apprentissageReseau();
		/*
		 * Phase de test
		 */
		testReseau();
	}

	/**
	 * 
	 * @param fichier
	 * @return liste des fleurs de la base IRIS
	 * @throws IOException
	 * @category initialisation
	 */
	private ArrayList<Fleur> lectureFichier(String fichier) throws IOException {
		@SuppressWarnings("unused")
		int tailleBase = 0;
		BufferedReader br1, br2;
		String st;
		ArrayList<Fleur> fleurs = new ArrayList<Fleur>();
		br1 = new BufferedReader(new FileReader(fichier));
		System.out.print("Lecture du fichier IRIS... ");
		while ((st = br1.readLine()) != null) {
			tailleBase++;
		}
		br1.close();
		String[] separated;
		br2 = new BufferedReader(new FileReader(fichier));
		while ((st = br2.readLine()) != null) {
			separated = st.split(",");
			double loS = Double.parseDouble(separated[0]);
			double laS = Double.parseDouble(separated[1]);
			double loP = Double.parseDouble(separated[2]);
			double laP = Double.parseDouble(separated[3]);
			String c = separated[4];
			Fleur f = new Fleur(loS, laS, loP, laP, c);
			fleurs.add(f);
		}
		br2.close();
		System.out.println(" Termine\n");
		return fleurs;
	}

	/**
	 * realise la construction du reseau du neurone
	 * 
	 * @category initialisation
	 */
	private void constructionReseau() {
		System.out.print("Creation des neurones... ");
		// Entree
		NeuroneEntree e1 = new NeuroneEntree(11, "e1");
		NeuroneEntree e2 = new NeuroneEntree(12, "e2");
		NeuroneEntree e3 = new NeuroneEntree(13, "e3");
		NeuroneEntree e4 = new NeuroneEntree(14, "e4");
		// Filtre 1
		NeuroneIntermediaire i1 = new NeuroneIntermediaire(21, "filtre1-i1", 1);
		NeuroneIntermediaire i2 = new NeuroneIntermediaire(22, "filtre1-i2", 1);
		NeuroneIntermediaire i3 = new NeuroneIntermediaire(23, "filtre1-i3", 1);
		// Filtre 2
		NeuroneIntermediaire i4 = new NeuroneIntermediaire(24, "filtre2-i4", 1);
		NeuroneIntermediaire i5 = new NeuroneIntermediaire(25, "filtre2-i5", 1);
		NeuroneIntermediaire i6 = new NeuroneIntermediaire(26, "filtre2-i6", 1);
		// Couche 2
		NeuroneIntermediaire i7 = new NeuroneIntermediaire(31, "i7", 2);
		NeuroneIntermediaire i8 = new NeuroneIntermediaire(32, "i8", 2);
		// Couche 3
		NeuroneIntermediaire i9 = new NeuroneIntermediaire(41, "i9", 3);
		NeuroneIntermediaire i10 = new NeuroneIntermediaire(42, "i10", 3);
		// Couche 4
		NeuroneIntermediaire i11 = new NeuroneIntermediaire(51, "i11", 4);
		NeuroneIntermediaire i12 = new NeuroneIntermediaire(52, "i12", 4);
		// Sortie
		NeuroneSortie s1 = new NeuroneSortie(61, "Iris-setosa");
		NeuroneSortie s2 = new NeuroneSortie(62, "Iris-versicolor");
		NeuroneSortie s3 = new NeuroneSortie(63, "Iris-virginica");

		System.out.println("Creation des aretes... ");
		// Liaison entree - filtre
		Arete e1i1 = new Arete(e1, i1);
		Arete e2i1 = new Arete(e2, i1);
		Arete e2i2 = new Arete(e2, i2);
		Arete e3i2 = new Arete(e3, i2);
		Arete e3i3 = new Arete(e3, i3);
		Arete e4i3 = new Arete(e4, i3);

		Arete e1i4 = new Arete(e1, i4);
		Arete e2i4 = new Arete(e2, i4);
		Arete e2i5 = new Arete(e2, i5);
		Arete e3i5 = new Arete(e3, i5);
		Arete e3i6 = new Arete(e3, i6);
		Arete e4i6 = new Arete(e4, i6);

		// Liaison filtre - couche 1
		Arete i1i7 = new Arete(i1, i7);
		Arete i1i8 = new Arete(i1, i8);
		Arete i2i7 = new Arete(i2, i7);
		Arete i2i8 = new Arete(i2, i8);
		Arete i3i7 = new Arete(i3, i7);
		Arete i3i8 = new Arete(i3, i8);
		Arete i4i7 = new Arete(i4, i7);
		Arete i4i8 = new Arete(i4, i8);
		Arete i5i7 = new Arete(i5, i7);
		Arete i5i8 = new Arete(i5, i8);
		Arete i6i7 = new Arete(i6, i7);
		Arete i6i8 = new Arete(i6, i8);

		// Liaison couche 1 - couche 2
		Arete i7i9 = new Arete(i7, i9);
		Arete i7i10 = new Arete(i7, i10);
		Arete i8i9 = new Arete(i8, i9);
		Arete i8i10 = new Arete(i8, i10);

		// Liaison couche 2 - couche 3
		Arete i9i11 = new Arete(i9, i11);
		Arete i9i12 = new Arete(i9, i12);
		Arete i10i11 = new Arete(i10, i11);
		Arete i10i12 = new Arete(i10, i12);

		// Liaison couche 3 - sortie
		Arete i11s1 = new Arete(i11, s1);
		Arete i11s2 = new Arete(i11, s2);
		Arete i11s3 = new Arete(i11, s3);
		Arete i12s1 = new Arete(i12, s1);
		Arete i12s2 = new Arete(i12, s2);
		Arete i12s3 = new Arete(i12, s3);

		// Renvoi vecteur entree
		Arete e1i9 = new Arete(e1, i9);
		Arete e2i9 = new Arete(e2, i9);
		Arete e3i9 = new Arete(e3, i9);
		Arete e4i9 = new Arete(e4, i9);
		Arete e1i10 = new Arete(e1, i10);
		Arete e2i10 = new Arete(e2, i10);
		Arete e3i10 = new Arete(e3, i10);
		Arete e4i10 = new Arete(e4, i10);

		Arete e1i11 = new Arete(e1, i11);
		Arete e2i11 = new Arete(e2, i11);
		Arete e3i11 = new Arete(e3, i11);
		Arete e4i11 = new Arete(e4, i11);
		Arete e1i12 = new Arete(e1, i12);
		Arete e2i12 = new Arete(e2, i12);
		Arete e3i12 = new Arete(e3, i12);
		Arete e4i12 = new Arete(e4, i12);

		perceptron.ajouterNeurone(e1);
		perceptron.ajouterNeurone(e2);
		perceptron.ajouterNeurone(e3);
		perceptron.ajouterNeurone(e4);

		perceptron.ajouterNeurone(i1);
		perceptron.ajouterNeurone(i2);
		perceptron.ajouterNeurone(i3);
		perceptron.ajouterNeurone(i4);
		perceptron.ajouterNeurone(i5);
		perceptron.ajouterNeurone(i6);

		perceptron.ajouterNeurone(i7);
		perceptron.ajouterNeurone(i8);

		perceptron.ajouterNeurone(i9);
		perceptron.ajouterNeurone(i10);

		perceptron.ajouterNeurone(i11);
		perceptron.ajouterNeurone(i12);

		perceptron.ajouterNeurone(s1);
		perceptron.ajouterNeurone(s2);
		perceptron.ajouterNeurone(s3);

		perceptron.ajouterArete(e1i1);
		perceptron.ajouterArete(e2i1);
		perceptron.ajouterArete(e2i2);
		perceptron.ajouterArete(e3i2);
		perceptron.ajouterArete(e3i3);
		perceptron.ajouterArete(e4i3);

		perceptron.ajouterArete(e1i4);
		perceptron.ajouterArete(e2i4);
		perceptron.ajouterArete(e2i5);
		perceptron.ajouterArete(e3i5);
		perceptron.ajouterArete(e3i6);
		perceptron.ajouterArete(e4i6);

		perceptron.ajouterArete(i1i7);
		perceptron.ajouterArete(i1i8);
		perceptron.ajouterArete(i2i7);
		perceptron.ajouterArete(i2i8);
		perceptron.ajouterArete(i3i7);
		perceptron.ajouterArete(i3i8);
		perceptron.ajouterArete(i4i7);
		perceptron.ajouterArete(i4i8);
		perceptron.ajouterArete(i5i7);
		perceptron.ajouterArete(i5i8);
		perceptron.ajouterArete(i6i7);
		perceptron.ajouterArete(i6i8);

		perceptron.ajouterArete(i7i9);
		perceptron.ajouterArete(i7i10);
		perceptron.ajouterArete(i8i9);
		perceptron.ajouterArete(i8i10);

		perceptron.ajouterArete(i9i11);
		perceptron.ajouterArete(i9i12);
		perceptron.ajouterArete(i10i11);
		perceptron.ajouterArete(i10i12);

		perceptron.ajouterArete(i11s1);
		perceptron.ajouterArete(i11s2);
		perceptron.ajouterArete(i11s3);
		perceptron.ajouterArete(i12s1);
		perceptron.ajouterArete(i12s2);
		perceptron.ajouterArete(i12s3);

		perceptron.ajouterArete(e1i9);
		perceptron.ajouterArete(e2i9);
		perceptron.ajouterArete(e3i9);
		perceptron.ajouterArete(e4i9);
		perceptron.ajouterArete(e1i10);
		perceptron.ajouterArete(e2i10);
		perceptron.ajouterArete(e3i10);
		perceptron.ajouterArete(e4i10);

		perceptron.ajouterArete(e1i11);
		perceptron.ajouterArete(e2i11);
		perceptron.ajouterArete(e3i11);
		perceptron.ajouterArete(e4i11);
		perceptron.ajouterArete(e1i12);
		perceptron.ajouterArete(e2i12);
		perceptron.ajouterArete(e3i12);
		perceptron.ajouterArete(e4i12);

		System.out.println("Perceptron pret (" + perceptron.nombreNoeuds() + " noeud(s) et " + perceptron.nombreAretes()
				+ " arete(s)).\n");
	}

	/**
	 * melange la base IRIS et la normalise
	 * 
	 * @category initialisation
	 */
	private void traitementPostApprentissage() {
		melanger(fleurs);
		normaliserLigne(fleurs);
		// c.afficherFleurs(fleurs);
		/*
		 * Initialisation des poids entre -0.1 et 0.1
		 */
		perceptron.initialisationPoidsAleatoire();
	}

	/**
	 * apprentissage des poids synaptiques du reseau
	 * 
	 * @category apprentissage
	 */
	private void apprentissageReseau() {
		System.out.print("Apprentissage...");
		/*
		 * Recuperation des neurones entree et sortie
		 */
		NeuroneEntree entree1 = (NeuroneEntree) perceptron.getNoeud(11);
		NeuroneEntree entree2 = (NeuroneEntree) perceptron.getNoeud(12);
		NeuroneEntree entree3 = (NeuroneEntree) perceptron.getNoeud(13);
		NeuroneEntree entree4 = (NeuroneEntree) perceptron.getNoeud(14);
		NeuroneSortie sortie1 = (NeuroneSortie) perceptron.getNoeud(61);
		NeuroneSortie sortie2 = (NeuroneSortie) perceptron.getNoeud(62);
		NeuroneSortie sortie3 = (NeuroneSortie) perceptron.getNoeud(63);

		double erreurPropagationCourante = 0.9;
		double erreurPropagationAvant = 1;
		//while (erreurPropagationAvant > erreurPropagationCourante && erreurPropagationCourante > 0.1) {
		while (erreurPropagationAvant > erreurPropagationCourante && erreurPropagationCourante > CRITERE_ARRET) {
			erreurPropagationAvant = erreurPropagationCourante;
			erreurPropagationCourante = 0;
			// boucle principale d'exécution
			for (int i = 0; i < NB_FLEURS_BASE_APP; i++) {
				// set des neurones d'entrée
				entree1.setValeurSynaptique(fleurs.get(i).longueurSepale);
				entree2.setValeurSynaptique(fleurs.get(i).largeurSpeale);
				entree3.setValeurSynaptique(fleurs.get(i).longueurPetale);
				entree4.setValeurSynaptique(fleurs.get(i).largeurPetale);
				// set des neurones de sortie
				int cas = fleurs.get(i).getClasseAttendue();
				switch (cas) {
				case 1:
					sortie1.setValeurAttendue(1);
					sortie2.setValeurAttendue(0);
					sortie3.setValeurAttendue(0);
					break;
				case 2:
					sortie1.setValeurAttendue(0);
					sortie2.setValeurAttendue(1);
					sortie3.setValeurAttendue(0);
					break;
				case 3:
					sortie1.setValeurAttendue(0);
					sortie2.setValeurAttendue(0);
					sortie3.setValeurAttendue(1);
					break;
				default:
					System.out.println("ERREUR");
					break;
				}
				perceptron.propagationActivite();
				// System.out.println(perceptron.toString()+"\n");
				//System.out.println(perceptron.erreurPropagation());
				erreurPropagationCourante += perceptron.erreurPropagation();
				perceptron.retropropagationErreur();
				perceptron.miseAJourPoids();
			}
			erreurPropagationCourante = (double)((double)erreurPropagationCourante/(double)NB_FLEURS_BASE_APP);
			//System.out.println(erreurPropagationAvant+" -> "+erreurPropagationCourante);
		}
		System.out.println(" Termine.\n");
		System.out.println("Moyenne erreur propagation finale = " + erreurPropagationCourante + "\n");
	}

	/**
	 * test du reseau
	 * 
	 * @category test
	 */
	private void testReseau() {
		NeuroneEntree entree1 = (NeuroneEntree) perceptron.getNoeud(11);
		NeuroneEntree entree2 = (NeuroneEntree) perceptron.getNoeud(12);
		NeuroneEntree entree3 = (NeuroneEntree) perceptron.getNoeud(13);
		NeuroneEntree entree4 = (NeuroneEntree) perceptron.getNoeud(14);

		String etiquette = "";
		String prediction = "";
		int predictionCorrect = 0;
		int predictionIncorrect = 0;
		StringBuilder sb;
		double tauxBonneReconnaissance = 0;
		for (int i = NB_FLEURS_BASE_APP; i < fleurs.size(); i++) {
			sb = new StringBuilder();
			entree1.setValeurSynaptique(fleurs.get(i).longueurSepale);
			entree2.setValeurSynaptique(fleurs.get(i).largeurSpeale);
			entree3.setValeurSynaptique(fleurs.get(i).longueurPetale);
			entree4.setValeurSynaptique(fleurs.get(i).largeurPetale);
			perceptron.propagationActivite();
			etiquette = fleurs.get(i).classe;
			prediction = perceptron.prediction();
			sb.append("Fleur : " + fleurs.get(i).classe + " Prediction : " + perceptron.prediction());
			if (etiquette.equals(prediction)) {
				predictionCorrect++;
				sb.append(" Vrai");
			} else {
				predictionIncorrect++;
				sb.append(" Faux");
			}
			//System.out.println(sb.toString());
		}
		System.out.println("Nombre de prediction correcte : " + predictionCorrect);
		System.out.println("Nombre de prediction incorrecte : " + predictionIncorrect);
		tauxBonneReconnaissance = (double) predictionCorrect
				/ ((double) predictionCorrect + (double) predictionIncorrect);
		System.out.println("Taux de bonne reconnaissance : " + tauxBonneReconnaissance * 100.0 + "%\n");
		this.tauxBonneReco = tauxBonneReconnaissance;
	}

	/**
	 * affichage des fleurs de la base IRIS
	 * 
	 * @param fleurs
	 * @category affichage
	 */
	private void afficherFleurs(ArrayList<Fleur> fleurs) {
		for (int i = 0; i < fleurs.size(); i++) {
			System.out.println(fleurs.get(i).toString());
		}
	}

	/**
	 * melange la base IRIS
	 * 
	 * @param fleurs
	 * @return fleurs melangees
	 * @category initialisation
	 */
	private ArrayList<Fleur> melanger(ArrayList<Fleur> fleurs) {
		ArrayList<Fleur> nouvelle = fleurs;
		Collections.shuffle(fleurs);
		return nouvelle;

	}

	/**
	 * normalise la base IRIS
	 * 
	 * @param fleurs
	 * @return fleurs normalisees
	 * @category initialisation
	 */
	private ArrayList<Fleur> normaliserLigne(ArrayList<Fleur> fleurs) {
		double somme = 0;
		for (int i = 0; i < fleurs.size(); i++) {
			somme = fleurs.get(i).longueurSepale + fleurs.get(i).largeurSpeale + fleurs.get(i).longueurPetale
					+ fleurs.get(i).largeurPetale;
			fleurs.get(i).longueurSepale /= somme;
			fleurs.get(i).largeurSpeale /= somme;
			fleurs.get(i).longueurPetale /= somme;
			fleurs.get(i).largeurPetale /= somme;
		}
		return fleurs;
	}

	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream(new File("apprentissage.txt")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		System.out.println("Projet Apprentissage Numerique - Perceptron Multi Couche\n");
		double meilleurTauxReco = Double.MIN_VALUE;
		String poidsPerceptron = "";
		for (int i = 0; i < NB_APPRENTISSAGE; i++) {
			MainClass mc = new MainClass();
			if(mc.tauxBonneReco > meilleurTauxReco){
				meilleurTauxReco = mc.tauxBonneReco;
				poidsPerceptron = mc.perceptron.affichagePoids();
			}
		}
		System.out.println(poidsPerceptron);
	}

}