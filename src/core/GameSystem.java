package core;

import core.Grid.Location;

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

	public boolean shiftTile(Tile t, Direction d) {
		boolean moved = false;
		
		return moved;
	}
	
	public boolean addRandomTile() {
		Location loc = grid.getRandomEmptyLocation();
		
		if (loc == null) {
			return false;
		}
		
		int value = 2 << R.rng.nextInt(2);
		Tile t = new Tile(value, loc.getX(), loc.getY(), grid);
		grid.setTile(loc, t);
		
		return true;
	}
	
	public void makeMove() {
		
	}
	
	public boolean isGameOver() {
		return false;
	}
		
	public boolean isLegalCombination(Tile first, Tile second) {
		return first.getValue() == second.getValue();
	}
}
