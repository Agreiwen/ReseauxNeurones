package deepLearning;

public class Fleur {
	
	
	public double longueurSepale;
	public double largeurSpeale;
	public double longueurPetale;
	public double largeurPetale;
	public String classe;
	
	
	public Fleur(double a, double b, double c, double d, String classe){
		this.longueurSepale=a;
		this.largeurSpeale=b;
		this.longueurPetale=c;
		this.largeurPetale=d;
		this.classe=classe;
	}
	
	//pour le main et les neurones de sortie
	public int getClasseAttendue(){
		if(classe.equals("Iris-setosa"))return 1;
		if(classe.equals("Iris-versicolor"))return 2;
		if(classe.equals("Iris-virginica"))return 3;
		return -1;
	}


	public double getLongueurSepale() {
		return longueurSepale;
	}


	public void setLongueurSepale(double longueurSepale) {
		this.longueurSepale = longueurSepale;
	}


	public double getLargeurSpeale() {
		return largeurSpeale;
	}


	public void setLargeurSpeale(double largeurSpeale) {
		this.largeurSpeale = largeurSpeale;
	}


	public double getLongueurPetale() {
		return longueurPetale;
	}


	public void setLongueurPetale(double longueurPetale) {
		this.longueurPetale = longueurPetale;
	}


	public double getLargeurPetale() {
		return largeurPetale;
	}


	public void setLargeurPetale(double largeurPetale) {
		this.largeurPetale = largeurPetale;
	}


	public String getClasse() {
		return classe;
	}


	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String toString(){
		return (longueurSepale+" "+largeurSpeale+" "+longueurPetale+" "+largeurPetale+" "+classe);
	}
}
