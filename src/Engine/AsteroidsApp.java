package Engine;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import AsteroidsLogic.AsteroidLogic;
import AsteroidsLogic.Scoreboard;
import AsteroidsLogic.ShipCollider;
import AsteroidsLogic.ShipController;
import AsteroidsLogic.ShipGun;
import AsteroidsLogic.TorusEdgeBehaviour;
import Collision.CollisionSystem2D;
import EntityManagement.Entity;
import EntityManagement.EntityManager;
import Graphics.GraphicsSystem;
import Physics.Body2D;
import Physics.PhysicsSystem;
import Physics.UpdateSystem;
import Services.KeyValueService;
import Services.ServiceLocator;
import Visual.ShapeVisual;
import Visual.TextVisual;
import Visual.VisualSystem;
import at.fhooe.mtd.sgl.app.ApplicationListener;
import at.fhooe.mtd.sgl.math.Vector2d;

public class AsteroidsApp implements ApplicationListener {

	double SIZE = 2000;
	
	
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
		sl.add(new CollisionSystem2D());
		sl.add(new KeyValueService<Integer>());
		sl.get(PhysicsSystem.class).setGlobalGravity(new Vector2d(0, 0));
		((KeyValueService<Integer>)sl.get(KeyValueService.class)).addValue("score", 0);
		
		
		Entity player = new Entity();
		ShipController sc = new ShipController(player, new Vector2d(0,0));
		sc.setPos(0,0).setAngle(0).setMass(1).setAngularDampening(0.999).setDampening(new Vector2d(0.999,0.999));
		player.addComponent(sc);
		player.getComponent(ShipController.class).addShipPolygonVisual();
		player.addComponent(new ShipGun(player));
		player.addComponent(new TorusEdgeBehaviour(player, 1000, 562.5));
		player.setName("player");
		player.addComponent(new ShipCollider(player, 15));
		
		Entity scoreboard = new Entity();
		scoreboard.addComponent(new Body2D(scoreboard, new Vector2d(0,0)));
		scoreboard.getComponent(Body2D.class).setMass(0).setPos(0, 400);
		scoreboard.addComponent(new TextVisual(scoreboard));
		scoreboard.addComponent(new Scoreboard(scoreboard));
		scoreboard.setName("scoreboard");
		
		
		//start up everything
		sl.startup();
		
		sl.get(EntityManager.class).addEntity(player);
		sl.get(EntityManager.class).addEntity(scoreboard);
		sl.get(EntityManager.class).addEntity(AsteroidLogic.createAsteroid(3, new Vector2d(300,400)));
		sl.get(EntityManager.class).addEntity(AsteroidLogic.createAsteroid(2, new Vector2d(600,800)));
		sl.get(EntityManager.class).addEntity(AsteroidLogic.createAsteroid(4, new Vector2d(-100,500)));
		sl.get(EntityManager.class).addEntity(AsteroidLogic.createAsteroid(2, new Vector2d(-600,-800)));
		sl.get(EntityManager.class).addEntity(AsteroidLogic.createAsteroid(2, new Vector2d(200, -400)));


		
		
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

