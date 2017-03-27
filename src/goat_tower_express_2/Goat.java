package goat_tower_express_2;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

//should split this class into a generic class of objects
//and specific ones that do slightly diff functions
public class Goat {
	public int type=0;
	int max_vel=5;
	double accel_const=1;
	private double[] pos={0,0};
	private double[] vel={0,0};
	private double[] accel={0,0};
	private double[] size={0,0};
	//careful with these since they're ints but pos is double
	//also 600 is meaningless, will be overwritten in constructor
    int[] max={600,600};	
    int[] min={0,0};	
    int death_count=0;
    int kill_count=0;
    BufferedImage img;   

    float[] scales = { 1f, 1f, 1f, 1f };
    float[] offsets = new float[4];
    RescaleOp rop;

    
    public Goat(int input_type, BufferedImage inputimg, int x_i, int y_i, int x_vi, int y_vi, int x_max, int y_max)
    {
    	//0 for goat, 1 for grass
    	type=input_type;
    	img=inputimg;
    	size[0]=img.getWidth();
    	size[1]=img.getHeight();

    	//this has to happen before assigning random positions
    	set_minmax(0,x_max,(int) size[0]);
    	set_minmax(1,y_max,(int) size[1]);

    	//position<0 is a flag to randomize the position in that component
    	pos[0]=x_i-size[0]/2;
    	pos[1]=y_i-size[1]/2;
    	for(int i=0;i<2;i++)
    	{
    		if(pos[i]<0)
    			random_pos(i);
    	}
    	vel[0]=x_vi;
    	vel[1]=y_vi;
    }
    
    public void set_minmax(int dir, int new_max,int size)
    {
    	max[dir]=new_max-size/2;
    	min[dir]=size/2;
    	
    }
    
    public int get_pos(int mode, int dir)
    {    	
    	//positions are stored as doubles to avoid rounding error
    	//but publicly are ints b/c drawn at integer locations

    	//mode 0 is for hitboxing, we will give center
    	//mode 1 is for drawing, we will give top left corner
    	if(mode==0)    	
        	return (int) pos[dir];
    	else
    		return (int) (pos[dir]-size[dir]/2);

    	
    }

	//currently not being used
    public void move(int dir, int val)
    {
    	vel[dir]+=val;
    	if(vel[dir]>max_vel)
    		vel[dir]-=val;
    }
    
    public void accel(int dir, int val)
    {
    	accel[dir]=val*accel_const;
    }
    
    public int check_collision(Goat goat)
    {
    	int sum=0;
    	for(int i=0;i<2;i++)
    	{
    		if(Math.abs(pos[i]-goat.get_pos(0, i))<Math.abs(size[i]/2+goat.size[i]/2))
    			sum+=1;
    	}
    	
    	if(sum==2)
    	{
    		goat.respawn();
    		kill_count++;
    		return 1;
    	}
    	else
    		return 0;
    	
    }

    public void random_pos(int dir)
    {
    	//dir can be either 0 for x, 1 for y, or negative for both
    	for(int i=0;i<2;i++)
    	{
    		if(dir<0||dir==i)
    		pos[i]=Math.random()*max[i];
    	}
    }

    public void respawn()
    {
    	random_pos(-1);
    	death_count++;
    }

    public void setColorScale() {
        scales[0] = 1f;
        scales[1] = 1f;
        scales[2] = 1f;
        offsets[1]= 130f;
        rop = new RescaleOp(scales, offsets, null);
    }

    public void reconfigure(int time)
    {
            double delta= ((double) time)/10;
            for(int i=0; i<2; i++){
            	pos[i]+=(vel[i]*delta);
            	
            	//hard caps on position
            	//todo: offset coordinates so they are centered
            	//important for hitboxes and stuff
            	if(pos[i]>max[i])
            	{
            		pos[i]=max[i];
            		vel[i]=0;
            	}
            	if(pos[i]<min[i])
            	{
            		pos[i]=min[i];
            		vel[i]=0;
            	}
            	
            	//arrow keys accelerate you
            	vel[i]+=accel[i]*delta;
            	
            	//terminal velocity of sorts
            	if(vel[i]>max_vel)
            		vel[i]-=1;
            	else if(vel[i]<-max_vel)
            		vel[i]+=1;
            }
    	
        if(kill_count>=100)
        	setColorScale();
    }
}
