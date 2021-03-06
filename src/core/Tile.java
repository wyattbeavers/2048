package core;

public class Tile {
	private int value;
	private int row, col;
	private boolean merged;
	
	/**
	 * Creates a tile with the specified row and column coordinates.
	 * 
	 * @param v the value of this tile
	 * @param row the row of this tile
	 * @param col the column of this tile
	 * @param merged whether the tile was formed by merging with another tile.
	 */
	protected Tile(int v, int row, int col) {
		this.value = v;
		this.row = row;
		this.col = col;
		this.merged = false;
	}
	
	/**
	 * Returns the value of the tile.
	 * 
	 * @return the value of the tile
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns the row of the tile.
	 * 
	 * @return the row of the tile
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns the column of the tile.
	 * 
	 * @return the column of the tile
	 */
	public int getCol() {
		return col;
	}	
	
	/**
	 * Tests whether the tile was formed by merging two tiles.
	 * 
	 * @return true, if the tile was formed by merging two tiles.
	 */
	public boolean isMerged() {
		return this.merged;
	}
	
	/**
	 * Sets the value of the tile.
	 * 
	 * @param newValue the new value for the tile
	 */
	public void setValue(int newValue) {
		this.value = newValue;
	}
	
	/**
	 * Sets the row and column of the tile.  Does not
	 * update the Grid that contains this tile.  Use
	 * the Grid.setTile() to move a tile within a Grid.
	 * 
	 * @param newRow the new row for the tile
	 * @param newCol the new column for the tile
	 * @see Grid#setTile(int, int, Tile)
	 */
	
	public void setLocation(int newRow, int newCol) {
		this.row = newRow;
		this.col = newCol;
	}
	
	/**
	 * Sets the merge status of the tile.
	 * 
	 * @param merged the merge status.
	 */
	public void setMerged(boolean merged) {
		this.merged = merged;
	}
}
