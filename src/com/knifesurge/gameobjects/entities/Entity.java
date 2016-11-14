package com.knifesurge.gameobjects.entities;

import com.knifesurge.gameobjects.GameObject;
import com.knifesurge.main.ID;

public abstract class Entity extends GameObject{

	public Entity(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick()
	{
		entityTick();
	}

	public void render()
	{
		entityRender();
	}

	protected abstract void entityTick();
	protected abstract void entityRender();
	protected abstract void move(float x, float y);
	
}
