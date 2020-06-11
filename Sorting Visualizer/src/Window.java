import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Window {
	
	private JFrame window;
	private Canvas canvas;
	private Graphics g;
	private BufferedImage image;
	private BufferStrategy bs;
	
	public static final Color white = new Color(255, 255, 255);
	public final int WIDTH;
	public final int HEIGHT;
	
	Window(int w, int h) {
		WIDTH = w;
		HEIGHT = h;
		this.setWindow();
	}
	private void setWindow() {
		canvas = this.setImage();
		window = new JFrame();
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(canvas, BorderLayout.CENTER);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g.setColor(white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	private Canvas setImage() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension newDimension = new Dimension(WIDTH, HEIGHT);
		canvas.setPreferredSize(newDimension);
		return canvas;
	}
	public Graphics getGraphics() {
		return this.g;
	}
	public BufferStrategy getBuffer() {
		return this.bs;
	}
	public BufferedImage getImage() {
		return this.image;
	}
}