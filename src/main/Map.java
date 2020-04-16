package main;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Map {
	public static final int LONGUEUR = 25;
	public static final int HAUTEUR = 25;

	private List<Case> map;

	public Map() {
		this.map = new ArrayList<Case>();

		for (int i = 0; i < Map.HAUTEUR; i++) {
			for (int j = 0; j < Map.LONGUEUR; j++) {
				this.map.add(new Case(i, j));
			}
		}
	}

	public void render(Graphics g) throws SlickException {

		for (int i = 0; i < Map.LONGUEUR; i++) {
			for (int j = 0; j < Map.HAUTEUR; j++) {
				Case maCase = this.map.get((j * Map.LONGUEUR) + i);
				g.drawImage(maCase.getImage(), maCase.getX(), maCase.getY());
			}
		}
	}

	public void click(int x, int y) throws SlickException {
		Case caseChoisie = this.getCaseAt(x, y);

		if (caseChoisie != null) {
			caseChoisie.inverserVivant();
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
