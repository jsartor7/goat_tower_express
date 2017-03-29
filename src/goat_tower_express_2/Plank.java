package goat_tower_express_2;

import java.awt.image.BufferedImage;

public class Plank extends Entity {

	public Plank(BufferedImage inputimg, int x_i, int y_i, int x_vi, int y_vi, int x_max, int y_max) {
		super(inputimg, x_i, y_i, x_vi, y_vi, x_max, y_max);
		// TODO Auto-generated constructor stub

		collision_flag=2;
	    
	}

}
