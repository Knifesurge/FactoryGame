package com.knifesurge.pathfinding;

import com.knifesurge.display.Display;
import com.knifesurge.gameobjects.GameObject;
import com.knifesurge.main.FactoryGame;

public class Node {

	public int x;
	public int y;
	public boolean validity = false;
	public boolean isOutOfBounds = false;
	public Node parent = null;
	
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Node(Node n)
	{
		this.x = n.x;
		this.y = n.y;
	}

	public boolean isValid()
	{
		if(this.x > FactoryGame.WIDTH)
		{
			this.isOutOfBounds = true;
			return false;
		}
		if(this.x < 0)
		{
			this.isOutOfBounds = true;
			return false;
		}
		if(this.y > FactoryGame.HEIGHT)
		{
			this.isOutOfBounds = true;
			return false;
		}
		if(this.y < 0)
		{
			this.isOutOfBounds = true;
			return false;
		}
		
		for(GameObject object : FactoryGame.handler.object)
		{
			if((this.x < object.x + object.getWidth() / 1) && (this.x > object.x - object.getWidth() / 1) && (this.y < object.y + object.getHeight() / 2) && (this.y > object.y - object.getHeight() / 2))
				if(!object.isAlive) return false;
		}
		return true;
	}
	
	public boolean isValid(GameObject ex)
	{
		if(this.x > FactoryGame.WIDTH)
		{
			this.isOutOfBounds = true;
			return false;
		}
		if(this.x < 0)
		{
			this.isOutOfBounds = true;
			return false;
		}
		if(this.y > FactoryGame.HEIGHT)
		{
			this.isOutOfBounds = true;
			return false;
		}
		if(this.y < 0)
		{
			this.isOutOfBounds = true;
			return false;
		}
		
		for(GameObject object : FactoryGame.handler.object)
		{
			if((this.x < object.x + object.getWidth() / 1) && (this.x > object.x - object.getWidth() / 1) && (this.y < object.y + object.getHeight() / 2) && (this.y > object.y - object.getHeight() / 2))
				if((object != ex) && (!object.isAlive)) return false;
		}
		return true;
	}
	
	public GameObject getOccupant(GameObject ex)
	{
		for(GameObject object : FactoryGame.handler.object)
		{
			if((this.x < object.x + object.getWidth() / 2) && (this.x > object.x - object.getWidth() / 2) && (this.y < object.y + object.getHeight() / 2) && (this.y > object.y - object.getHeight() / 2) && (object != ex))
				return object;
		}
		return null;
	}
	
	public void checkIfValid()
	{
		this.validity = isValid();
	}
	
	public void checkIfValid(GameObject ex)
	{
		this.validity = isValid(ex);
	}
	
	public void reset()
	{
		this.parent = null;
		checkIfValid();
	}
	
	public Node[] getNeighbours()
	{
		return FactoryGame.mainGrid.getNeighbours(this);
	}
	
	public String toString()
	{
		if(isValid())
			return "Node at " + this.x + ", " + this.y + " valid";
		return "Node at " + this.x + ", " + this.y + " invalid";
	}
	
}
