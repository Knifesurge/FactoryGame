package com.knifesurge.main;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import com.knifesurge.display.Display;
import com.knifesurge.gameobjects.entities.Player;
import com.knifesurge.gameobjects.entities.structures.BasicFactory;
import com.knifesurge.handlers.Handler;
import com.knifesurge.pathfinding.Grid;

public class FactoryGame implements Runnable{

	public static final int WIDTH = 1080;
	public static final int HEIGHT = WIDTH / 16 * 9;
	
	private static Thread thread;
	public static Handler handler;
	public static Player player;
	
	public static Grid mainGrid = new Grid(WIDTH, HEIGHT);
	
	private void init()
	{
		Display.create("Factory Game", WIDTH, HEIGHT, true);
		
		/************ OPENGL ************/
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1.0, 1.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		/************ OPENGL ************/
		
		handler = new Handler();
		player = new Player(100, 100, ID.Player);
		handler.addObject(player);
		handler.addObject(new BasicFactory(200, 200, ID.Structure));
	}
	public synchronized void start()
	{
		thread = new Thread(this, "MAIN_GAME_THREAD");
		String osName = System.getProperty("os.name");
        if (osName.contains("Mac"))
        	thread.run();
        else
        	thread.start();
	}
	
	public synchronized static void stop()
	{
		try
		{
			destroy();
			thread.join();
			System.exit(0);
		} catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();
		double nsPerTick = 1.0E9D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		while(!Display.shouldClose())
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while(delta >= 1)
			{
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try { 
				Thread.sleep(2);
			} catch (Exception e) {e.printStackTrace();}
			
			if(shouldRender)
			{
				frames++;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000)
			{
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
			Display.update();
		}
	}
	
	public void tick()
	{
		handler.tick();
	}
	
	private static void destroy()
	{
		Display.destroy();
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);

		handler.render();
	}
}
