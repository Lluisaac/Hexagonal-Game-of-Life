package main;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Map {
	public static final int LONGUEUR = 48;
	public static final int HAUTEUR = 34;

	private List<Case> map;
	private Bouton actual;
	private Bouton start;
	private Bouton stop;
	private boolean started;

	public Map() throws SlickException {
		this.map = new ArrayList<Case>();
		
		this.start = new Bouton("assets/start.png", 1400, 800) {
			@Override
			public void click() {
				start();
			}
		};
		
		this.stop = new Bouton("assets/stop.png", 1400, 800) {

			@Override
			public void click() {
				stop();
			}
			
		};
		
		this.actual = this.start;
		this.started = false;

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

			indice = this.getValueFrom(((x + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR,
					((y - 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));

			indice = this.getValueFrom(((x - 1 + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR,
					((y - 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));

			indice = this.getValueFrom(((x + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR,
					((y + 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));

			indice = this.getValueFrom(((x - 1 + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR,
					((y + 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));
			
			indice = this.getValueFrom(x, ((y + 2) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));

			indice = this.getValueFrom(x, ((y - 2) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));

			indice = this.getValueFrom(((x - 2 + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR,
					((y - 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));

			indice = this.getValueFrom(((x - 2 + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR,
					((y + 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));

			indice = this.getValueFrom(((x + 1 + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR,
					((y - 1) + Map.HAUTEUR) % Map.HAUTEUR);
			adjacentes.add(this.map.get(indice));

			indice = this.getValueFrom(((x + 1 + (y % 2)) + Map.LONGUEUR) % Map.LONGUEUR,
					((y + 1) + Map.HAUTEUR) % Map.HAUTEUR);
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

		g.drawImage(this.actual.getImage(), this.actual.getX(), this.actual.getY());
	}

	public void click(int x, int y) throws SlickException {
		Element choisi = this.getElementAt(x, y);
		
		if(choisi != null) {
			choisi.click();
		}

	}

	protected Element getElementAt(float x, float y) throws SlickException {

		List<Element> elements = new ArrayList<Element>(this.map);
		elements.add(this.actual);

		for (int i = 0; i < elements.size(); i++) {
			Element actuel = elements.get(i);

			if (!actuel.contiensCoordonnees(x, y)) {
				elements.remove(actuel);
				i--;
			}
		}

		if (elements.size() > 0) {
			return elements.get(elements.size() - 1);
		} else {
			return null;
		}
	}

	public void update() {
		if (this.started) {
			for (Case case1 : this.map) {
				case1.preUpdate();
			}
			for (Case case1 : this.map) {
				case1.postUpdate();
			}
		}
	}

	public void start() {
		this.started = true;
		this.actual = this.stop;
	}
	
	public void stop() {
		this.started = false;
		this.actual = this.start;
	}
}
