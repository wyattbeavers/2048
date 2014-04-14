package core;

import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.io.FileReader;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class R {
	protected static Map<Integer,Color> tilesColors;
	protected static Map<String,String> strings;
	protected static Map<String,Integer> gameSettings;
	protected static Map<Integer, Direction> keyBindings;
	
	protected static Random rng;
	
	static {
		rng = new Random();
		loadStrings("res/strings");
		loadTileColors("conf/tiles.conf");
		loadGameSettings("conf/gameSettings.conf");
		loadKeyBindings();
	}
	
	private static void loadTileColors(String path) {
		try {
			FileReader f = new FileReader(path);
			Properties p = new Properties();
			
			p.load(f);
			
			tilesColors = new HashMap<Integer,Color>();
			
			for (String s: p.stringPropertyNames()) {
				int value = Integer.parseInt(s);
				int rgb = Integer.parseInt(p.getProperty(s), 16);

				tilesColors.put(value, new Color(rgb));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static void loadStrings(String path) {
		try {
			FileReader f = new FileReader(path);
			Properties p = new Properties();
			
			p.load(f);
			
			strings = new HashMap<String,String>();
			
			for (String s: p.stringPropertyNames()) {
				strings.put(s, p.getProperty(s));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}			
	}
	
	private static void loadGameSettings(String path) {
		try {
			FileReader f = new FileReader(path);
			Properties p = new Properties();
			
			p.load(f);
			
			gameSettings = new HashMap<String,Integer>();
			
			for (String s: p.stringPropertyNames()) {
				gameSettings.put(s, Integer.parseInt(p.getProperty(s)));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static void loadKeyBindings() {
		keyBindings = new HashMap<Integer, Direction>();
		keyBindings.put(KeyEvent.VK_UP, Direction.UP);
		keyBindings.put(KeyEvent.VK_DOWN, Direction.DOWN);		
		keyBindings.put(KeyEvent.VK_LEFT, Direction.LEFT);
		keyBindings.put(KeyEvent.VK_RIGHT, Direction.RIGHT);
	}
}
