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
	static int max_goats=2;
	static Goat[] goats = new Goat[max_goats];
	static Listener listen;
	
	static private void init_game()
	{
	        frame.setVisible(true);
	        dframe = new DrawFrame(goats);
	        listen= new Listener(goats);
	        frame.add("Center",listen);
	        listen.add(dframe);
	        listen.setPreferredSize(dframe.getPreferredSize());
	        
	       // frame.add("Center", dframe);     
	        
	       frame.addWindowListener(new WindowAdapter() {
	           public void windowClosing(WindowEvent e) {System.exit(0);}
	       });
	       init_goats();
	}

	static private void init_goats()
	{
		//need to handle resizing of frame or something
		int x_max=(int) dframe.getPreferredSize().getWidth();
		int y_max=(int) dframe.getPreferredSize().getHeight();
		
		//this is kind of stupid
		//needs to be more generic for different sprites
		int width=(int) dframe.goatimg.getWidth();
		int height=(int) dframe.goatimg.getHeight();
		int type=0;
		for (int i=0;i<max_goats;i++)
		{
			if(i>0)
			{
				width=(int) dframe.grassimg.getWidth();
				height=(int) dframe.grassimg.getHeight();
				type=1;				
				goats[i]= new Goat(type,700,700,0,0,x_max,y_max,width,height);

			}
			else
				goats[i]= new Goat(type,0,0,i,i,x_max,y_max,width,height);
		}

	}
	
	static private void draw()
	{
        frame.repaint();
        frame.pack();     
	}
	
	static private void update_goats(int time)
	{
		for(int i=0; i<max_goats;i++)
		{
			goats[i].reconfigure(time);
			if(i>0)
				goats[0].check_collision(goats[i]);
		}
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
				update_goats(elapse);        
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
