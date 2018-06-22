package Visual;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import Visual.Pose2D;
import Visual.Visual;
import Visual.VisualSystem;
import at.fhooe.mtd.sgl.math.Vector2d;

public class TextVisual extends Component implements Visual {

	String text = "";
	Pose2D pose;
	
	public TextVisual(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}
	
	public TextVisual setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public void render(Graphics2D g) {
		g.translate(pose.getPosX(), pose.getPosY());
		g.rotate(pose.getAngle());
		
		g.setColor(Color.WHITE);

		g.drawString(text, 0, 0);
		
	}
	
	@Override
	public void activate() {
		super.activate();
		System.out.println("hello");
		this.pose = getEntity().getComponent(Pose2D.class);
		ServiceLocator.getInstance().get(VisualSystem.class).addVisual(this);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		ServiceLocator.getInstance().get(VisualSystem.class).removeVisual(this);
	}

}
