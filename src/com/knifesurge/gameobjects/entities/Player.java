package com.knifesurge.gameobjects.entities;

import org.lwjgl.opengl.GL11;

import com.knifesurge.main.ID;

public class Player extends Entity{

	public Player(int x, int y, ID id) {
		super(x, y, id);
		this.radius = 50f;
	}

	protected void entityTick()
	{
		move((float)destX, (float)destY);
		destX = 0;
		destY = 0;
		
	}

	protected void entityRender()
	{
		
		GL11.glPointSize(radius);
		
		GL11.glBegin(GL11.GL_POINTS);
			GL11.glColor3f(0.0f, 1.0f, 0.5f);
			GL11.glVertex2f(x, y);
		GL11.glEnd();
	}
	
	public void move(float x, float y)
	{
		GL11.glTranslatef(x, y, 0);
	}
}
