package deepLearning;

public class Noeud {
	
	private final int id;
	private String etiquette;
	
	public Noeud(int id, String etiquette){
		this.id = id;
		this.etiquette = etiquette;
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

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Noeud other = (Noeud) obj;
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
		return "Noeud["+this.id+", "+this.etiquette+"]";
	}
	
}
