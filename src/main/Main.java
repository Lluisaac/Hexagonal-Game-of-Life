package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {
	
	private Map map;
	
    public Main() {
        super("Hexagonal Game of Life");
        
        this.map = new Map();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.map.render(g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	
    }
    
    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Main(), 640, 480, false).start();
    }
}