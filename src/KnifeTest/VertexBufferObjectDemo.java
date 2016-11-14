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
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.awt.Window;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.knifesurge.display.Display;
import com.knifesurge.main.FactoryGame;

/**
 * Renders a colored triangle using Vertex Buffer Objects (VBOs)<br>
 * Outperforms any other rendering mode in OpenGL<br>
 * Able to modify data on the fly<br>
 * Non-deprecated<br>
 * Use the GPU
 * @author Knifesurge
 *
 */
public class VertexBufferObjectDemo {

	public VertexBufferObjectDemo()
	{
		Display.create("Vertex Buffer Objects Demo", 1080, 720, true);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1, 1, 1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		final int amountOfVertices = 3;
		final int vertexSize = 3;
		final int colorSize = 3;
		
		FloatBuffer vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
		vertexData.put(new float[]{-0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0.5f, 0});
		vertexData.flip();
		
		FloatBuffer colorData = BufferUtils.createFloatBuffer(amountOfVertices * colorSize);
		colorData.put(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
		colorData.flip();
		
		int vboVertexHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);	//Stores float buffer data from VBO into the buffer
		glBindBuffer(GL_ARRAY_BUFFER, 0);							//Unbind the buffer
		
		int vboColorHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboColorHandle);
		glBufferData(GL_ARRAY_BUFFER, colorData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
			glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);
			
			glBindBuffer(GL_ARRAY_BUFFER, vboColorHandle);
			glColorPointer(colorSize, GL_FLOAT, 0, 0L);
			
			glEnableClientState(GL_VERTEX_ARRAY);
			glEnableClientState(GL_COLOR_ARRAY);
			glDrawArrays(GL_TRIANGLES, 0, amountOfVertices);
			glDisableClientState(GL_COLOR_ARRAY);
			glDisableClientState(GL_VERTEX_ARRAY);
			Display.update();
		}
		
		glDeleteBuffers(vboVertexHandle);
		glDeleteBuffers(vboColorHandle);
		
		Display.destroy();
	}
	
	public static void main(String[] args)
	{
		new VertexBufferObjectDemo();
	}
}
