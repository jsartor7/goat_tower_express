package goat_tower_express_2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Listener extends JPanel implements KeyListener {
    private char c = 'e';
    public Goat[] goats;
    
    
    public Listener(Goat[] in_goats) {
        addKeyListener(this);
        goats=in_goats;
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void keyPressed(KeyEvent k) {
        int keyCode = k.getKeyCode();
        switch (keyCode) {
        case KeyEvent.VK_LEFT:
            goats[0].accel(0,-1);
            break;
        case KeyEvent.VK_RIGHT:
            goats[0].accel(0,1);
            break;
    	case KeyEvent.VK_UP:
    		goats[0].accel(1,-1);
    		break;
    	case KeyEvent.VK_DOWN:
    		goats[0].accel(1,1);
    		break;
    }

    }
    public void keyReleased(KeyEvent k) { 

        int keyCode = k.getKeyCode();
        switch (keyCode) {
        case KeyEvent.VK_LEFT:
            goats[0].accel(0,0);
            break;
        case KeyEvent.VK_RIGHT:
            goats[0].accel(0,0);
            break;
    	case KeyEvent.VK_UP:
    		goats[0].accel(1,0);
    		break;
    	case KeyEvent.VK_DOWN:
    		goats[0].accel(1,0);
    		break;
    }
    }
    public void keyTyped(KeyEvent k) {
        int keyCode = k.getKeyCode();
        switch (keyCode) {
        case KeyEvent.VK_LEFT:
            goats[0].accel(0,-1);
            break;
        case KeyEvent.VK_RIGHT:
            goats[0].accel(0,1);
            break;
    	case KeyEvent.VK_UP:
    		goats[0].accel(1,-1);
    		break;
    	case KeyEvent.VK_DOWN:
    		goats[0].accel(1,1);
    		break;
    }
    }

}