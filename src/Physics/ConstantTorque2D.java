package Physics;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;

public class ConstantTorque2D extends Component implements Updateable {

	private double constantTorque = 2;
	public ConstantTorque2D(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double dt) {
		this.getEntity().getComponent(Body.class).applyTorque(constantTorque);
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		ServiceLocator.getInstance().get(UpdateSystem.class).addUpdateable(this);
	}

	
}
