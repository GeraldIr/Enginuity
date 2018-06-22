package Graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public interface GraphicsLayer {
	int getLayerOrdinal();
	void setTransform(AffineTransform tx);
	void render(Graphics2D g);
}
