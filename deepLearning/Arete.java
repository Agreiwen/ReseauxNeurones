package deepLearning;

public class Arete {
	
	private Noeud source;
	private Noeud destination;
	private int poids;
	
	public Arete(Noeud source, Noeud destination, int poids){
		this.source = source;
		this.destination = destination;
		this.poids = poids;
	}
	
	public Arete(Noeud source, Noeud destination){
		this.source = source;
		this.destination = destination;
		this.poids = 0;
	}
	
	public Noeud getSource(){
		return this.source;
	}
	
	public Noeud getDestination(){
		return this.destination;
	}
	
	public int getPoids(){
		return this.poids;
	}
	
	public void setPoids(int poids){
		this.poids = poids;
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
		if (this.poids != other.poids || this.destination != other.destination || this.source != other.source)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 7;
		int result = prime * this.poids + this.source.getEtiquette().length() + this.destination.getEtiquette().length();
		return result;
	}

	@Override
	public String toString() {
		return "Arete["+this.source.getEtiquette()+", "+this.destination.getEtiquette()+", "+this.poids+"]";
	}

	
	
}
