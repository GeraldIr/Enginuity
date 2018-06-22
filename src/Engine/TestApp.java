package Engine;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import Collision.Collider2D;
import Collision.CollisionSystem2D;
import EntityManagement.Entity;
import EntityManagement.EntityManager;
import Graphics.GraphicsSystem;
import Physics.Body2D;
import Physics.ConstantTorque2D;
import Physics.NewtonianGravity2D;
import Physics.PhysicsSystem;
import Physics.UpdateSystem;
import Services.ServiceLocator;
import Visual.ConnectingLine2D;
import Visual.ShapeVisual;
import Visual.Trail2D;
import Visual.VisualSystem;
import at.fhooe.mtd.sgl.app.ApplicationListener;
import at.fhooe.mtd.sgl.math.Vector2d;

public class TestApp implements ApplicationListener {

	double SIZE = 5000;
	
	
	ServiceLocator sl = ServiceLocator.getInstance();

	
	@Override
	public void update(double dt) {
		sl.updateAll(dt);
		
	}

	@Override
	public void create() {
		
		sl.add(new GraphicsSystem());
		sl.add(new VisualSystem(0));
		sl.add(new EntityManager());
		sl.add(new UpdateSystem());
		sl.add(new PhysicsSystem());
		sl.add(new CollisionSystem2D());
		
		//Setup an entity that is a blue rectangle at the position 300,300
		//which also moves
		
//		Entity moon = new Entity();
//		moon.addComponent(new Body2D(moon, new Vector2d(-300,0)));
//		moon.getComponent(Body2D.class).setMass(100).setPos(0, 100);
//		moon.addComponent(new ShapeVisual(moon, new Rectangle(20,20), Color.RED));
//		moon.addComponent(new NewtonianGravity2D(moon));
//		moon.setName("moon");
		
		
		Entity earth = new Entity();
		
		earth.addComponent(new Body2D(earth, new Vector2d(0, 0)));
		earth.getComponent(Body2D.class).setMass(10000).setPos(0, 300);
		earth.addComponent(new ShapeVisual(earth, new Rectangle(40,40), Color.BLUE));
		earth.addComponent(new Trail2D(earth));
		earth.getComponent(Trail2D.class).setMaxLines(20);
		earth.getComponent(ShapeVisual.class).setOffset(-20, -20);
		earth.addComponent(new NewtonianGravity2D(earth));
		earth.addComponent(new Collider2D(earth, 20));
		earth.setName("earth");
		
		
		
		Entity sun = new Entity();
		sun.addComponent(new Body2D(sun, new Vector2d(0,0)));
		sun.getComponent(Body2D.class).setMass(100000000).setPos(0, 0);
		sun.addComponent(new ShapeVisual(sun, new Rectangle(70,70), Color.YELLOW));
		sun.getComponent(ShapeVisual.class).setOffset(-35, -35);
		sun.addComponent(new ConnectingLine2D(sun, sun.getComponent(Body2D.class), earth.getComponent(Body2D.class)));
		sun.addComponent(new Collider2D(sun, 35));
		sun.addComponent(new ConstantTorque2D(sun));
		sun.addComponent(new NewtonianGravity2D(sun));
		sun.setName("sun");
		
		//start up everything
		sl.startup();
		
		sl.get(EntityManager.class).addEntity(earth);
		//sl.get(EntityManager.class).addEntity(moon);
		sl.get(EntityManager.class).addEntity(sun);

		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		double scale = width / SIZE;
		
		AffineTransform tx = new AffineTransform();
		tx.translate(width / 2, height / 2);
		tx.scale(scale, -scale);
		
		sl.get(VisualSystem.class).setTransform(tx);
		
	}
	
	

}
