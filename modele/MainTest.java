package modele;

public class MainTest {

	public static void main(String[] args) {
		Noeud e1 = new Noeud(0, "e1");
		Noeud e2 = new Noeud(1, "e2");
		Noeud e3 = new Noeud(e1);
		Noeud s = new Noeud(3);
		s.setEtiquette("f");
		
		System.out.println(e1.toString());
		System.out.println(s.toString());
		System.out.println(e1.equals(e3));
	}

}
