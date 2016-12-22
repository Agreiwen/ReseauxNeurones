package deepLearning;

public class NeuroneIntermediaire extends Noeud{

	private final int couche;
	
	public NeuroneIntermediaire(int id, String etiquette, int couche) {
		super(id, etiquette);
		this.couche = couche;
	}

}
