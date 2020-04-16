package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bouton extends Element {
	
	private int x;
	private int y;
	private Image sprite;
	private Map map;
	
	public Bouton(String chemin, int x, int y, Map map) throws SlickException{
		this.x = x;
		this.y = y;
		this.sprite = new Image(chemin); 
		this.map = map;
	}

	@Override
	public Image getImage() {
		return this.sprite;
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	@Override
	public void click() {
		this.map.start();
	}

}
