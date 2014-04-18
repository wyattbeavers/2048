package core;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
		
		public String toString() {
			return "(" + this.row + "," + this.col +")";
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
	
	public Tile getTile(Location loc) {
		return getTile(loc.getRow(), loc.getCol());
	}

	public boolean setTile(Location loc, Tile t) {
		return setTile(loc.getRow(), loc.getCol(), t);
	}
	
	/**
	 * Moves a tile to the specified row and column in the Grid.
	 * The Tile's attributes are also updated.
	 * 
	 * @param newRow the row
	 * @param newCol the column
	 * @param t the tile
	 * @return false if the specified location is occupied, true otherwise
	 */
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
	
	public void removeTile(Tile t) {
		grid[t.getRow()][t.getCol()] = null;
	}
	
	/**
	 * Tests if the specified row and column is empty.
	 * 
	 * @param row the row
	 * @param col the column
	 * @return true if the specified location is empty
	 */
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
	
	/**
	 * Returns a List of locations in the order they should be traversed
	 * when shifting tiles.  If the direction is UP or LEFT, the list 
	 * begins at (0,0), traverses left-to-right, up-to-down, and terminates 
	 * at (numRows, numCols).  If the direction is DOWN or RIGHT, the list
	 * is reversed.
	 *  
	 * @param d the direction
	 * @return a List of locations based on the direction
	 */
	public List<Location> getLocationsInTraverseOrder(Direction d) {
		List<Location> locs = new ArrayList<Location>();
		
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				locs.add(new Location(row, col));
			}
		}
		
		if (d == Direction.RIGHT || d == Direction.DOWN) {
			Collections.reverse(locs);
		}
		
		return locs;
	}
	
	//TODO
	public Location getFarthestEmptyLocation(Tile t, Direction d) {
		return null;
	}
}
