package deepLearning;

public class Neurone {
	
	private final int id;
	private String etiquette;
	private double valeurSynaptique;
	private double delta;
	
	public Neurone(int id, String etiquette){
		this.id = id;
		this.etiquette = etiquette;
		this.valeurSynaptique = 0;
		this.delta = 0;
	}
	
	public Neurone(int id, String etiquette, double valeurSynaptique){
		this.id = id;
		this.etiquette = etiquette;
		this.valeurSynaptique = valeurSynaptique;
		this.delta = 0;
	}
	
	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public int getId(){
		return id;
	}
	
	public String getEtiquette(){
		return etiquette;
	}
	
	public void setEtiquette(String etiquette){
		this.etiquette = etiquette;
	}
	
	public double getValeurSynaptique(){
		return this.valeurSynaptique;
	}
	
	public void setValeurSynaptique(double valeurSynaptique){
		this.valeurSynaptique = valeurSynaptique;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Neurone other = (Neurone) obj;
		if (this.id != other.id || this.etiquette != other.etiquette)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 7;
		int result = prime * this.id + this.etiquette.length();
		return result;
	}

	@Override
	public String toString() {
		return "Noeud["+this.id+", "+this.etiquette+", "+this.valeurSynaptique+","+this.delta+"]";
	}
	
}
