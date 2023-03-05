package GameDevelopment;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {
	public static int BOARD_SIZE=3;
	
	public static enum GameStatus{
		Incompleted,XWins,ZWins,Tie   //  Defining parameters
	}
	
	private JButton[][] buttons=new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTurn=true;

	public TicTacToe(){   //creating constructor
		super.setTitle("TicTacToe");
		super.setSize(800,800);
		GridLayout grid=new GridLayout(BOARD_SIZE,BOARD_SIZE);//creating grid layout
		super.setLayout(grid);
		Font font=new Font("Comic Sans",1,150);
		for(int row=0;row<BOARD_SIZE;row++) {
			for(int col=0;col<BOARD_SIZE;col++) {
				JButton button = new JButton("");
				buttons[row][col]=button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedButton=(JButton)e.getSource();
		makeMove(clickedButton);
		GameStatus gs=this.getGameStatus();
		if (gs==GameStatus.Incompleted) {
			return;
		}
		declareWinter(gs);
		int choice=JOptionPane.showConfirmDialog(this, "Do you want to restart the Game");
		if (choice==JOptionPane.YES_OPTION) {
			for(int row=0;row<BOARD_SIZE;row++) {
				for(int col=0;col<BOARD_SIZE;col++) {
					
					buttons[row][col].setText("");
					
				}
			}
			crossTurn=true;
		}else {
			super.dispose();
		}
	}
	
	private void declareWinter(GameStatus gs) {
		// TODO Auto-generated method stub
		if (gs==GameStatus.XWins) {
			JOptionPane.showMessageDialog(this, "X Wins");
		}
		else if (gs==GameStatus.ZWins) {
			JOptionPane.showMessageDialog(this, "Z Wins");
		}
		else {
			JOptionPane.showMessageDialog(this, "It is a Tie.");
		}
	}

	private GameStatus getGameStatus() {
		// TODO Auto-generated method stub
		String text1="",text2="";
		int row=0,col=0;
		// text inside rows
		row=0;
		while (row<BOARD_SIZE) {
			col=0;
			while (col<BOARD_SIZE) {
				text1=buttons[row][col].getText();
				text2=buttons[row][col+1].getText();
				if (!text1.equals(text2)||text1.length()==0) {
					break;
				}
				col++;
			}
			if (col==BOARD_SIZE-1) {
				if (text1.equals("X")) {
					return GameStatus.XWins;
				}
				else {
					return GameStatus.ZWins;
				}
			}
			row++;
		}
		
		// text inside column
		col=0;
		while (col<BOARD_SIZE) {
			row=0;
			while (row<BOARD_SIZE-1) {
				text1=buttons[row][col].getText();
				text2=buttons[row+1][col].getText();
				if (!text1.equals(text2)||text1.length()==0) {
					break;
				}
				row++;
			}
			if (row==BOARD_SIZE-1) {
				if (text1.equals("X")) {
					return GameStatus.XWins;
				}
				else {
					return GameStatus.ZWins;
				}
			}
			col++;
		}
		
		// test diagonal 1
		row=0;col=0;
		while (row<BOARD_SIZE-1) {
			text1=buttons[row][col].getText();
			text2=buttons[row+1][col+1].getText();
			if (!text1.equals(text2)||text1.length()==0) {
				break;
			}
			row++;
			col++;
		}
		if (row==BOARD_SIZE-1) {
			if (text1.equals("X")) {
				return GameStatus.XWins;
			}
			else {
				return GameStatus.ZWins;
			}
		}
		
		// test in diagonal 2
		row=BOARD_SIZE-1;
		col=0;
		while (row>0) {
			text1=buttons[row][col].getText();
			text2=buttons[row-1][col+1].getText();
			if (!text1.equals(text2)||text1.length()==0) {
				break;
			}
			row--;
			col++;
		}
		if (row==0) {
			if (text1.equals("X")) {
				return GameStatus.XWins;
			}
			else {
				return GameStatus.ZWins;
			}
		}
		String text="";
		for( row=0;row<BOARD_SIZE;row++) {
			for(col=0;col<BOARD_SIZE;col++) {
				text=buttons[row][col].getText();
				if (text.length()==0) {
					return GameStatus.Incompleted;
				}
			}
		}
		return GameStatus.Tie;
	}

	private void makeMove(JButton clickedButton) {
		String btntext=clickedButton.getText();
		if (btntext.length()>0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");
		}
		else {
			if (crossTurn) {
				clickedButton.setText("X");
			}
			else {
				clickedButton.setText("0");
			}
			crossTurn=!crossTurn;
		}
	}
}
