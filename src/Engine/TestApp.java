package Engine;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Shape;

import EntityManagement.Entity;
import EntityManagement.EntityManager;
import Graphics.GraphicsSystem;
import Physics.UpdateSystem;
import Physics.Body2D;
import Physics.PhysicsSystem;
import Physics.SimpleMovement;
import Services.ServiceLocator;
import Visual.ShapeVisual;
import Visual.Static2DPose;
import Visual.VisualSystem;
import at.fhooe.mtd.sgl.app.ApplicationListener;
import at.fhooe.mtd.sgl.math.Vector2d;

public class TestApp implements ApplicationListener {

	
	ServiceLocator sl = ServiceLocator.getInstance();

	
	@Override
	public void update(double dt) {
		sl.updateAll(dt);
		
	}

	@Override
	public void create() {
		
		System.out.println("Engage.");
		sl.add(new GraphicsSystem());
		sl.add(new VisualSystem(0));
		sl.add(new EntityManager());
		sl.add(new UpdateSystem());
		sl.add(new PhysicsSystem());
		
		//Setup an entity that is a blue rectangle at the position 300,300
		//which also moves
		Entity shape = new Entity();
		shape.addComponent(new Body2D(shape, new Vector2d(0,0)));
		shape.getComponent(Body2D.class).setHasGravity(true).setMass(1000000000).setPos(300, 300);
		shape.addComponent(new ShapeVisual(shape, new Rectangle(20,20), Color.BLUE));
		sl.get(EntityManager.class).addEntity(shape);
		
		Entity moon = new Entity();
		moon.addComponent(new Body2D(moon, new Vector2d(30,0)));
		moon.getComponent(Body2D.class).setHasGravity(false).setMass(1).setPos(300, 400);
		moon.addComponent(new ShapeVisual(moon, new Rectangle(20,20), Color.RED));
		
		sl.get(EntityManager.class).addEntity(moon);
		
		//start up everything
		sl.startup();
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
