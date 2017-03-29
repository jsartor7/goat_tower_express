package goat_tower_express_2;

import java.awt.image.BufferedImage;

public class EntityList {
	
	FileUtil fu=new FileUtil();
    public BufferedImage goatimg;
    public BufferedImage grassimg;
    public BufferedImage plankimg;
	static int num_planks=1;
	static int num_grass=20;
	static int num_entities=1+num_grass+num_planks;
	static Goat goat;
	static Grass[] grass=new Grass[num_grass];
	static Plank[] planks=new Plank[num_planks];
	int x_max;
	int y_max;

    public EntityList(DrawFrame dframe) {
    	grassimg=fu.LoadImg("grass.png");     
		x_max=(int) dframe.getPreferredSize().getWidth();
		y_max=(int) dframe.getPreferredSize().getHeight();
		dframe.master_list=this;
    
    	init_goat();
    	init_grass();
    	init_planks();
    }
    
    private void init_goat()
    {
    	goatimg=fu.LoadImg("goat.png");      
    	goat= new Goat(goatimg,700,700,0,0,x_max,y_max);
    }
    
    private void init_grass()
    {
    	grassimg=fu.LoadImg("grass.png");
    	for (int i=0;i<num_grass;i++)
		{
			grass[i]= new Grass(grassimg,-1,-1,0,0,x_max,y_max);
		}
    }

    private void init_planks()
    {
    	plankimg=fu.LoadImg("plank.jpg");
    	for (int i=0;i<num_planks;i++)
		{
			planks[i]= new Plank(plankimg,1000,1200,10,0,x_max,y_max);
		}
    }
    
    public void update_entities(int time)
    {
		goat.reconfigure(time);
		for(int i=0; i<num_grass;i++)
		{
			grass[i].reconfigure(time);
			goat.check_collision(grass[i]);
		}
		
		for(int i=0; i<num_planks;i++)
		{
			planks[i].reconfigure(time);
			goat.check_collision(planks[i]);
		}
    }
    
    public Entity[] get_list()
    {
    	int i=0;
    	Entity[] list=new Entity[num_entities];
    	list[i]=(Entity) goat;
    	i++;
    	
    	for(int j=0;j<planks.length;j++,i++)
        {
        	list[i]=(Entity) planks[j];
        }
    	
    	for(int j=0;j<grass.length;j++,i++)
        {
        	list[i]=(Entity) grass[j];
        }


    	return list;
    	
    }
}
