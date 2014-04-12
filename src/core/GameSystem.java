package core;

public class GameSystem {
	private Tile[][] grid;
	private int rows, cols;
	
	public GameSystem() {
		this(R.gameSettings.get("rows"),R.gameSettings.get("cols"));
	}
	
	public GameSystem(int rows, int cols) {
		new R();
		
		this.rows = rows;
		this.cols = cols;
		
		this.grid = new Tile[rows][cols];
		newGame();
	}
	
	public void newGame() {
		int startingTiles = R.gameSettings.get("starting_tiles"); 
		int tiles = 0;
		
		while (tiles < startingTiles) {
			int x = R.rng.nextInt(rows);
			int y = R.rng.nextInt(cols);
			
			int value = 2 << R.rng.nextInt(2);

			grid[x][y] = Tile.getTileByValue(value);
			
			tiles++;
		}
	}
	
	public void printGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Tile t = grid[i][j];
				
				System.out.print(t == null ? 0 : t.getValue());
			}
			System.out.println();
		}
	}
	
	public void addRandomTile() {
		
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
