package editor;

import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.DrawListener;

public class GUI implements DrawListener {
	private Draw draw;
	private A_StringEditor ed;
	// private L_StringEditor ed;

	public GUI() {
		ed = new A_StringEditor();
	//	 ed = new L_StringEditor();
		draw = new Draw();
		draw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		draw.addListener(this);
		draw.setCanvasSize(501, 31);
		draw.setXscale(-250, 250);
		draw.setYscale(-15, 15);
		Font font = new Font("Monospaced", Font.BOLD, 18);
		draw.setFont(font);
		draw.enableDoubleBuffering();
		draw.setPenRadius(0.01);
		draw.clear(Draw.BLACK);
		draw.setPenColor(Draw.LIME);
		draw.line(0, -12, 0, 12);
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.start();
	}
	
	private void start() {
		draw.show();
	}


	@Override
	public void keyReleased(int keycode) {
		switch (keycode) {
		case KeyEvent.VK_BACK_SPACE:
			ed.backspace();
			break;
		case KeyEvent.VK_DELETE:
			ed.delete();
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			ed.moveForward();
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
			ed.moveBackward();
			break;
		default:
			ed.insert((char) keycode);
			break;
		}
		draw.clear(Draw.BLACK);
		draw.setPenColor(Draw.LIME);
		draw.line(0, -12, 0, 12);
		draw.setPenColor(Draw.WHITE);
		String left = ed.lettersBeforeCursor(20);
		String right = ed.lettersAfterCursor(20);
		draw.textRight(-4, 0, left);
		draw.textLeft(4, 0, right);
		draw.show();
	}

}
