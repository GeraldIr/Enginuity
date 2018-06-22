package AsteroidsLogic;

import java.awt.event.KeyEvent;

import EntityManagement.Entity;
import Physics.Body2D;
import at.fhooe.mtd.sgl.Sgl;
import at.fhooe.mtd.sgl.input.InputListener;
import at.fhooe.mtd.sgl.math.Vector2d;

public class ShipController extends Body2D implements InputListener  {

	public ShipController(Entity entity, Vector2d initialVelocity) {
		super(entity, initialVelocity);
		// TODO Auto-generated constructor stub
	}
	
	double movementSpeed = 300;
	double turnSpeed = 5;
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		super.update(dt);
		if(isForwardPressed) {
			this.applyForce(Body2D.getDirectionalForce(movementSpeed, this.getAngle()));
		} else {
			
		}
		if(isBackwardPressed) {
			this.applyForce(Body2D.getDirectionalForce(-movementSpeed, this.getAngle()));
		} else {
			
		}
		if(isLeftPressed) {
			this.applyTorque(turnSpeed);
		} else {
			
		}
		if(isRightPressed) {
			this.applyTorque(-turnSpeed);
		} else {
			
		}
		
		
	}



	boolean isForwardPressed;
	boolean isBackwardPressed;
	boolean isLeftPressed;
	boolean isRightPressed;

	@Override
	public boolean keyDown(int arg0, char arg1) {
		if(arg0 == KeyEvent.VK_W) {
			isForwardPressed = true;
			return true;
		}
		if(arg0 == KeyEvent.VK_S) {
			isBackwardPressed = true;
			return true;
		}
		if(arg0 == KeyEvent.VK_A) {
			isLeftPressed = true;
			return true;
		}
		if(arg0 == KeyEvent.VK_D) {
			isRightPressed = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		if(arg0 == KeyEvent.VK_W) {
			isForwardPressed = false;
			return true;
		}
		if(arg0 == KeyEvent.VK_S) {
			isBackwardPressed = false;
			return true;
		}
		if(arg0 == KeyEvent.VK_A) {
			isLeftPressed = false;
			return true;
		}
		if(arg0 == KeyEvent.VK_D) {
			isRightPressed = false;
			return true;
		}
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
	
	public void addShipPolygonVisual() {
		if(!this.getEntity().hasComponent(PolygonVisual.class)) {
			PolygonVisual pg = new PolygonVisual(this.getEntity());
			pg.addPoint(new Vector2d(20, 0));
			pg.addPoint(new Vector2d(-14, -10));
			pg.addPoint(new Vector2d(-9, 0));	
			pg.addPoint(new Vector2d(-14, 10));
			this.getEntity().addComponent(pg);
		}
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		
		Sgl.input.addInputListener(this);
	}

}
