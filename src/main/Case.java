package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Case {
	public static final int LONGUEUR_CASE = 32;
	public static final int HAUTEUR_CASE = 29;
	public static final int BUFFER_CASE = 8;

	private int x;
	private int y;
	private boolean estVivant;
	
	public Case(int i, int j) {
		this.x = i;
		this.y = j;
		this.estVivant = false;
	}

	public Image getImage() throws SlickException {
		if(estVivant) {
			return new Image("assets/alive.png");	
		}
		else {
			return new Image("assets/dead.png");	
		}
	}

	public float getX() {
		return Case.getXValueFrom(this.x, this.y);
	}
	
	public float getY() {
		return Case.getYValueFrom(this.y);
	}

	public static float getXValueFrom(int column, int row) {
		if ((row % 2) == 0) {
			return column * Case.LONGUEUR_CASE;
		} else {
			return (column * Case.LONGUEUR_CASE) + (Case.LONGUEUR_CASE / 2);
		}
	}

	public static float getYValueFrom(int row) {
		if (row != 0) {
			return ((row * Case.HAUTEUR_CASE) - (Case.BUFFER_CASE * row)) + Case.BUFFER_CASE + 50;
		} else {
			return Case.BUFFER_CASE + 50;
		}
	}

	public void setVivant(boolean b) {
		this.estVivant = b;
	}
}
