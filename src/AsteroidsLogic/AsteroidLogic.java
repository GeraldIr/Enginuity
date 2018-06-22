package AsteroidsLogic;

import java.awt.Color;
import java.awt.Rectangle;

import Collision.Collider2D;
import Collision.CollisionLogic;
import EntityManagement.Entity;
import EntityManagement.EntityManager;
import Physics.Body2D;
import Services.ServiceLocator;
import Visual.ShapeVisual;
import at.fhooe.mtd.sgl.math.Vector2d;

public class AsteroidLogic extends Collider2D implements CollisionLogic  {

	private int asteroidSize;
	
	public AsteroidLogic(Entity entity, double radius, int size) {
		super(entity, radius);
		
		asteroidSize = size;
	}

	@Override
	public void resolveCollision(Collider2D other) {
		
		
		if(other.getEntity().name == "bullet") {
			if(asteroidSize > 1) {
				ServiceLocator.getInstance().get(EntityManager.class).addEntity(createAsteroid(asteroidSize-1, this.getEntity().getComponent(Body2D.class).getPos()));
				ServiceLocator.getInstance().get(EntityManager.class).addEntity(createAsteroid(asteroidSize-1, this.getEntity().getComponent(Body2D.class).getPos()));
			}
			this.getEntity().deactivate();
		}
	}
	
	public static Entity createAsteroid(int size, Vector2d pos) {
		Entity asteroid = new Entity();
		double speedX = (Math.random() * 200) - 100;
		double speedY = (Math.random() * 200) - 100;
		AsteroidBody asteroidBody = new AsteroidBody(asteroid, new Vector2d(speedX,speedY));
		asteroidBody.setPos(new Vector2d(pos.x, pos.y)).setMass(size);
		asteroid.addComponent(asteroidBody);
		asteroid.addComponent(new AsteroidLogic(asteroid, 30*size, size));
		asteroid.getComponent(AsteroidLogic.class).addPolygonVisual();
		asteroid.addComponent(new TorusEdgeBehaviour(asteroid, 1000, 562.5));
		asteroid.setName("asteroid");
		return asteroid;
	}
	
	public void addPolygonVisual() {
		if(!this.getEntity().hasComponent(PolygonVisual.class)) {
			PolygonVisual pg = new PolygonVisual(this.getEntity());
			pg.addPoint(new Vector2d(Math.sin(0) * radius * (1 + (Math.random()/2) - 0.25), 
					Math.cos(0) * radius * (1 + (Math.random()/2) - 0.25)));
			pg.addPoint(new Vector2d(Math.sin(2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25), 
					Math.cos(2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25)));
			pg.addPoint(new Vector2d(Math.sin(2*2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25), 
					Math.cos(2*2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25)));	
			pg.addPoint(new Vector2d(Math.sin(3*2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25), 
					Math.cos(3*2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25)));
			pg.addPoint(new Vector2d(Math.sin(4*2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25), 
					Math.cos(4*2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25)));
			pg.addPoint(new Vector2d(Math.sin(5*2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25), 
					Math.cos(5*2*Math.PI / 6) * radius * (1 + (Math.random()/2) - 0.25)));
			this.getEntity().addComponent(pg);
		}
	}
}
