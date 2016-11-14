package KnifeTest;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.knifesurge.display.Display;

/**
 * Render a colored triangle using a Display List<br>
 * Good for static geometry / geometry that won't / can't be modified later
 * @author Knifesurge
 *
 */
public class DisplayListsDemo {

	public DisplayListsDemo()
	{
		Display.create("Display Lists Demo", 1080, 720, true);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1, 1, 1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		int displayListHandle = glGenLists(1);
		
		glNewList(displayListHandle, GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glColor3f(1, 0, 0);
		glVertex2f(-0.5f, -0.5f);
		glColor3f(0, 1, 0);
		glVertex2f(0.5f, -0.5f);
		glColor3f(0, 0, 1);
		glVertex2f(0.5f, 0.5f);
		glEnd();
		glEndList();
		
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			glCallList(displayListHandle);
			
			Display.update();
		}
		Display.destroy();
	}
	
	public static void main(String[] args)
	{
		new DisplayListsDemo();
	}
}
