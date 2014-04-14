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
		reset();
	}
	
	public void reset() {
		grid = new Tile[rows][cols];
	}	
	
	public Tile getTile(int row, int col) {
		return grid[row][col];
	}
	
	public boolean setTile(Location loc, Tile t) {
		return setTile(loc.getX(), loc.getY(), t);
	}
	
	public boolean setTile(int newRow, int newCol, Tile t) {
		// cannot move to an occupied Tile		
		if (!isEmpty(newRow, newCol)) {
			return false;
		}
		
		int oldRow = t.getRow();
		int oldCol = t.getCol();
		
		grid[oldRow][oldCol] = null;
		grid[newRow][newCol] = t;
		
		return true;
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
}
