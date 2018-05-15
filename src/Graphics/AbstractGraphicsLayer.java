package Graphics;

import java.awt.Graphics2D;

import Services.*;

public abstract class AbstractGraphicsLayer implements Service, GraphicsLayer {

	private int layerOrdinal;
	
	public AbstractGraphicsLayer(int ordinal) {
		layerOrdinal = ordinal;
	}
	
	@Override
	public int getLayerOrdinal() {
		return layerOrdinal;
	}

	@Override
	public void setTransform() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startup() {
		ServiceLocator.getInstance().get(GraphicsSystem.class).addLayer(this);
		
	}

	@Override
	public void shutdown() {
		ServiceLocator.getInstance().get(GraphicsSystem.class).removeLayer(this);		
	}
	
	public String toString() {
		return "Layer Ordinal: " + layerOrdinal;
	}

}
