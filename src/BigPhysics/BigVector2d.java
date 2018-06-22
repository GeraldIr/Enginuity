package BigPhysics;

import java.math.BigDecimal;

public class BigVector2d {
	BigDecimal x;
	BigDecimal y;
	
	
	public BigVector2d(BigDecimal x, BigDecimal y) {
		this.x = x;
		this.y = y;
	}
	
	public BigVector2d(String x, String y) {
		this.x = new BigDecimal(x);
		this.y = new BigDecimal(y);
	}


	public BigVector2d() {
	}
	
	public BigVector2d mulAdd(BigVector2d f, double d) {
		return f;
		
	}

	public void scale(int i) {
		// TODO Auto-generated method stub
		
	}
	
	
}
