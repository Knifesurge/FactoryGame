package com.knifesurge.handlers;

import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import com.knifesurge.gameobjects.GameObject;
import com.knifesurge.main.FactoryGame;
import com.knifesurge.main.ID;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick()
	{
		try {
			for(GameObject o : this.object)
			{
				o.tick();
				containToMap(o);
			}
		} catch (Exception e) {}
	}
	
	public void render()
	{
		for (int i = 0; i < this.object.size(); i++)
		{
			GameObject temp = (GameObject)this.object.get(i);
			if(temp.id != ID.Player)	//Not rendering a player, so need to "protect" the object's drawing matrix from any moving the player has or will do
			{
				GL11.glPushMatrix();	//To protect from using the transformations of anything moving
				temp.render();
				GL11.glPopMatrix();
			}
			else	//Rendering a player
				temp.render();
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	public void containToMap(GameObject o)
	{
		o.x = o.clamp(o.x, FactoryGame.WIDTH - o.getWidth() / 2, 5 + o.getWidth() / 2);
		o.y = o.clamp(o.y, FactoryGame.HEIGHT - o.getHeight(), 5 + o.getWidth() / 2);
	}
	
	
}
