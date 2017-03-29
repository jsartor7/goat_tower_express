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

    static public BufferedImage dirtimg;
  //  static BufferedImage bi = new BufferedImage(2000,2000, BufferedImage.TYPE_INT_ARGB);
	FileUtil fu=new FileUtil();
	EntityList master_list;
	static Graphics g;
    Graphics2D g2d;
    RescaleOp dirtrop;
    		
    public DrawFrame() {
    	dirtimg=fu.LoadImg("dirt.jpg");	
    	g=dirtimg.createGraphics();   	
    }

    public Dimension getPreferredSize() {
        return new Dimension	(dirtimg.getWidth(null), dirtimg.getHeight(null));
    }
    
    public void paint(Graphics g) {
        g2d = (Graphics2D)g;
        //draw background first
        g2d.drawImage(dirtimg, dirtrop, 0, 0);
        
        //next the counter
        g2d.setColor(Color.WHITE);
        int style = Font.BOLD | Font.ITALIC;
        Font font = new Font ("Helvetica", style , 40);
        g2d.setFont(font);
        g2d.drawString("You have eaten "+master_list.goat.kill_count+" grass", 100, 100);
   
        //next all the important stuff
        Entity[] list =master_list.get_list();
    	Entity cur;
        for(int i=0;i<list.length;i++)
        {
        	cur=list[i];
        	g2d.drawImage(cur.img, cur.rop, cur.get_pos(1,0), cur.get_pos(1,1));
        }
        
    }
}
