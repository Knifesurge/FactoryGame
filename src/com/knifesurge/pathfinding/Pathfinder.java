package com.knifesurge.pathfinding;

import java.util.ArrayList;

import com.knifesurge.gameobjects.GameObject;
import com.knifesurge.main.FactoryGame;

public class Pathfinder {

	public GameObject host = null;
	private int x;
	private int y;
	private int destX;
	private int destY;
	public Direction direction = Direction.stop;
	private ArrayList<Node> destField = new ArrayList<Node>();
	
	public Pathfinder(GameObject object)
	{
		this.host = object;
		this.x = object.x;
		this.y = object.y;
		this.destX = this.host.destX;
		this.destY = this.host.destY;
	}
	
	public void setDestination(int x, int y)
	{
		this.destX = x;
		this.destY = y;
		setDestinationField();
	}
	
	public Direction getDirection(int destX, int destY)
	{
		setDestinationField();
		this.x = this.host.x;
		this.y = this.host.y;
		boolean left; boolean right; boolean above; boolean below; left = right = above = below = false;
		if(this.destField.contains(FactoryGame.mainGrid.nodes[this.x][this.y]))
		{
			this.host.isPathing = false;
			return Direction.stop;
		}
		
		if(!amIValid()) return Direction.stop;
		if(destX < this.host.x) left = true;
		if(destX > this.host.x) right = true;
		if(destY < this.host.x) below = true;
		if(destY > this.host.y) above = true;
		
		if((above) && (!right) && (!left)) return Direction.up;
		if((above) && (right)) return Direction.upRight;
		if((above) && (left)) return Direction.upLeft;
		if((below) && (!right) && (!left)) return Direction.down;
		if((below) && (right)) return Direction.downRight;
		if((below) && (left)) return Direction.downLeft;
		if((left) && (!above) && (!below)) return Direction.left;
		if((right) && (!above) && (!below)) return Direction.right;
		return Direction.stop;
	}
	
	public void move()
	{
		this.direction = getDirection(this.destX, this.destY);
		if(this.direction == Direction.stop)
		{
			this.host.setVelX(0);
			this.host.setVelY(0);
			return;
		}
		if(this.direction == Direction.up)
		{
			this.host.setVelX(0);
			this.host.setVelY(1);
			return;
		}
		if(this.direction == Direction.down)
		{
			this.host.setVelX(0);
			this.host.setVelY(-1);
			return;
		}
		if(this.direction == Direction.right)
		{
			this.host.setVelX(1);
			this.host.setVelY(0);
			return;
		}
		if(this.direction == Direction.left)
		{
			this.host.setVelX(-1);
			this.host.setVelY(0);
			return;
		}
		if(this.direction == Direction.upRight)
		{
			this.host.setVelX(1);
			this.host.setVelY(1);
			return;
		}
		if(this.direction == Direction.upLeft)
		{
			this.host.setVelX(-1);
			this.host.setVelY(1);
			return;
		}
		if(this.direction == Direction.downRight)
		{
			this.host.setVelX(1);
			this.host.setVelY(-1);
			return;
		}
		if(this.direction == Direction.downLeft)
		{
			this.host.setVelX(-1);
			this.host.setVelY(-1);
			return;
		}
	}
	
	public boolean amIValid()
	{
		if(this.host.isAlive) return true;
		Direction dir = getPureDirection(this.destX, this.destY);
		int newX = this.host.x;
		int newY = this.host.y;
		switch(dir)
		{
		case right:
			newX += this.host.speed;
			break;
		case downRight:
			newX += this.host.speed;
			newY -= this.host.speed;
			break;
		case upRight:
			newX += this.host.speed;
			newY += this.host.speed;
			break;
		case up:
			newY += this.host.speed;
			break;
		case down:
			newY -= this.host.speed;
			break;
		case downLeft:
			newX -= this.host.speed;
			newY -= this.host.speed;
			break;
		case left:
			newX -= this.host.speed;
			break;
		case upLeft:
			newX -= this.host.speed;
			newY += this.host.speed;
		}
		if(FactoryGame.mainGrid.isRangeValid(newX - this.host.getWidth() / 2, newX + this.host.getWidth() / 2, newY - this.host.getHeight() / 2, newY + this.host.getHeight() / 2, this.host))
			return true;
		return false;
	}
	
	public Direction getPureDirection(int destX, int destY)
	{
		boolean left;boolean right;boolean above;boolean below; left = right = above = below = false;
		if(destX < this.host.x) left = true;
		if(destX > this.host.x) right = true;
		if(destY < this.host.y) below = true;
		if(destY > this.host.y) above = true;
		
		if((above) && (!right) && (!left)) return Direction.up;
		if((above) && (right)) return Direction.upRight;
		if((above) && (left)) return Direction.upLeft;
		if((below) && (!right) && (!left)) return Direction.down;
		if((below) && (right)) return Direction.downRight;
		if((below) && (left)) return Direction.downLeft;
		if((left) && (!above) && (!below)) return Direction.left;
		if((right) && (!above) && (!below)) return Direction.right;
		return Direction.stop;
	}
	
	public int getDistanceFrom(GameObject target)
	{
		int difX = Math.abs(this.x - target.x);
		int difY = Math.abs(this.y - target.y);
		int distance = 0;
		boolean done = false;
		while(!done)
		{
			if((difX > 0) && (difY > 0))
			{
				distance += 14;
				difX--;
				difY--;
			}
			if(difX > 0)
			{
				distance += 10;
				difX--;
			}
			if(difY > 0)
			{
				distance += 10;
				difY--;
			}
			if((difY == difX) && (difX == 0))
			{
				done = true;
			}
		}
		return distance;
	}
	
	private ArrayList<Node> setDestinationField()
	{
		int size = 2;
		this.destField = FactoryGame.mainGrid.getRange(this.destX - size, this.destX + size, this.destY + size, this.destY - size);
		return FactoryGame.mainGrid.getRange(this.destX - size, this.destX + size, this.destY + size, this.destY - size);
	}
	
}
