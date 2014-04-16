package core;

public enum Direction {
	UP (-1, 0),
	DOWN (1, 0),
	LEFT (0, -1),
	RIGHT (0, 1);
	
	private int x, y;
	
	Direction(int x, int y) {
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