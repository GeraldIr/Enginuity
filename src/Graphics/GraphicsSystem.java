package Graphics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Services.Service;
import at.fhooe.mtd.sgl.Sgl;

public class GraphicsSystem implements Service {

	List<GraphicsLayer> layerList = new ArrayList<GraphicsLayer>();
	
	public void addLayer(GraphicsLayer newLayer) {
		layerList.add(newLayer);
		sort();
	}
	
	public void sort() {
		layerList.sort(new Comparator<GraphicsLayer>() {
			@Override
			public int compare(GraphicsLayer l1, GraphicsLayer l2) {
				return l1.getLayerOrdinal() - l2.getLayerOrdinal();
			}
		});
	}
	
	public void removeLayer(GraphicsLayer deleteLayer) {
		layerList.remove(deleteLayer);
	}
	
	
	@Override
	public void update(double dt) {
		Sgl.graphics.beginUpdate();
		for(GraphicsLayer l : layerList)
			l.render(Sgl.graphics.getGraphicsContext());
		Sgl.graphics.endUpdate();
		
	}


	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "This GraphicsSystem contains " + layerList.size() + " layers.";
		
	}

}
