package deepLearning;

public class NeuroneIntermediaire extends Neurone{

	private final int couche;
	
	public NeuroneIntermediaire(int id, String etiquette, int couche) {
		super(id, etiquette);
		this.couche = couche;
	}

	public NeuroneIntermediaire(int id, String etiquette, double valeurSynaptique) {
		super(id, etiquette, valeurSynaptique);
		this.couche = 0;
	}

	public NeuroneIntermediaire(int id, String etiquette) {
		super(id, etiquette);
		this.couche = 0;
	}
	
	public int getCouche(){
		return this.couche;
	}

}
