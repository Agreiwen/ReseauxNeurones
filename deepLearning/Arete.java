package deepLearning;

public class Arete {
	
	private Neurone source;
	private Neurone destination;
	private double poidsSynaptique;
	
	public Arete(Neurone source, Neurone destination, int poidsSynaptique){
		this.source = source;
		this.destination = destination;
		this.poidsSynaptique = poidsSynaptique;
	}
	
	public Arete(Neurone source, Neurone destination){
		this.source = source;
		this.destination = destination;
		this.poidsSynaptique = 1;
	}
	
	public Neurone getSource(){
		return this.source;
	}
	
	public Neurone getDestination(){
		return this.destination;
	}
	
	public double getPoidsSynaptique(){
		return this.poidsSynaptique;
	}
	
	public void setPoidsSynaptique(double poids){
		this.poidsSynaptique = poids;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Arete other = (Arete) obj;
		if (this.poidsSynaptique != other.poidsSynaptique || this.destination != other.destination || this.source != other.source)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 7;
		int result = (int) (prime * this.poidsSynaptique + this.source.getEtiquette().length() + this.destination.getEtiquette().length());
		return result;
	}

	@Override
	public String toString() {
		return "Arete["+this.source.getEtiquette()+", "+this.destination.getEtiquette()+", "+this.poidsSynaptique+"]";
	}

	
	
}
