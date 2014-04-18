package core;

import core.Grid.Location;
import java.util.List;

public class GameSystem {
	private Grid grid;
	private int rows, cols;
	
	public GameSystem() {
		this(R.gameSettings.get("rows"),R.gameSettings.get("cols"));
	}
	
	public GameSystem(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
		this.grid = new Grid(rows, cols);
		newGame();
	}
	
	public void newGame() {
		int startingTiles = R.gameSettings.get("starting_tiles"); 
		int tiles = 0;
		
		grid.reset();
		
		while (tiles < startingTiles) {
			addRandomTile();
			tiles++;
		}
	}
	
	public void printGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Tile t = grid.getTile(i, j);
				
				System.out.print(t == null ? 0 : t.getValue());
			}
			System.out.println();
		}
	}
	
	//TODO
	public void resetTileMergeStatus() {
		
	}
	
	public boolean shiftTiles(Direction d) {
		boolean moved = false;
		
		resetTileMergeStatus();
		
		List<Location> locs = grid.getLocationsInTraverseOrder(d);
		
		for (Location loc : locs) {
			Tile t = grid.getTile(loc);
			
			if (t != null) {
				moved |= shiftTile(t,d);
			}
		}
		
		return moved;
	}
	
	public boolean shiftTile(Tile t, Direction d) {
		boolean moved = false;
		
		int prevRow = t.getRow();
		int prevCol = t.getCol();
		
		int nextRow = prevRow + d.getX();
		int nextCol = prevCol + d.getY();
				
		while (grid.isValidLocation(nextRow, nextCol) && 
				grid.isEmpty(nextRow, nextCol)) {
			moved = true;
			grid.setTile(nextRow, nextCol, t);

			prevRow = nextRow;
			prevCol = nextCol;
				
			nextRow += d.getX();
			nextCol += d.getY();
		}
		if (grid.isValidLocation(nextRow, nextCol) && 
				isValidMerge(t, grid.getTile(nextRow, nextCol))){
			moved = true;
			Tile merged = new Tile(t.getValue() +grid.getTile(nextRow,nextCol).getValue(), nextRow, nextCol,true);

			grid.removeTile(t);
			grid.removeTile(grid.getTile(nextRow,nextCol));
			grid.setTile(nextRow, nextCol, merged);
		}
		
		return moved;
	}
	
	/**
	 * Adds a tile to a random empty location.  The tile has a value of 2
	 * with probability 90%, or has a value of 4 with probability 10%.
	 * 
	 * @return true if a Tile was added, false if the grid was full.
	 */
	public boolean addRandomTile() {
		Location loc = grid.getRandomEmptyLocation();
		
		if (loc == null) {
			return false;
		}
		
		int value = (R.rng.nextFloat() < 0.9 ? 2 : 4);
		Tile t = new Tile(value, loc.getRow(), loc.getCol());
		grid.setTile(loc, t);
		
		return true;
	}
	
	public boolean isGameOver() {
		return false;
	}
		
	public boolean isValidMerge(Tile first, Tile second) {
		return first.getValue() == second.getValue() &&
				!first.isMerged() && !second.isMerged();
	}
}
