import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MayinTarlasi implements MouseListener {
	JFrame frame;
	Btn[][] board = new Btn[10][10];
	int openButton;
	public MayinTarlasi() {
		openButton=0;
		frame = new JFrame("May�n Tarlas�");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10,10));
		for(int row=0; row<board.length; row ++) {
			for(int col =0; col<board[0].length; col++) {
				Btn b = new Btn(row,col);
				frame.add(b);
				b.addMouseListener(this);
				board[row][col]=b;
			}
		}
		generateMine();
		updateCount();
		//printMine();
		
		frame.setVisible(true);
		
	}
	public void generateMine() {
		int i=0;
		while(i<10) {
			int randRow = (int) (Math.random()*board.length);
			int randCol = (int) (Math.random()*board[0].length);
			
			while(board[randRow][randCol].isMine()) {
				randRow=(int) (Math.random()*board.length);
				randCol=(int)(Math.random()* board[0].length);
				
			}
			board[randRow][randCol].setMine(true);
			i++;
			
		}
		
	}
	public void print() {
		for(int row=0; row<board.length; row ++) {
			for(int col =0; col<board[0].length; col++) {
				if(board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("may�n.jpg"));
				}else {
					board[row][col].setText(board[row][col].getCount()+"");
					board[row][col].setEnabled(false);
				}
				
			}
		}
		
	}
	public void printMine() {
		for(int row=0; row<board.length; row ++) {
			for(int col =0; col<board[0].length; col++) {
				if(board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("may�n.jpg"));
				}
			}
		}
		
	}
		
	
	public void updateCount() {
		for(int row=0; row<board.length; row ++) {
			for(int col =0; col<board[0].length; col++) {
				if(board[row][col].isMine()) {
					counting(row, col);
				}
				
				
				
			}
		}
		
	}
	public void counting(int row,int col) {
		for(int i =row-1; i<=row+1; i++) {
			for(int k=col-1;k<= col+1;k++) {
				try {
					int value = board[i][k].getCount();
					board[i][k].setCount(++value);
					
				}catch(Exception e) {
					
				}
			}
		}
		
	}
	
	public void open(int row, int col) {
		if(row<0 || row>=board.length || col<0 || col>=board[0].length || board[row][col].getText().length() >0 || board[row][col].isEnabled()==false) {
			return;
		}else if(board[row][col].getCount() !=0){
			board[row][col].setText(board[row][col].getCount()+ "");
			board[row][col].setEnabled(false);
			openButton++;
		}else {
			openButton++;
			board[row][col].setEnabled(false);
			open(row-1,col);
			open(row+1,col);
			open(row, col-1);
			open(row,col+1);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Btn b = (Btn) e.getComponent();
		if(e.getButton()==1) {
			System.out.println("Sol t�k");
			if(b.isMine()) {
				JOptionPane.showMessageDialog(frame, "May�na bast�n�z Oyun Bitti!!");
				print();
			}
			else {
				open(b.getRow(),b.getCol());
				if(openButton ==(board.length*board[0].length)- 10) {
					JOptionPane.showMessageDialog(frame, "Tebrikler oyunu kazand�n�z.");
					print();
				}
				
			}
			
		}
		else if(e.getButton()==3) {
			System.out.println("Sa� t�k");
			if(!b.isFlag()) {
				b.setIcon(new ImageIcon("flag.png"));
				b.setFlag(true);
			}
			else {
				b.setIcon(null);
				b.setFlag(false);
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
