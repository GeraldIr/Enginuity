package AsteroidsLogic;

import EntityManagement.Component;
import EntityManagement.Entity;
import Physics.Updateable;
import Services.KeyValueService;
import Services.ServiceLocator;
import Visual.TextVisual;

public class Scoreboard extends Component implements Updateable{

	public Scoreboard(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double dt) {
		this.getEntity().getComponent(TextVisual.class).setText("Score: " + ((KeyValueService<Integer>)ServiceLocator.getInstance().get(KeyValueService.class)).getValue("score"));
	}

}
