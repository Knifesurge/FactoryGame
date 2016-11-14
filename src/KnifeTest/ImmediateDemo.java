package KnifeTest;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.knifesurge.display.Display;

/**
 * Renders a colored triangle using immediate mode in OpenGL<br>
 * STAY AWAY FROM RENDERING LIKE THIS ON A LARGE SCALE
 * @author Knifesurge
 *
 */
public class ImmediateDemo {

	public ImmediateDemo()
	{
		Display.create("Immediate Demo", 1080, 720, true);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1, 1, 1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBegin(GL_TRIANGLES);
			glColor3f(1, 0, 0);
			glVertex2f(-0.5f, -0.5f);
			glColor3f(0, 1, 0);
			glVertex2f(0.5f, -0.5f);
			glColor3f(0, 0, 1);
			glVertex2f(0.5f, 0.5f);
			glEnd();
			
			Display.update();
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	public static void main(String[] args)
	{
		new ImmediateDemo();
	}
	
}
