package Engine;

import java.util.Scanner;

import at.fhooe.mtd.sgl.app.ApplicationListener;
import at.fhooe.mtd.sgl.app.Java2dApplication;
import at.fhooe.mtd.sgl.app.Java2dApplicationConfig;
import at.fhooe.mtd.sgl.graphics.GfxConfigurator;

public class Launcher {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		ApplicationListener game;
		switch(reader.next()) {
		case "p":
			game = new TestApp();
			break;
		case "s":
			game = new SpringApp();
			break;
		case "t":
			game = new TDApp();
			break;
		case "a":
			game = new AsteroidsApp();
			break;
		default:
			game = new SpringApp();
			break;
		}
		reader.close();
		new Java2dApplication(getConfig(), game);		
	}
	
	public static Java2dApplicationConfig getConfig() {
		GfxConfigurator gfxc = new GfxConfigurator();
		gfxc.runDialog();
		
		return Java2dApplicationConfig.create(gfxc);
	}
}
