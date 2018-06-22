package Visual;

import EntityManagement.Component;
import EntityManagement.Entity;
import Physics.UpdateSystem;
import Physics.Updateable;
import Services.ServiceLocator;

public class ConnectingLine2D extends Component implements Updateable {

	Line2DVisual line;
	Pose2D pos1;
	Pose2D pos2;
	
	public ConnectingLine2D(Entity entity, Pose2D pos1, Pose2D pos2) {
		super(entity);
		this.pos1 = pos1;
		this.pos2 = pos2;
		line = new Line2DVisual(pos1.getPosX(), pos1.getPosY(), pos2.getPosX(), pos2.getPosY());
		ServiceLocator.getInstance().get(VisualSystem.class).addVisual(line);
	}

	@Override
	public void update(double dt) {
		line.x1 = pos1.getPosX();
		line.x2 = pos2.getPosX();
		line.y1 = pos1.getPosY();
		line.y2 = pos2.getPosY();
		
	}

	@Override
	public void activate() {
		super.activate();
		System.out.println("Engage: connectingLine");
		ServiceLocator.getInstance().get(UpdateSystem.class).addUpdateable(this);
	}
	
	

}
