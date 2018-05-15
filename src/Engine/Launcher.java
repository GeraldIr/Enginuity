package Engine;

import at.fhooe.mtd.sgl.Sgl;

import at.fhooe.mtd.sgl.app.Java2dApplication;
import at.fhooe.mtd.sgl.app.Java2dApplicationConfig;
import at.fhooe.mtd.sgl.graphics.GfxConfigurator;

public class Launcher {
	public static void main(String[] args) {
		
		TestApp game = new TestApp();
		new Java2dApplication(getConfig(), game);		
	}
	
	public static Java2dApplicationConfig getConfig() {
		GfxConfigurator gfxc = new GfxConfigurator();
		gfxc.runDialog();
		
		return Java2dApplicationConfig.create(gfxc);
	}
}
