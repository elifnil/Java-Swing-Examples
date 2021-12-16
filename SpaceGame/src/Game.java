import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Timer;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
class Fire{
	private int x;
	private int y;
	public Fire(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}

public class Game extends JPanel implements KeyListener, ActionListener{
	Timer timer = new Timer(2,this);
	private int elapsed_time=0;
	private int spent_fire=0;
	private BufferedImage image;
	private ArrayList<Fire> fires = new ArrayList<Fire>();
	private int fireY=1;
	private int ballX=0;
	private int movedBallX=2;
	private int spaceShipX=0;
	private int movedSpaceShipX=20;
	public boolean control() {
		for(Fire fire:fires) {
			if(new Rectangle(fire.getX(),fire.getY(),10,20).intersects(new Rectangle(ballX,0,20,20))) {
				return true;
			}
		}
		return false;
	}
	
	public Game() {
		try {
			image =ImageIO.read(new FileImageInputStream(new File("rocket.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(Color.black);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		elapsed_time+=5;
		g.setColor(Color.red);
		g.fillOval(ballX, 0, 20, 20);
		g.drawImage(image, spaceShipX, 490,image.getWidth()/20,image.getHeight()/20,this);
		for(Fire fire: fires) {
			if(fire.getY()<0) {
				fires.remove(fire);
			}
		}
		g.setColor(Color.BLUE);
		for(Fire fire: fires) {
			g.fillRect(fire.getX(), fire.getY(),10, 20);
		}
		if(control()) {
			timer.stop();
			String message = "Tebrikler, kazand�n�z! \n" +
							 "Harcanan Ate�: " + spent_fire+
						     "\nGe�en S�re: " + elapsed_time/1000.0 + " saniye";
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
			}
	}
	
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c==KeyEvent.VK_LEFT) {
			if(spaceShipX<=0) {
				spaceShipX=0;
			}
			else {
				spaceShipX-=movedSpaceShipX;
			}
			
		}
		else if(c==KeyEvent.VK_RIGHT) {
			if(spaceShipX>=750) {
				spaceShipX=750;
			}
			else {
				spaceShipX+=movedSpaceShipX;
			}
			
		}
		else if(c==KeyEvent.VK_SPACE) {
			fires.add(new Fire(spaceShipX+15,470));
			spent_fire++;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(Fire fire : fires) {
			fire.setY(fire.getY()-fireY);
		}
		ballX += movedBallX;
		if(ballX>=760) {
			movedBallX=-movedBallX;
			
		}
		if(ballX<=0) {
			movedBallX =-movedBallX;
		}
		repaint();
	
		
	}

}
