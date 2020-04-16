package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {

	public static final int TICK_LENGTH = 1000;
	private Map map;
	private int surplus;

	public Main() {
		super("Hexagonal Game of Life");
		this.surplus = 0;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.map = new Map();
		
		container.getInput().addMouseListener(new MouseListener() {
			
			@Override
			public void setInput(Input arg0) {
			}

			@Override
			public boolean isAcceptingInput() {
				return true;
			}

			@Override
			public void inputStarted() {
			}

			@Override
			public void inputEnded() {
			}

			@Override
			public void mouseWheelMoved(int arg0) {
			}

			@Override
			public void mouseReleased(int button, int x, int y) {
				if (button == Input.MOUSE_LEFT_BUTTON) {
					try {
						map.click(x, y);
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void mousePressed(int arg0, int arg1, int arg2) {
			}

			@Override
			public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
			}
		});
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.map.render(g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		this.surplus += delta;
		int ticksFaits = (this.surplus / Main.TICK_LENGTH);
		this.surplus %= Main.TICK_LENGTH;
		
		for(int i = 0; i < ticksFaits; i++) {
			this.map.update();
		}
	}

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new Main(), 1600, 900, false).start();
	}
}