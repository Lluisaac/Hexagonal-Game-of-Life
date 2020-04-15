package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Map {
	public static final int LONGUEUR = 25;
	public static final int HAUTEUR = 25;
	
	private Case[][] map;
	
	public Map () {
		this.map = new Case[Map.LONGUEUR][Map.HAUTEUR];
		
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				this.map[i][j] = new Case(i, j);
			}
		}
	}
	
	public void render(Graphics g) throws SlickException {
		
		for (int i = 0; i < Map.LONGUEUR; i++) {
			for (int j = 0; j < Map.HAUTEUR; j++) {
				Case maCase = this.map[j][i]; 
				g.drawImage(maCase.getImage(), maCase.getX(), maCase.getY());
			}
		}
	}

	public void click(int x, int y) {
		this.map[0][0].setVivant(true);
	}
}
