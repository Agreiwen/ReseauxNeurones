package deepLearning;

public class MainClass {

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
		
		System.out.println("Perceptron pret ("+test.nombreNoeuds()+" noeud(s) et "+test.nombreAretes()+" arete(s)).\n");
		
		
		//set les 4 poids recup�r�s de la base IRIS pour chaque fleur
		
		e1.setValeurSynaptique(0.5);
		e2.setValeurSynaptique(0.3431372549019608);
		e3.setValeurSynaptique(0.13725490196078433);
		e4.setValeurSynaptique(0.019607843137254905);
		
		
		//differenciation la valeur attendue en fonction de la classe de chaque fleur 
		
		s1.setValeurAttendue(1);
		s2.setValeurAttendue(0);
		s3.setValeurAttendue(0);
		
		test.initialisationPoidsAleatoire();
		test.propagationActivite();
		System.out.println(test.toString()+"\n");
		test.retropropagationErreur();
	}

}
