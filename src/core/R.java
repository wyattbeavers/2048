package core;

import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

public class R {
	protected static HashMap<Integer,Tile> tiles;
	protected static Random rng;
	
	static {
		rng = new Random();
		//TODO: load defaults from file into tiles.
	}
}
