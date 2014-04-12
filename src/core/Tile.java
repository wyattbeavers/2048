package core;
import java.awt.Color;

public class Tile {
	private int value;
	private Color color;
	
	/**
	 * Creates a tile with a Random color.
	 * 
	 * @param v the value of this tile
	 */
	protected Tile(int v) {
		float r = R.rng.nextFloat();
		float g = R.rng.nextFloat();
		float b = R.rng.nextFloat();
		
		Color c = new Color(r,g,b);
		
		this.value = v;
		this.color = c;
	}
	
	/**
	 * Creates a tile with a specified value and color.
	 * 
	 * @param v the value of this tile
	 * @param c the color of this tile
	 */
	protected Tile(int v, Color c) {
		this.value = v;
		this.color = c;
	}
	
	public int getValue() {
		return value;
	}
	
	public Color getColor() {
		return color;
	}
	
	/**
	 * Combines this tile with another tile and returns the result.
	 * If the value of the new tile has not been used before, a random
	 * color is generated for the new tile.
	 * 
	 * @param other the other tile
	 * @return a tile that is the combination of <code>this</code> and other.
	 */
	public Tile combine(Tile other) {
		int newValue = this.value + other.getValue();
		Tile t = getTileByValue(newValue);
		
		if (t == null) {
			t = new Tile(newValue);
			R.tiles.put(t.getValue(), t);
		}
		
		return t;
	}
	
	/**
	 * Looks up and returns the Tile associated with the specified value.
	 * @param value
	 * @return the tile associated with <code>value</code>, null if there is no such tile.
	 */
	public static Tile getTileByValue(int value) {
		return R.tiles.get(value);
	}
}
