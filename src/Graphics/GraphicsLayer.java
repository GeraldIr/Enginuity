package Graphics;

import java.awt.Graphics2D;

public interface GraphicsLayer {
	int getLayerOrdinal();
	void setTransform();
	void render(Graphics2D g);
}
