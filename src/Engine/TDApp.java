package Engine;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import EntityManagement.Entity;
import EntityManagement.EntityManager;
import Graphics.GraphicsSystem;
import Physics.PhysicsSystem;
import Physics.UpdateSystem;
import Services.ServiceLocator;
import TopDown.PlayerMovementTD;
import Visual.ShapeVisual;
import Visual.VisualSystem;
import at.fhooe.mtd.sgl.app.ApplicationListener;
import at.fhooe.mtd.sgl.math.Vector2d;

public class TDApp implements ApplicationListener {

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
		
		Entity player = new Entity();
		player.addComponent(new ShapeVisual(player, new Rectangle(40,40), Color.BLUE));
		PlayerMovementTD playerMovementTD = new PlayerMovementTD(player);
		player.addComponent(playerMovementTD);
		
		//start up everything
		sl.startup();
		
		sl.get(EntityManager.class).addEntity(player);

		
		
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

