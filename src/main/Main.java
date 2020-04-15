package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {
	
    public Main() {
        super("Lesson 1 :: WindowGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	g.drawImage(new Image("assets/alive.png"), 25, 25);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	
    }
    
    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Main(), 640, 480, false).start();
    }
}