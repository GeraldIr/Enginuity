package Engine;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import EntityManagement.Entity;
import EntityManagement.EntityManager;
import Graphics.GraphicsSystem;
import Physics.Body2D;
import Physics.NewtonianSpring2D;
import Physics.PhysicsSystem;
import Physics.UpdateSystem;
import Services.ServiceLocator;
import Visual.ConnectingLine2D;
import Visual.ShapeVisual;
import Visual.Trail2D;
import Visual.VisualSystem;
import at.fhooe.mtd.sgl.app.ApplicationListener;
import at.fhooe.mtd.sgl.math.Vector2d;

public class SpringApp implements ApplicationListener {

	double SIZE = 5000;
	
	
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
		sl.get(PhysicsSystem.class).setGlobalGravity(new Vector2d(0,-40));
		
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
		earth.getComponent(Body2D.class).setMass(1).setPos(100, -300);
		earth.addComponent(new ShapeVisual(earth, new Rectangle(40,40), Color.BLUE));
		earth.addComponent(new Trail2D(earth));
		earth.getComponent(Trail2D.class).setMaxLines(20);
		earth.getComponent(ShapeVisual.class).setOffset(-20, -20);
		//earth.addComponent(new NewtonianGravity2D(earth));
		earth.setName("earth");
		
		
//		Entity earth2 = new Entity();
//		earth2.addComponent(new Body2D(earth2, new Vector2d(0, 0)));
//		earth2.getComponent(Body2D.class).setMass(1).setPos(0, -500);
//		earth2.addComponent(new ShapeVisual(earth2, new Rectangle(40,40), Color.BLUE));
//		earth2.addComponent(new Trail2D(earth2));
//		earth2.getComponent(Trail2D.class).setMaxLines(20);
//		earth2.getComponent(ShapeVisual.class).setOffset(-20, -20);
//		//earth2.addComponent(new NewtonianGravity2D(earth2));
//		earth2.setName("earth2");
//		
		
		
		Entity sun = new Entity();
		sun.addComponent(new Body2D(sun, new Vector2d(0,0)));
		sun.getComponent(Body2D.class).setMass(0).setPos(0, 0);
		sun.addComponent(new ShapeVisual(sun, new Rectangle(70,70), Color.YELLOW));
		sun.getComponent(ShapeVisual.class).setOffset(-35, -35);

		//sun.addComponent(new NewtonianGravity2D(sun));
		
		sun.setName("sun");
		
		
		Entity springy = new Entity();
		NewtonianSpring2D spring = (new NewtonianSpring2D(springy));
		spring.setObject1(earth.getComponent(Body2D.class));
		spring.setObject2(sun.getComponent(Body2D.class));
		spring.setSpringConstant(1);
		spring.setEquilibriumLength(spring.getSpringLength());
		springy.addComponent(spring);
		springy.addComponent(new ConnectingLine2D(springy, sun.getComponent(Body2D.class), earth.getComponent(Body2D.class)));

//		Entity springy2 = new Entity();
//		NewtonianSpring2D spring2 = (new NewtonianSpring2D(springy2));
//		spring2.setObject1(earth.getComponent(Body2D.class));
//		spring2.setObject2(earth2.getComponent(Body2D.class));
//		spring2.setSpringConstant(1);
//		spring2.setEquilibriumLength(spring2.getSpringLength());
//		springy2.addComponent(spring2);
//		springy2.addComponent(new ConnectingLine2D(springy2, earth.getComponent(Body2D.class), earth2.getComponent(Body2D.class)));
//		
		//start up everything
		sl.startup();
		
		sl.get(EntityManager.class).addEntity(earth);
		//sl.get(EntityManager.class).addEntity(earth2);
		sl.get(EntityManager.class).addEntity(sun);
		sl.get(EntityManager.class).addEntity(springy);
		//sl.get(EntityManager.class).addEntity(springy2);

		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		double scale = width / SIZE;
		
		AffineTransform tx = new AffineTransform();
		tx.setToIdentity();
		tx.translate(width / 2, height / 2);
		tx.scale(scale, -scale);
		
		sl.get(VisualSystem.class).setTransform(tx);
		
	}
	
	

}
