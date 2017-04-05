package farkle;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

public class FarkleFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static FarkleFrame frame;
	public JLabel player1Label;
	public JLabel player2Label;
	public JLabel player1Score;
	public JLabel player2Score;
	public JLabel turnScore;
	public JLabel mainOut;
	public JLabel player1Hold1;
	public JLabel player1Hold2;
	public JLabel player1Hold3;
	public JLabel player1Hold4;
	public JLabel player1Hold5;
	public JLabel player1Hold6;
	public JLabel player2Hold1;
	public JLabel player2Hold2;
	public JLabel player2Hold3;
	public JLabel player2Hold4;
	public JLabel player2Hold5;
	public JLabel player2Hold6;
	private BufferedImage[] diceImages;
	private JLabel[] dice;
	private JButton rollButton;
	private JButton bankButton;
	public boolean selectDice;
	

	public FarkleFrame(){
		FarkleFrame.frame = this;
		try{
			initGUI();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void initGUI() throws IOException{
		setTitle("Farkle");
        setSize(700, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        rollButton = new JButton("Roll");
        rollButton.setVisible(true);
        rollButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Farkle.rollDice();
				
			}
        	
        });
        bankButton = new JButton("Bank");
        bankButton.setVisible(true);
        bankButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Farkle.bankPoints();
			}
        	
        });
        player1Label = new JLabel();
        player1Label.setVisible(true);
        player1Label.setText("Player 1");
        player2Label = new JLabel();
        player2Label.setVisible(true);
        player2Label.setText("Player 2");
        player1Score = new JLabel();
        player1Score.setVisible(true);
        player1Score.setText("0");
        turnScore = new JLabel();
        turnScore.setVisible(true);
        turnScore.setText("0");
        player2Score = new JLabel();
        player2Score.setVisible(true);
        player2Score.setText("0");
        BufferedImage userImage = ImageIO.read(new File("src/farkle/user.png"));
        JLabel userImageLabel = new JLabel(new ImageIcon(userImage.getScaledInstance(200, 200, Image.SCALE_FAST)));
        BufferedImage user2Image = ImageIO.read(new File("src/farkle/user-2.png"));
        JLabel user2ImageLabel = new JLabel(new ImageIcon(user2Image.getScaledInstance(200, 200, Image.SCALE_FAST)));
        BufferedImage diceEmptyImage = ImageIO.read(new File("src/farkle/dice_empty.png"));
        player1Hold1 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold2 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold3 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold4 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold5 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold6 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        BufferedImage dice1Image = ImageIO.read(new File("src/farkle/dice_1.png"));
        JLabel dice1 = new JLabel(new ImageIcon(dice1Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        dice1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CurrentTurn turn = CurrentTurn.currentTurn;
				if(turn != null && !turn.getHoldDice()[0]){
					Farkle.hold.add(0);
					dice1.setIcon(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
					if(Farkle.isPlayer1Turn()){
						player1Hold1.setIcon(new ImageIcon(diceImages[turn.getDice()[0]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					else{
						player2Hold1.setIcon(new ImageIcon(diceImages[turn.getDice()[0]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					if(Farkle.hold.size() > 0 && turn.checkScore(Farkle.hold) > 0){
						turnScore.setText((turn.getTurnScore() + turn.checkScore(Farkle.hold)) + "");
						enableButtons();
					}else{
						disableButtons();
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        BufferedImage dice2Image = ImageIO.read(new File("src/farkle/dice_2.png"));
        JLabel dice2 = new JLabel(new ImageIcon(dice2Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        BufferedImage dice3Image = ImageIO.read(new File("src/farkle/dice_3.png"));
        JLabel dice3 = new JLabel(new ImageIcon(dice3Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        BufferedImage dice4Image = ImageIO.read(new File("src/farkle/dice_4.png"));
        JLabel dice4 = new JLabel(new ImageIcon(dice4Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        BufferedImage dice5Image = ImageIO.read(new File("src/farkle/dice_5.png"));
        JLabel dice5 = new JLabel(new ImageIcon(dice5Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        BufferedImage dice6Image = ImageIO.read(new File("src/farkle/dice_6.png"));
        JLabel dice6 = new JLabel(new ImageIcon(dice6Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        diceImages = new BufferedImage[] {diceEmptyImage, dice1Image, dice2Image, dice3Image, dice4Image, dice5Image, dice6Image};
        dice = new JLabel[] {dice1, dice2, dice3, dice4, dice5, dice6};
        player2Hold1 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold2 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold3 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold4 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold5 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold6 = new JLabel(new ImageIcon(diceEmptyImage.getScaledInstance(100, 100, Image.SCALE_FAST)));
        mainOut = new JLabel();
        createLayout(rollButton, bankButton, player1Label, player2Label, player1Score, turnScore, player2Score, userImageLabel, user2ImageLabel,
        		player1Hold1, player1Hold2, player1Hold3, player1Hold4, player1Hold5, player1Hold6,
        		player2Hold1, player2Hold2, player2Hold3, player2Hold4, player2Hold5, player2Hold6,
        		dice1, dice2, dice3, dice4, dice5, dice6, mainOut);
	}
	
	public void setDiceImages(int[] rolled){
		for(int i = 0; i < rolled.length; i++){
			dice[i].setIcon(new ImageIcon(diceImages[rolled[i]].getScaledInstance(100, 100, Image.SCALE_FAST)));
		}
	}
	
	private void createLayout(JComponent... args) {

        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(args[7], c);
        c.gridx = 2;
        c.gridheight = 1;
        pane.add(args[0], c);
        c.gridy = 1;
        pane.add(args[1], c);
        c.gridx = 4;
        c.gridy = 0;
        c.gridheight = 2;
        pane.add(args[8], c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        pane.add(args[2], c);
        c.gridx = 4;
        pane.add(args[3], c);
        c.gridy = 4;
        c.gridx = 0;
        pane.add(args[4], c);
        c.gridx = 2;
        pane.add(args[5], c);
        c.gridx = 4;
        pane.add(args[6], c);
        //col 1 dice
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 5;
        pane.add(args[9], c);
        c.gridy = 6;
        pane.add(args[10], c);
        c.gridy = 7;
        pane.add(args[11], c);
        c.gridy = 8;
        pane.add(args[12], c);
        c.gridy = 9;
        pane.add(args[13], c);
        c.gridy = 10;
        pane.add(args[14], c);
        //col 2 dice
        c.gridx = 1;
        c.gridy = 5;
        pane.add(args[21], c);
        c.gridy = 6;
        pane.add(args[22], c);
        c.gridy = 7;
        pane.add(args[23], c);
        c.gridy = 8;
        pane.add(args[24], c);
        c.gridy = 9;
        pane.add(args[25], c);
        c.gridy = 10;
        pane.add(args[26], c);
        //col 3 dice
        c.gridx = 2;
        c.gridy = 5;
        pane.add(args[15], c);
        c.gridy = 6;
        pane.add(args[16], c);
        c.gridy = 7;
        pane.add(args[17], c);
        c.gridy = 8;
        pane.add(args[18], c);
        c.gridy = 9;
        pane.add(args[19], c);
        c.gridy = 10;
        pane.add(args[20], c);
        c.gridx = 3;
        c.gridy = 5;
        c.gridwidth = 3;
        c.gridheight = 6;
        pane.add(args[27], c);
    }

	public void disableButtons(){
		rollButton.setEnabled(false);
		bankButton.setEnabled(false);
	}
	
	public void enableButtons(){
		rollButton.setEnabled(true);
		bankButton.setEnabled(true);
	}

	public void appendMessage(String string) {
		mainOut.setText(mainOut.getText() + "\n" + string);
		
	}

	public void resetDice() {
		player1Hold1.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player1Hold2.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player1Hold3.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player1Hold4.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player1Hold5.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player1Hold6.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player2Hold1.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player2Hold2.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player2Hold3.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player2Hold4.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player2Hold5.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		player2Hold6.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
		
	}
	
}
