package Visual;

import java.awt.Color;
import java.awt.Graphics2D;

public class Line2DVisual implements Visual {

	double x1,x2,y1,y2;
	int frames = 0;
	Color color = Color.CYAN;
	
	public Line2DVisual(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Line2DVisual setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public Line2DVisual setFrames(int frames) {
		this.frames = frames;
		return this;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

}
