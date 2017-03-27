package goat_tower_express_2;

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
    
    public Goat(int input_type,int x_i, int y_i, int x_vi, int y_vi, int x_max, int y_max, int width, int height)
    {
    	type=input_type;
    	pos[0]=x_i-width/2;
    	pos[1]=y_i-height/2;
    	vel[0]=x_vi;
    	vel[1]=y_vi;
    	set_minmax(0,x_max,width);
    	set_minmax(1,y_max,height);
    	size[0]=width;
    	size[1]=height;
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
    		return 1;
    	}
    	else
    		return 0;
    	
    }
    
    public void respawn()
    {
    	for(int i=0;i<2;i++)
    	{
    		pos[i]=Math.random()*max[i];
    	}
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
    	
        
    }
}
