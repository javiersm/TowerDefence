import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;


public class KeyHandler implements MouseListener, MouseMotionListener {


	public void mouseDragged(MouseEvent e) {
			
	}

	public void mouseMoved(MouseEvent e) {
		//se actualiza la posicion del raton
		//hay que quitarle los bordes que sobran del frame y solo dejar los del panel
		Screen.mouse = new Point(e.getX() - ((Frame.size.width - Screen.myWidth)/2), 
								 e.getY() - ((Frame.size.height - Screen.myHeight)) -((Frame.size.width -Screen.myWidth)/2));
	}


	public void mouseClicked(MouseEvent e) {
		
	}


	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
