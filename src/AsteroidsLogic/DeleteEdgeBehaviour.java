package AsteroidsLogic;

import EntityManagement.Component;
import EntityManagement.Entity;
import Physics.UpdateSystem;
import Physics.Updateable;
import Services.ServiceLocator;
import Visual.Pose2D;

public class DeleteEdgeBehaviour extends Component implements Updateable {
	double xSize;
	double ySize;
	Pose2D pose;
	
	public DeleteEdgeBehaviour(Entity entity, double xSize, double ySize) {
		super(entity);
		this.xSize = xSize;
		this.ySize = ySize;
	}

	@Override
	public void update(double dt) {
		if(pose.getPosX() > xSize || pose.getPosY() > ySize) {
			this.getEntity().deactivate();
		}
		if(pose.getPosX() < -xSize || pose.getPosY() < -ySize) {
			this.getEntity().deactivate();
		}
	}

	@Override
	public void activate() {
		pose = getEntity().getComponent(Pose2D.class);
		ServiceLocator.getInstance().get(UpdateSystem.class).addUpdateable(this);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		ServiceLocator.getInstance().get(UpdateSystem.class).removeUpdateable(this);
		super.activate();
	}
	
	

}
