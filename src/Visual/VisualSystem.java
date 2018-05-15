package Visual;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import Graphics.AbstractGraphicsLayer;

public class VisualSystem extends AbstractGraphicsLayer {
	
	public VisualSystem(int layerOrdinal) {
		super(layerOrdinal);
	}
	
	private List<Visual> visualList = new ArrayList<Visual>();
	
	public void addVisual(Visual newVisual) {
		visualList.add(newVisual);
	}
	
	public void removeVisual(Visual oldVisual) {
		visualList.remove(oldVisual);
	}
	
	@Override
	public void render(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		for(Visual v : visualList) {
			v.render(g);
			g.setTransform(oldTransform);
		}
		
	}


}
