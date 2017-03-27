package goat_tower_express_2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DrawFrame extends Component {

    public BufferedImage goatimg;
    public BufferedImage grassimg;
    static public BufferedImage dirtimg;
  //  static BufferedImage bi = new BufferedImage(2000,2000, BufferedImage.TYPE_INT_ARGB);
	FileUtil fu=new FileUtil();
	static Goat[] goats;
	static Graphics g;
    Graphics2D g2d;


    
 //   float[] scales = { 1f, 1f, 1f, 1f };
 //   float[] offsets = new float[4];
    RescaleOp dirtrop;
    		
    public DrawFrame(Goat[] in_goats) {
    	dirtimg=fu.LoadImg("dirt2.jpg");	
    	g=dirtimg.createGraphics();
    	goatimg=fu.LoadImg("goat.png");      	
    	grassimg=fu.LoadImg("grass.png");      	
    	goats=in_goats;
    }

    public Dimension getPreferredSize() {
        return new Dimension	(dirtimg.getWidth(null), dirtimg.getHeight(null));
    }

    
    public void setOpacity(float opacity) {
    	//don't really need this function at all
  //      scales[3] = opacity;
    //    rop = new RescaleOp(scales, offsets, null);
    }

    public void setColorScale(float opacity) {
    	//don't really need this function at all
  //      scales[3] = opacity;
    //    rop = new RescaleOp(scales, offsets, null);
    }
    public void paint(Graphics g) {
        g2d = (Graphics2D)g;
        
        //draw backgroun first
        g2d.drawImage(dirtimg, dirtrop, 0, 0);
        
        //next the counter
        g2d.setColor(Color.WHITE);
        int style = Font.BOLD | Font.ITALIC;
        Font font = new Font ("Helvetica", style , 40);
        g2d.setFont(font);
        g2d.drawString("You have eaten "+goats[0].kill_count+" grass", 100, 100);
   
        //next all the important stuff
        for(int i=0;i<goats.length;i++)
        {
        	Goat cur=goats[i];
        	g2d.drawImage(cur.img, cur.rop, cur.get_pos(1,0), cur.get_pos(1,1));
        }
        
    }
}
