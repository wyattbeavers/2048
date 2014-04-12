package core;
import java.util.List;
import java.util.ArrayList;

public class Grid {
	private int rows;
	private int cols;
	
	private Tile[][] grid;
	
	/**
	 * Wrapper class for
	 * 
	 *
	 */
	protected class Location {
		private int x, y;
		
		Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	public Grid() {
		this(R.gameSettings.get("rows"),R.gameSettings.get("cols"));
	}
	
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}
	
	public void reset() {
		grid = new Tile[rows][cols];
		int startingTiles = R.gameSettings.get("starting_tiles");
		
		for (int tilesAdded = 0; tilesAdded < startingTiles; tilesAdded++) {
			addRandomTile();
		}
			
	}
	
	public boolean isEmpty(int row, int col) {
		return grid[row][col] == null;
	}
	
	public List<Location> getEmptyLocations() {
		List<Location> locs = new ArrayList<Location>();
		
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if (isEmpty(row, col)) {
					locs.add(new Location(row, col));
				}
			}
		}
		return locs;
	}
	
	/**
	 * 
	 * @return
	 */
	public Location getRandomEmptyLocation() {
		List<Location> locs = getEmptyLocations();
		
		if (locs.isEmpty()) {
			return null;
		}
		
		int randomIndex = R.rng.nextInt(locs.size());
		
		return locs.get(randomIndex);
	}
	
	public boolean addRandomTile() {
		Location loc = getRandomEmptyLocation();
		
		if (loc == null) {
			return false;
		}
		
		int value = 2 << R.rng.nextInt(2);
		grid[loc.getX()][loc.getY()] = Tile.getTileByValue(value);
		
		return true;
	}
	
	public boolean shift(Direction d) {
		boolean moved = false;
		
		
		
		return moved;
	}
}
