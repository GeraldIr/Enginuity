package AsteroidsLogic;

import EntityManagement.Component;
import EntityManagement.Entity;
import Physics.UpdateSystem;
import Physics.Updateable;
import Services.ServiceLocator;
import Visual.Pose2D;

public class TorusEdgeBehaviour extends Component implements Updateable {
	double xSize;
	double ySize;
	Pose2D pose;
	
	public TorusEdgeBehaviour(Entity entity, double xSize, double ySize) {
		super(entity);
		this.xSize = xSize;
		this.ySize = ySize;
	}

	@Override
	public void update(double dt) {
		if(pose.getPosX() > xSize) {
			pose.setPos(-xSize, pose.getPosY());
		}
		if(pose.getPosY() > ySize) {
			pose.setPos(pose.getPosX(), -ySize);
		}
		if(pose.getPosX() < -xSize) {
			pose.setPos(xSize, pose.getPosY());
		}
		if(pose.getPosY() < -ySize) {
			pose.setPos(pose.getPosX(), ySize);
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
