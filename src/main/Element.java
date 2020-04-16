package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Element {
	
	public abstract Image getImage() throws SlickException;
	public abstract float getX();
	public abstract float getY();
	public abstract void click();
	
	public boolean contiensCoordonnees(float x, float y) throws SlickException {
		boolean estAvant = (x < this.getX()) || (y < this.getY());
		boolean estApres = (x > (this.getX() + this.getImage().getWidth()))
				|| (y > (this.getY() + this.getImage().getHeight()));

		return !estAvant && !estApres && !this.dansTransparence(x, y);
	}
	
	public boolean dansTransparence(float x, float y) throws SlickException {
		int spriteX = (int) (x - this.getX());
		int spriteY = (int) (y - this.getY());

		Image sprite = this.getImage();
		Color pixelColor = sprite.getColor(spriteX, spriteY);

		return pixelColor.getAlpha() == 0;
	}
}
