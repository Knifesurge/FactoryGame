package com.knifesurge.gameobjects.entities.structures;

import org.lwjgl.opengl.GL11;

import com.knifesurge.main.ID;

public class BasicFactory extends Structure{

	public BasicFactory(int x, int y, ID id) {
		super(x, y, id);
		this.width = 150;
		this.height = 150;
	}

	protected void structureTick()
	{
		
	}

	protected void structureRender()
	{
			GL11.glLoadIdentity();	//Reset the matrix
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor3f(1.0f, 0.0f, 0.0f);
				GL11.glVertex2f(x, y);
				GL11.glColor3f(1.0f, 0.0f, 0.0f);
				GL11.glVertex2f(x+width, y);
				GL11.glColor3f(1.0f, 0.0f, 0.0f);
				GL11.glVertex2f(x+width, y+height);
				GL11.glColor3f(1.0f, 0.0f, 0.0f);
				GL11.glVertex2f(x, y+height);
			GL11.glEnd();
	}

}
