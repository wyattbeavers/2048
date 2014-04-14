package core;

public class Tile {
	private int value;
	private int row, col;
	private Grid grid;
	
	/**
	 * Creates a tile with the specified row and column coordinates.
	 * 
	 * @param v the value of this tile
	 * @param row the row of this tile
	 * @param col the column of this tile
	 * @param g the Grid instance 
	 */
	protected Tile(int v, int row, int col, Grid g) {
		this.value = v;
		this.row = row;
		this.col = col;
		this.grid = g;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean setLocation(int row, int col) {
		return grid.setTile(row, col, this);
	}
}
