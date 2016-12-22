package deepLearning;

public class MainClass {

	public static void main(String[] args) {
		
		System.out.println("Projet Apprentissage Numerique - Perceptron Multi Couche\n");
		
		Perceptron test = new Perceptron();
		
		System.out.print("Creation des neurones... ");
		
		NeuroneEntree e1 = new NeuroneEntree(0, "e1");
		NeuroneEntree e2 = new NeuroneEntree(1, "e2");
		NeuroneEntree e3 = new NeuroneEntree(2, "e3");
		
		NeuroneIntermediaire i1 = new NeuroneIntermediaire(3, "i1", 1);
		NeuroneIntermediaire i2 = new NeuroneIntermediaire(4, "i2", 1);
		NeuroneIntermediaire i3 = new NeuroneIntermediaire(5, "i3", 1);
		NeuroneIntermediaire i4 = new NeuroneIntermediaire(6, "i4", 1);
		NeuroneIntermediaire i5 = new NeuroneIntermediaire(7, "i5", 1);
		NeuroneIntermediaire i6 = new NeuroneIntermediaire(8, "i6", 1);
		
		NeuroneIntermediaire i7 = new NeuroneIntermediaire(9, "i7", 2);
		NeuroneIntermediaire i8 = new NeuroneIntermediaire(10, "i8", 2);
		
		NeuroneSortie s1 = new NeuroneSortie(11, "s1");
		
		System.out.println("Creation des aretes... ");
		
		Arete e1i1 = new Arete(e1, i1);
		Arete e1i2 = new Arete(e1, i2);
		Arete e1i3 = new Arete(e1, i3);
		Arete e1i4 = new Arete(e1, i4);
		
		Arete e2i1 = new Arete(e2, i1);
		Arete e2i2 = new Arete(e2, i2);
		Arete e2i3 = new Arete(e2, i3);
		Arete e2i4 = new Arete(e2, i4);
		Arete e2i5 = new Arete(e2, i5);
		Arete e2i6 = new Arete(e2, i6);
		
		Arete e3i3 = new Arete(e3, i3);
		Arete e3i4 = new Arete(e3, i4);
		Arete e3i5 = new Arete(e3, i5);
		Arete e3i6 = new Arete(e3, i6);
		
		Arete i1i7 = new Arete(i1, i7);
		Arete i2i7 = new Arete(i2, i7);
		Arete i3i7 = new Arete(i3, i7);
		Arete i4i7 = new Arete(i4, i7);
		Arete i5i7 = new Arete(i5, i7);
		Arete i6i7 = new Arete(i6, i7);
		
		Arete i1i8 = new Arete(i1, i8);
		Arete i2i8 = new Arete(i2, i8);
		Arete i3i8 = new Arete(i3, i8);
		Arete i4i8 = new Arete(i4, i8);
		Arete i5i8 = new Arete(i5, i8);
		Arete i6i8 = new Arete(i6, i8);
		
		Arete i7s1 = new Arete(i7, s1);
		Arete i8s1 = new Arete(i8, s1);
		
		test.ajouterNoeud(e1);
		test.ajouterNoeud(e2);
		test.ajouterNoeud(e3);
		
		test.ajouterNoeud(i1);
		test.ajouterNoeud(i2);
		test.ajouterNoeud(i3);
		test.ajouterNoeud(i4);
		test.ajouterNoeud(i5);
		test.ajouterNoeud(i6);
		
		test.ajouterNoeud(i7);
		test.ajouterNoeud(i8);
		
		test.ajouterNoeud(s1);
		
		test.ajouterArete(e1i1);
		test.ajouterArete(e1i2);
		test.ajouterArete(e1i3);
		test.ajouterArete(e1i4);
		
		test.ajouterArete(e2i1);
		test.ajouterArete(e2i2);
		test.ajouterArete(e2i3);
		test.ajouterArete(e2i4);
		test.ajouterArete(e2i5);
		test.ajouterArete(e2i6);
		
		test.ajouterArete(e3i3);
		test.ajouterArete(e3i4);
		test.ajouterArete(e3i5);
		test.ajouterArete(e3i6);
		
		test.ajouterArete(i1i7);
		test.ajouterArete(i1i8);
		test.ajouterArete(i2i7);
		test.ajouterArete(i2i8);
		test.ajouterArete(i3i7);
		test.ajouterArete(i3i8);
		test.ajouterArete(i4i7);
		test.ajouterArete(i4i8);
		test.ajouterArete(i5i7);
		test.ajouterArete(i5i8);
		test.ajouterArete(i6i7);
		test.ajouterArete(i6i8);
		
		test.ajouterArete(i7s1);
		test.ajouterArete(i8s1);
		
		System.out.println("Perceptron pret ("+test.nombreNoeuds()+" noeud(s) et "+test.nombreAretes()+" arete(s)).\n");
		
		System.out.println("Precedant de s1 ("+test.precedent(s1).size()+") : "+test.precedent(s1).toString());
		System.out.println("Precedant de i7 ("+test.precedent(i7).size()+") : "+test.precedent(i7).toString());
		System.out.println("Precedant de i3 ("+test.precedent(i3).size()+") : "+test.precedent(i3).toString());
		System.out.println("Precedant de i1 ("+test.precedent(i1).size()+") : "+test.precedent(i1).toString());
		System.out.println("Precedant de e1 ("+test.precedent(e1).size()+") : "+test.precedent(e1).toString());
		
	}

}
