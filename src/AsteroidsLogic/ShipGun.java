package AsteroidsLogic;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import EntityManagement.Component;
import EntityManagement.Entity;
import EntityManagement.EntityManager;
import Physics.Body2D;
import Services.ServiceLocator;
import Visual.Pose2D;
import Visual.ShapeVisual;
import at.fhooe.mtd.sgl.Sgl;
import at.fhooe.mtd.sgl.input.InputListener;
import at.fhooe.mtd.sgl.math.Vector2d;

public class ShipGun extends Component implements InputListener {

	Pose2D pose;
	
	public ShipGun(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keyDown(int arg0, char arg1) {
		if(arg0 == KeyEvent.VK_SPACE) {
			shoot();
			return true;
		}
			
		return false;
	}
	
	private void shoot() {
		Entity bullet = new Entity();
		Body2D body = new Body2D(bullet, Body2D.getDirectionalForce(1000, pose.getAngle()).add(this.getEntity().getComponent(Body2D.class).getVelocity()));
		body.setPos(new Vector2d(pose.getPosX(), pose.getPosY()));
		body.setMass(1);
		bullet.addComponent(body);
		ShapeVisual bulletVisual = new ShapeVisual(bullet, new Rectangle(8,8), Color.white);
		bulletVisual.setOffset(-4, -4);
		bullet.addComponent(bulletVisual);
		BulletLogic bl = new BulletLogic(bullet, 4);
		bullet.addComponent(bl);
		bullet.addComponent(new DeleteEdgeBehaviour(bullet, 1000, 562.5));
		bullet.setName("bullet");
		ServiceLocator.getInstance().get(EntityManager.class).addEntity(bullet);
		
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseDown(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMove(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseUp(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(double arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		pose = this.getEntity().getComponent(Pose2D.class);
		
		Sgl.input.addInputListener(this);
	}

}
