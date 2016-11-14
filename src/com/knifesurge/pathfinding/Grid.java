package com.knifesurge.pathfinding;

import java.util.ArrayList;

import com.knifesurge.gameobjects.GameObject;
import com.knifesurge.main.FactoryGame;

public class Grid {

	public Node[][] nodes;
	private int length;
	private int height;
	
	public Grid(int x, int y)
	{
		x++;
		y++;
		this.nodes = new Node[x][y];
		for(int i=0;i<this.nodes.length;i++)
		{
			for(int j=0;j<this.nodes[i].length;j++)
			{
				this.nodes[i][j] = new Node(i, j);
			}
		}
		
		this.length = x;
		this.height = y;
		
		int k = 0;
		for(Node[] n : this.nodes)
		{
			for(Node a : n)
				k++;
		}
		//DEBUG
		System.out.println("Nodes " + k);
	}
	
	
	public Node[] getNeighbours(Node in)
	{
		ArrayList<Node> node = new ArrayList<Node>();
		if(in.isOutOfBounds)
		{
			System.err.println("Getting neighbour for out of bounds node");
			return null;
		}
		
		node.add(this.nodes[(in.x + 1)][in.y]);
		node.add(this.nodes[(in.x - 1)][in.y]);
		node.add(this.nodes[in.x][in.y + 1]);
		node.add(this.nodes[in.x][in.y - 1]);
		node.add(this.nodes[(in.x + 1)][in.y + 1]);
		node.add(this.nodes[(in.x - 1)][in.y - 1]);
		node.add(this.nodes[(in.x + 1)][in.y - 1]);
		node.add(this.nodes[(in.x - 1)][in.y + 1]);
		
		Node[] output = new Node[node.size()];
		for(int i=0;i<output.length;i++)
			output[i] = ((Node)node.get(i));
		return output;
	}
	
	public void update()
	{
		for(Node[] n : this.nodes)
			for(Node real : n)
				real.checkIfValid();
	}
	
	public boolean isRangeValid(int startX, int endX, int startY, int endY, GameObject object)
	{
		for(int x = startX; x < endX; x++)
			for(int y = startY; y < endY; y++)
				if(!FactoryGame.mainGrid.nodes[x][y].isValid(object)) return false;
		return true;
	}
	
	public GameObject getOccupantOfRange(int startX, int endX, int startY, int endY, GameObject object)
	{
		for(int x = startX; x < endX; x++)
			for(int y = startY; y < endY; y++)
				if(FactoryGame.mainGrid.nodes[x][y].getOccupant(object) != null)
					return FactoryGame.mainGrid.nodes[x][y].getOccupant(object);
		return null;
	}
	
	public ArrayList<Node> getRange(int startX, int endX, int startY, int endY)
	{
		ArrayList<Node> output = new ArrayList();
		try {
			for(int x = startX; x < endX; x++)
				for(int y = startY; y < endY; y++)
					output.add(FactoryGame.mainGrid.nodes[x][y]);
		} catch (Exception e) {e.printStackTrace();}
		return output;
	}
	
	public GameObject getObjectAt(int x, int y)
	{
		for(GameObject object : FactoryGame
				.handler.object)
			if((x < object.x + object.getWidth() / 2) && (x > object.x - object.getWidth() / 2) && (y < object.y + object.getHeight() / 2) && (y > object.y - object.getHeight() / 2))
				return object;
		return null;
	}
	
}
