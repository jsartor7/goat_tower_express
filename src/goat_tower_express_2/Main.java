package goat_tower_express_2;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Main extends JApplet  {

	static FileUtil fu=new FileUtil();
    static JFrame frame = new JFrame("Goat Tower Express 2");
	static DrawFrame dframe;
	static EntityList master_list;
	static Listener listen;
	
	
	static private void init_game()
	{
	        frame.setVisible(true);
	        dframe = new DrawFrame();
	        master_list=new EntityList(dframe);
	        listen= new Listener(master_list);
	        frame.add("Center",listen);
	        listen.add(dframe);
	        listen.setPreferredSize(dframe.getPreferredSize());
	        
	       // frame.add("Center", dframe);     
	        
	       frame.addWindowListener(new WindowAdapter() {
	           public void windowClosing(WindowEvent e) {System.exit(0);}
	       });
	}
	
	static private void draw()
	{
        frame.repaint();
        frame.pack();     
	}
	
	
	static private void main_loop()
	{
		long last_time=System.currentTimeMillis();
		while(true)
		{
			long time=System.currentTimeMillis();
			double frac=((double) time%1000);
			frac=frac/1000;
			int elapse=(int) (time-last_time);
			if(elapse>30)
			{	
				last_time=time;
				master_list.update_entities(elapse);        
				draw();

			}
		}
	}
	
	//main is for applications
	public static void main(String[] args) {
		init_game();
		main_loop();
	}
	
//these 4 are for applets
	 public void init()
	{
			init_game();
	}
	 public void start()
	 {
		 main_loop();
	 }
	 public void stop()
	 {
		 //do nothing
	 }
	 public void destroy()
	 {
		 //nothin
	 }


}
