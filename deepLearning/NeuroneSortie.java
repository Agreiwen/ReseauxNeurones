package deepLearning;

public class NeuroneSortie extends Neurone{
	
	private double valeurAttendue;
	
	public NeuroneSortie(int id, String etiquette) {
		super(id, etiquette);
		this.valeurAttendue = 0;
	}

	public NeuroneSortie(int id, String etiquette, double valeurSynaptique) {
		super(id, etiquette, valeurSynaptique);
		this.valeurAttendue = 0;
	}

	public double getValeurAttendue() {
		return valeurAttendue;
	}
	
	public void setValeurAttendue(double valeurAttendue){
		this.valeurAttendue = valeurAttendue;
	}
}
