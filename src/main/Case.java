package main;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Case extends Element {
	private static final int OFFSET = 50;
	public static final int LONGUEUR_CASE = 32;
	public static final int HAUTEUR_CASE = 29;
	public static final int BUFFER_CASE = 8;

	private int x;
	private int y;
	private boolean estVivant;
	private boolean prochainEtat;
	
	private List<Case> adjacentes;

	public Case(int i, int j) {
		this.x = i;
		this.y = j;
		this.estVivant = false;
		this.prochainEtat = false;

		this.adjacentes = new ArrayList<Case>();
	}

	public boolean addAdjacente(Case adjacente) {
		return this.adjacentes.add(adjacente);
	}

	public boolean addAllAdjacente(List<Case> adjacentes) {
		return this.adjacentes.addAll(adjacentes);
	}
	
	@Override
	public Image getImage() throws SlickException {
		if (estVivant) {
			return new Image("assets/alive.png");
		} else {
			return new Image("assets/dead.png");
		}
	}
	
	@Override
	public float getX() {
		return Case.getXValueFrom(this.x, this.y);
	}

	@Override
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
			return ((row * Case.HAUTEUR_CASE) - (Case.BUFFER_CASE * row)) + Case.BUFFER_CASE + Case.OFFSET;
		} else {
			return Case.BUFFER_CASE + Case.OFFSET;
		}
	}

	public void setVivant(boolean b) {
		this.estVivant = b;
	}

	public void inverserVivant() {
		this.estVivant = !this.estVivant;
	}

	public void preUpdate() {
		int vivantAdjacents = this.getNombreVivantsAdjacents();
		this.prochainEtat = this.estVivant;
		if(this.estVivant) {
			if (vivantAdjacents != 2 && vivantAdjacents != 3) {
				this.prochainEtat = false;
			} 
		} else {
			if(vivantAdjacents == 3) {
				this.prochainEtat = !this.estVivant;
			}	
		}
	}

	private int getNombreVivantsAdjacents() {
		int compteur = 0;
		for (Case case1 : this.adjacentes) {
			if(case1.estVivant) {
				compteur++;
			}
		}
		return compteur;
	}

	public void postUpdate() {
		this.setVivant(this.prochainEtat);
	}

	@Override
	public void click() {
		this.inverserVivant();
	}
}
