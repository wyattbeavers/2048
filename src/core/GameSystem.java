package core;

import core.Grid.Location;
import java.util.List;

public class GameSystem {
	private Grid grid;
	private int score;
	
	public GameSystem() {
		this.grid = new Grid();
		this.score = 0;
		newGame();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public int getScore() {
		return score;
	}
	
	public void newGame() {
		score = 0;
		int startingTiles = R.gameSettings.get("starting_tiles");
		int tiles = 0;
		
		grid.reset();
		
		while (tiles < startingTiles) {
			addRandomTile();
			tiles++;
		}
	}
	
	public void printGrid() {
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				Tile t = grid.getTile(i, j);
				
				System.out.print(t == null ? 0 : t.getValue());
			}
			System.out.println();
		}
	}
	
	public void resetTileMergeStatus() {
		List<Tile> tiles = grid.getNonEmptyTiles();
		
		for (Tile t : tiles) {
			t.setMerged(false);
		}
	}
	
	public boolean shiftTiles(Direction d) {
		boolean moved = false;
		
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
			Tile merged = new Tile(t.getValue() + grid.getTile(nextRow,nextCol).getValue(), nextRow, nextCol);
			merged.setMerged(true);

			grid.removeTile(t);
			grid.removeTile(grid.getTile(nextRow,nextCol));
			grid.setTile(nextRow, nextCol, merged);
			
			score += merged.getValue();
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
	
	/**
	 * Attempts to shift and merge tiles based on the specified Direction.
	 * If at least one Tile changes location or merges, a random Tile is added
	 * to the grid.
	 * 
	 * @param d the Direction
	 */
	public void makeMove(Direction d) {
		resetTileMergeStatus();
		
		boolean moved = shiftTiles(d);
		
		if (moved) {
			addRandomTile();
		}
	}
	
	//TODO
	public boolean isGameOver() {
		return grid.getEmptyLocations().isEmpty();
	}
	
	/**
	 * Tests if merging the specified Tiles is valid.  For a merge
	 * to be valid, both Tiles must have the same value, and neither 
	 * of the tiles can be the result of another merge.
	 * 
	 * @param first the first tile
	 * @param second the second tile
	 * @return true, if the tiles may be merged
	 */
	public boolean isValidMerge(Tile first, Tile second) {
		return first.getValue() == second.getValue() &&
				!first.isMerged() && !second.isMerged();
	}
}
