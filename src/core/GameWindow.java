package core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;

public class GameWindow extends JFrame implements KeyListener{
	static final long serialVersionUID = 0l; 
	private GameSystem gs;
	private List<JPanel> tiles;
	private List<JLabel> tileValues;
	
	public GameWindow() {
		super("2048");
		
		gs = new GameSystem();
		
		setupGUI();
	}
	
	public void setupGUI() {
		int rows = gs.getGrid().getRows();
		int cols = gs.getGrid().getCols();

		setLayout(new GridLayout(rows, cols, 20, 20));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		tiles = new ArrayList<JPanel>(rows * cols);
		tileValues = new ArrayList<JLabel>(rows * cols);
		
		for (int i = 0; i < rows * cols; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setPreferredSize(new Dimension(100,100));
			
			JLabel text = new JLabel("", JLabel.CENTER);
			text.setFont(new Font(text.getFont().getName(), Font.PLAIN, 40));
			panel.add(text,BorderLayout.CENTER);
			
			tiles.add(panel);
			tileValues.add(text);
			
			add(panel);
		}
		updateTiles();
		pack();
		setVisible(true);
		addKeyListener(this);		
	}
	
	public void updateTiles() {
		for (int i = 0; i < tiles.size(); i++) {
			JPanel panel = tiles.get(i);
			
			Tile t = gs.getGrid().getTileByIndex(i);

			int value = t == null ? 0 : t.getValue();
					
			String text = value == 0 ? "" : String.valueOf(value);
			tileValues.get(i).setText(text);
			
			Color c = R.tileColors.get(value);
			
			if (c == null) {
				float r = R.rng.nextFloat();
				float g = R.rng.nextFloat();
				float b = R.rng.nextFloat();
				
				Color randomColor = new Color(r,g,b);
				R.tileColors.put(value, randomColor);
				
				c = randomColor;
			}
			
			panel.setBackground(c);
		}
	}
	
	public void keyPressed(KeyEvent e) { }
	
	public void keyReleased(KeyEvent e) {
		Direction d = R.keyBindings.get(e.getKeyCode());
		
		gs.makeMove(d);
		
		updateTiles();
	}
	
	public void keyTyped(KeyEvent e) { }
}
