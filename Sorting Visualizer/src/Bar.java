import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Bar {
	private Graphics g;
	private int length;
	private final int width;
	private int x = 0;
	private int y;
	private final int space;
	private BufferStrategy bs;
	private boolean sorted;
	
	public static final Color PURPLE = new Color(226, 60, 180);
	public static final Color GREEN = new Color(60, 226, 60);
	public static final Color RED = new Color(226, 60, 60);
	public static final Color BLUE = new Color(60, 120, 226);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color YELLOW = new Color(226, 226, 60);
	
	Bar(Graphics g, int scale, int total, int len, int distFromTop, BufferStrategy bs) {
		this.g = g;
		this.length = scale * len;
		this.space = setSpacing(total);
		this.width = (750 - (space * total - space)) / total;
		this.y = distFromTop * width + distFromTop * space;
		this.bs = bs;
		drawBar();
	}
	//Public methods
	//Mainly used to manipulate bars
	//All methods use the LOWER bar as parameter, and UPPER bar as this
	public boolean isGreaterThan(Bar lower) {
		this.drawBar(RED);
		lower.drawBar(RED);
		//bs.show();
		if (this.length > lower.length) {
			return true;
		} else {
			return false;
		}
	}
	public void swap(Bar lower) {
		this.switchBars(lower);
		this.confirmSwitch(lower);
		//delay(SortingVisualizer.updateCap);
	}
	private void delay(int fps) {
		double firstTime = 0.0;
		double nextTime = System.nanoTime() / 1000000000.0;
		double skippedTime = 0.0;
		double elapsedTime = 0.0;
		boolean running = true;
		while(running) {
			firstTime = System.nanoTime() / 1000000000.0;
			elapsedTime = firstTime - nextTime;
			nextTime = firstTime;
			skippedTime += elapsedTime;
			if (skippedTime >= 1.0 / (double) fps) {
				running = false;
			}
		}
		
	}
	public String toString() {
		return "" + this.length;
	}
	public void switchBars(Bar lower) {
		delay(SortingVisualizer.updateCap);
		this.eraseBar();
		lower.eraseBar();
		int thisX = this.x;
		int thisY = this.y;
		this.setLoc(lower.getX(), lower.getY());
		lower.setLoc(thisX, thisY);
		this.drawBar(RED);
		lower.drawBar(RED);
		//bs.show();
	}
	public void confirmSwitch(Bar lower) {
		delay(SortingVisualizer.updateCap);
		this.drawBar(BLUE);
		lower.drawBar(BLUE);
		//bs.show();
	}
	public void resetColor() {
		delay(SortingVisualizer.updateCap);
		this.drawBar(BLUE);
	}
	public void confirmSorted() {
		delay(SortingVisualizer.updateCap);
		this.drawBar(GREEN);
		this.sorted = true;
		//bs.show();
	}
	public boolean isSorted() {
		return this.sorted;
	}
	public void eraseBar() {
		drawBar(WHITE);
	}
	public void showBar() {
		drawBar(BLUE);
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getLength() {
		return this.length;
	}
	//Private methods
	//Mainly used to draw and animate bars
	private int setSpacing(int total) {
		if (total < 10) {
			return 10;
		} else if (total < 50) {
			return 7;
		} else if (total < 100) {
			return 5;
		} else
			return 3;
	}
	private void drawBar() {
		g.setColor(BLUE);
		g.fillRect(x, y, length + 1, width + 1);
		bs.show();
	}
	private void drawBar(Color c) {
		g.setColor(c);
		g.fillRect(x, y, length + 1, width + 1);
		bs.show();
	}
	private void setLoc(int x, int y) {
		this.x = x;
		this.y = y;
	}
}