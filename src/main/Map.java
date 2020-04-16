package main;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Map {
	public static final int LONGUEUR = 24;
	public static final int HAUTEUR = 24;

	private List<Case> map;

	public Map() {
		this.map = new ArrayList<Case>();

		for (int i = 0; i < Map.HAUTEUR; i++) {
			for (int j = 0; j < Map.LONGUEUR; j++) {
				this.map.add(new Case(j, i));
			}
		}
		
		this.creerAdjacentes();
	}

	private void creerAdjacentes() {
		for (int i = 0; i < Map.LONGUEUR * Map.HAUTEUR; i++) {
			List<Case> adjacentes = new ArrayList<Case>();
			Case actuelle = this.map.get(i);
			
			int x = this.getXFrom(i);
			int y = this.getYFrom(i);
			
			int indice = this.getValueFrom(((x - 1) + Map.LONGUEUR) % Map.LONGUEUR, y);
			adjacentes.add(this.map.get(indice)); 
			
			indice = this.getValueFrom(((x + 1) + Map.LONGUEUR) % Map.LONGUEUR, y);
			adjacentes.add(this.map.get(indice)); 
			
			indice = this.getValueFrom(((x + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR, ((y - 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice)); 
			
			indice = this.getValueFrom(((x - 1 + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR, ((y - 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice)); 
			
			indice = this.getValueFrom(((x + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR, ((y + 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice)); 
			
			indice = this.getValueFrom(((x - 1 + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR, ((y + 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice)); 
			
			actuelle.addAllAdjacente(adjacentes);
		}
		
	}
	
	public int getXFrom(int value) {
		return value % Map.LONGUEUR;
	}
	
	public int getYFrom(int value) {
		return value / Map.LONGUEUR;
	}
	
	public int getValueFrom(int x, int y) {
		return (y * Map.LONGUEUR) + x;
	}

	public void render(Graphics g) throws SlickException {

		for (int i = 0; i < Map.HAUTEUR; i++) {
			for (int j = 0; j < Map.LONGUEUR; j++) {
				Case maCase = this.map.get((i * Map.LONGUEUR) + j);
				g.drawImage(maCase.getImage(), maCase.getX(), maCase.getY());
			}
		}
	}

	public void click(int x, int y) throws SlickException {
		Case caseChoisie = this.getCaseAt(x, y);

		if (caseChoisie != null) {
			caseChoisie.inverserVivant();
			System.out.println(this.map.indexOf(caseChoisie));
		}

	}

	protected Case getCaseAt(float x, float y) throws SlickException {

		List<Case> cases = new ArrayList<Case>(this.map);

		for (int i = 0; i < cases.size(); i++) {
			Case actuelle = cases.get(i);

			if (!actuelle.contiensCoordonnees(x, y)) {
				cases.remove(actuelle);
				i--;
			}
		}

		if (cases.size() > 0) {
			return cases.get(cases.size() - 1);
		} else {
			return null;
		}
	}
}
