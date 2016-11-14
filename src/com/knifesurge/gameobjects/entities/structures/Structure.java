package com.knifesurge.gameobjects.entities.structures;

import com.knifesurge.gameobjects.GameObject;
import com.knifesurge.main.ID;

public abstract class Structure extends GameObject{

	public Structure(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	public void tick()
	{
		structureTick();
	}

	public void render()
	{
		structureRender();
	}

	protected abstract void structureTick();
	protected abstract void structureRender();
	
}
