package KnifeTest;

import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColorPointer;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertexPointer;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.knifesurge.display.Display;

/**
 * Render <br>
 * Mainly used if Vertex Buffer Objects are not supported on the system running
 * @author Knifesurge
 *
 */
public class VertexArraysDemo {

	public VertexArraysDemo()
	{
		Display.create("Vertex Arrays Demo", 1080, 720, true);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1, 1, 1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		final int amountOfVertices = 6;
		final int vertexSize = 2;
		final int colorSize = 3;
		
		FloatBuffer vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
		vertexData.put(new float[]{-0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f});
		vertexData.flip();		//NEED TO DO THIS IN ORDER FOR OPENGL TO BE ABLE TO READ THE BUFFER
		
		FloatBuffer colorData = BufferUtils.createFloatBuffer(amountOfVertices * colorSize);
		colorData.put(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
		colorData.flip();		//NEED TO DO THIS IN ORDER FOR OPENGL TO BE ABLE TO READ THE BUFFER
		
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			glEnableClientState(GL_VERTEX_ARRAY);
			glEnableClientState(GL_COLOR_ARRAY);
			
			glVertexPointer(vertexSize, GL_FLOAT, 0, vertexData);
			glColorPointer(colorSize, GL_FLOAT, 0, colorData);
			
			glDrawArrays(GL_TRIANGLES, 0, amountOfVertices);	//Draws stuff on the screen
			
			glDisableClientState(GL_COLOR_ARRAY);
			glDisableClientState(GL_VERTEX_ARRAY);
			
			Display.update();
		}
		Display.destroy();
	}
	
	public static void main(String[] args)
	{
		new VertexArraysDemo();
	}
}
