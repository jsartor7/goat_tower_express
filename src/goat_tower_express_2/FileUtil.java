package goat_tower_express_2;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;


//this "extends" is just a hack such that getcodebase will work
public class FileUtil extends JApplet{
    public URL find_URL(String filepath) {
    	URL src=null;

        try {
             src = ((new File(filepath)).toURI()).toURL();
        } catch (MalformedURLException e) {
        }
        return src;
    }
    
    public BufferedImage LoadImg(String imageFileName)
    {   
    	//this code gets all fucked up when you're in applet vs application
    	//it seems you need it in top level folder for application
    	//and in the class directory for applet
    	BufferedImage img=null;
    	try {
    	    //URL url = new URL(getCodeBase(), imageFileName);
    	   // img = ImageIO.read(url);
    		URL res = getClass().getResource(imageFileName);
    		img= ImageIO.read(res);
    	} catch (IOException e){
    		    	System.out.println("Image  could not be read");
    		    	} 
      //  URL imageURL = this.getClass().getResource(imageFileName);
     //   Image img = Toolkit.getDefaultToolkit().createImage(imageURL);
        //	try {
    	//InputStream is = this.getClass().getResourceAsStream(imageFileName);
    //    URL imageSrc=find_URL(imageFileName);
    //    img = ImageIO.read(this.getClass().getResourceAsStream("pics/"+imageFileName));
    //	} catch (IOException e) {
  //  	System.out.println("Image "+imageFileName+" could not be read");
   // 	}
    	return img;
    }
    		
}
