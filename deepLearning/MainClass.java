package deepLearning;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.xml.sax.ext.LexicalHandler;

public class MainClass {

	public MainClass() {

	}

	public ArrayList<Fleur> lectureFichier(String fichier) throws IOException {
		int tailleBase = 0;
		BufferedReader br1, br2;
		String st;
		ArrayList<Fleur> fleurs = new ArrayList<Fleur>();
		br1 = new BufferedReader(new FileReader(fichier));

		System.out.println("Lecture fichier... ");
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
		return fleurs;
	}

	public static void main(String[] args) {

		System.out.println("Projet Apprentissage Numerique - Perceptron Multi Couche\n");

		Perceptron test = new Perceptron();

		System.out.print("Creation des neurones... ");

		NeuroneEntree e1 = new NeuroneEntree(11, "e1");
		NeuroneEntree e2 = new NeuroneEntree(12, "e2");
		NeuroneEntree e3 = new NeuroneEntree(13, "e3");
		NeuroneEntree e4 = new NeuroneEntree(14, "e4");

		NeuroneIntermediaire i1 = new NeuroneIntermediaire(21, "i1", 1);
		NeuroneIntermediaire i2 = new NeuroneIntermediaire(22, "i2", 1);
		NeuroneIntermediaire i3 = new NeuroneIntermediaire(23, "i3", 1);
		NeuroneIntermediaire i4 = new NeuroneIntermediaire(24, "i4", 1);
		NeuroneIntermediaire i5 = new NeuroneIntermediaire(25, "i5", 1);

		NeuroneSortie s1 = new NeuroneSortie(31, "Iris-setosa");
		NeuroneSortie s2 = new NeuroneSortie(32, "Iris-versicolor");
		NeuroneSortie s3 = new NeuroneSortie(33, "Iris-virginica");

		System.out.println("Creation des aretes... ");

		Arete e1i1 = new Arete(e1, i1);
		Arete e1i2 = new Arete(e1, i2);
		Arete e1i3 = new Arete(e1, i3);
		Arete e1i4 = new Arete(e1, i4);
		Arete e1i5 = new Arete(e1, i5);

		Arete e2i1 = new Arete(e2, i1);
		Arete e2i2 = new Arete(e2, i2);
		Arete e2i3 = new Arete(e2, i3);
		Arete e2i4 = new Arete(e2, i4);
		Arete e2i5 = new Arete(e2, i5);

		Arete e3i1 = new Arete(e3, i1);
		Arete e3i2 = new Arete(e3, i2);
		Arete e3i3 = new Arete(e3, i3);
		Arete e3i4 = new Arete(e3, i4);
		Arete e3i5 = new Arete(e3, i5);

		Arete e4i1 = new Arete(e4, i1);
		Arete e4i2 = new Arete(e4, i2);
		Arete e4i3 = new Arete(e4, i3);
		Arete e4i4 = new Arete(e4, i4);
		Arete e4i5 = new Arete(e4, i5);

		Arete i1s1 = new Arete(i1, s1);
		Arete i2s1 = new Arete(i2, s1);
		Arete i3s1 = new Arete(i3, s1);
		Arete i4s1 = new Arete(i4, s1);
		Arete i5s1 = new Arete(i5, s1);

		Arete i1s2 = new Arete(i1, s2);
		Arete i2s2 = new Arete(i2, s2);
		Arete i3s2 = new Arete(i3, s2);
		Arete i4s2 = new Arete(i4, s2);
		Arete i5s2 = new Arete(i5, s2);

		Arete i1s3 = new Arete(i1, s3);
		Arete i2s3 = new Arete(i2, s3);
		Arete i3s3 = new Arete(i3, s3);
		Arete i4s3 = new Arete(i4, s3);
		Arete i5s3 = new Arete(i5, s3);

		test.ajouterNeurone(e1);
		test.ajouterNeurone(e2);
		test.ajouterNeurone(e3);
		test.ajouterNeurone(e4);

		test.ajouterNeurone(i1);
		test.ajouterNeurone(i2);
		test.ajouterNeurone(i3);
		test.ajouterNeurone(i4);
		test.ajouterNeurone(i5);

		test.ajouterNeurone(s1);
		test.ajouterNeurone(s2);
		test.ajouterNeurone(s3);

		test.ajouterArete(e1i1);
		test.ajouterArete(e1i2);
		test.ajouterArete(e1i3);
		test.ajouterArete(e1i4);
		test.ajouterArete(e1i5);

		test.ajouterArete(e2i1);
		test.ajouterArete(e2i2);
		test.ajouterArete(e2i3);
		test.ajouterArete(e2i4);
		test.ajouterArete(e2i5);

		test.ajouterArete(e3i1);
		test.ajouterArete(e3i2);
		test.ajouterArete(e3i3);
		test.ajouterArete(e3i4);
		test.ajouterArete(e3i5);

		test.ajouterArete(e4i1);
		test.ajouterArete(e4i2);
		test.ajouterArete(e4i3);
		test.ajouterArete(e4i4);
		test.ajouterArete(e4i5);

		test.ajouterArete(i1s1);
		test.ajouterArete(i2s1);
		test.ajouterArete(i3s1);
		test.ajouterArete(i4s1);
		test.ajouterArete(i5s1);

		test.ajouterArete(i1s2);
		test.ajouterArete(i2s2);
		test.ajouterArete(i3s2);
		test.ajouterArete(i4s2);
		test.ajouterArete(i5s2);

		test.ajouterArete(i1s3);
		test.ajouterArete(i2s3);
		test.ajouterArete(i3s3);
		test.ajouterArete(i4s3);
		test.ajouterArete(i5s3);

		System.out.println(
				"Perceptron pret (" + test.nombreNoeuds() + " noeud(s) et " + test.nombreAretes() + " arete(s)).\n");
		// parse fichier IRIS

		// lecture base iris
		MainClass c = new MainClass();
		ArrayList<Fleur> fleurs = null;
		try {
			fleurs = c.lectureFichier("src/iris.data.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// melange de la liste de fleurs
		c.melanger(fleurs);
		c.afficherFleurs(fleurs);
		c.normaliser(fleurs);

		test.initialisationPoidsAleatoire();
		
		// boucle principale d'exécution
		for (int i = 0; i < fleurs.size(); i++) {
			//set des neurones d'entrée
			e1.setValeurSynaptique(fleurs.get(i).longueurSepale);
			e2.setValeurSynaptique(fleurs.get(i).largeurSpeale);
			e3.setValeurSynaptique(fleurs.get(i).longueurPetale);
			e4.setValeurSynaptique(fleurs.get(i).largeurPetale);
			
			
			//set des neurones de sortie
			int cas = fleurs.get(i).getClasseAttendue();
			switch(cas){
			case 1:
				s1.setValeurAttendue(1);
				s2.setValeurAttendue(0);
				s3.setValeurAttendue(0);
				break;
			case 2:
				s1.setValeurAttendue(0);
				s2.setValeurAttendue(1);
				s3.setValeurAttendue(0);
				break;
			case 3:
				s1.setValeurAttendue(0);
				s2.setValeurAttendue(0);
				s3.setValeurAttendue(1);
				break;
			default:
				System.out.println("ERREUR");
				break;
			}
			
			/***********/
			/** FAIRE **/
			/***********/
			test.propagationActivite();
			
			test.retropropagationErreur();
			test.miseAJourPoids();
		}
		
		
		/*********************************/
		/**version de base (pour tester)**/

		e1.setValeurSynaptique(0.5);
		e2.setValeurSynaptique(0.3431372549019608);
		e3.setValeurSynaptique(0.13725490196078433);
		e4.setValeurSynaptique(0.019607843137254905);

	/*	s1.setValeurAttendue(1);
		s2.setValeurAttendue(0);
		s3.setValeurAttendue(0);
	*/
		test.propagationActivite();
		System.out.println(test.toString() + "\n");
		
	//	System.out.println(test.toString() + "\n");
		
		/******************************/
	}

	public void afficherFleurs(ArrayList<Fleur> fleurs) {
		for (int i = 0; i < fleurs.size(); i++) {
			System.out.println(fleurs.get(i).toString());
		}
	}

	public ArrayList<Fleur> melanger(ArrayList<Fleur> fleurs) {
		ArrayList<Fleur> nouvelle = fleurs;
		Collections.shuffle(fleurs);
		return nouvelle;

	}
	
	public ArrayList<Fleur> normaliser(ArrayList<Fleur> fleurs){
		double sommeLoS=0;
		double sommeLaS=0;
		double sommeLoP=0;
		double sommeLaP=0;
		
		for(int i=0; i<fleurs.size(); i++){
			sommeLoS += fleurs.get(i).longueurSepale;
			sommeLaS += fleurs.get(i).largeurSpeale;
			sommeLoP += fleurs.get(i).longueurPetale;
			sommeLaP += fleurs.get(i).largeurPetale;
		}
		
		for(int i=0;i<fleurs.size();i++){
			fleurs.get(i).longueurSepale /= sommeLoS;
			fleurs.get(i).largeurSpeale /= sommeLaS;
			fleurs.get(i).longueurPetale /= sommeLoP;
			fleurs.get(i).largeurPetale /= sommeLaP;
			
		}
		return fleurs;
	}

}
