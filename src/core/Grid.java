package core;
import java.util.List;
import java.util.ArrayList;

/**
 * A 2D matrix of Tiles.  The coordinate system used is (row, col).
 * For example, the Tiles in a 3x4 matrix would be referenced as:
 * 
 * (0,0)  (0,1)  (0,2)  (0,3)
 * (1,0)  (1,1)  (1,2)  (1,3)
 * (2,0)  (2,1)  (2,2)  (2,3)
 * 
 * @author Michael Nguyen
 */
public class Grid {
	private int rows;
	private int cols;
	
	private Tile[][] grid;
	
	/**
	 * Wrapper class for row, column pairings.
	 */
	protected class Location {
		private int row, col;
		
		Location(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
	}
	
	/**
	 * Creates a grid using the default game settings 
	 */
	public Grid() {
		this(R.gameSettings.get("rows"),R.gameSettings.get("cols"));
	}
	
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new Tile[rows][cols];
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public boolean isValidLocation(int row, int col) {
		return (row < this.rows && row >= 0) &&
				(col < this.cols && col >= 0);
	}
	
	public void reset() {
		grid = new Tile[rows][cols];
	}	
	
	public Tile getTile(int row, int col) {
		return grid[row][col];
	}
	
	public boolean setTile(Location loc, Tile t) {
		return setTile(loc.getRow(), loc.getCol(), t);
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
		t.setLocation(newRow, newCol);
		
		return true;
	}
	
	
	public boolean isEmpty(int row, int col) {
		return grid[row][col] == null;
	}
	
	/**
	 * Returns a List of all empty locations in the grid.
	 * 
	 * @return a List of empty locations
	 */
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
	 * Returns a random empty location in the grid.
	 * 
	 * @return a random empty location
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
