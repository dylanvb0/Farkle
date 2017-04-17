/*Dylan Vander Berg and Dan Kelly
*Farkle
*JFrame class
* Dan - 888 lines
* Dylan - 325 lines
 */
package farkle;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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
	private String currentText;
	

	public FarkleFrame(){
		FarkleFrame.frame = this;
		try{
			initGUI();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void initGUI() throws IOException{
		setTitle("Farkle");
		setIconImage(new ImageIcon(getClass().getResource("farkle-icon.png")).getImage());
        setSize(700, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        currentText = "";
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
        Font bigFont = new Font("Dialog.bold", Font.PLAIN, 36);
        Font smallFont = new Font("Dialog.bold", Font.PLAIN, 24);
        player1Label = new JLabel();
        player1Label.setVisible(true);
        player1Label.setText("Player 1");
        player1Label.setForeground(Color.RED);
        player1Label.setFont(smallFont);
        player2Label = new JLabel();
        player2Label.setVisible(true);
        player2Label.setText("Player 2");
        player2Label.setFont(smallFont);
        player1Score = new JLabel();
        player1Score.setVisible(true);
        player1Score.setText("0");
        player1Score.setFont(bigFont);
        turnScore = new JLabel();
        turnScore.setVisible(true);
        turnScore.setText("0");
        turnScore.setFont(bigFont);
        player2Score = new JLabel();
        player2Score.setVisible(true);
        player2Score.setText("0");
        player2Score.setFont(bigFont);
        BufferedImage userImage = ImageIO.read(new File("src/farkle/user.png"));
        JLabel userImageLabel = new JLabel(new ImageIcon(userImage.getScaledInstance(200, 200, Image.SCALE_FAST)));
        BufferedImage user2Image = ImageIO.read(new File("src/farkle/user-2.png"));
        JLabel user2ImageLabel = new JLabel(new ImageIcon(user2Image.getScaledInstance(200, 200, Image.SCALE_FAST)));
        BufferedImage diceEmptyImage = ImageIO.read(new File("src/farkle/dice_empty.png"));
        diceImages = new BufferedImage[7];
        diceImages[0] = diceEmptyImage;
        player1Hold1 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        /* BEGIN - Dan Kelly*/
        player1Hold1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Farkle.isPlayer1Turn()){//make sure it's player 1s turn
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[0] && turn.getCurrentRollHold()[0]){//make sure turn is active, dice isn't being held from previous roll, and it is in hold position right now
						Farkle.hold.remove((Object)0);//remove occurrence of 0 in hold array
						dice[0].setIcon(new ImageIcon(diceImages[turn.getDice()[0]].getScaledInstance(100, 100, Image.SCALE_FAST)));//set dice image to blank dice for middle spot
						player1Hold1.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));//change dice image to real dice for hold spot
						Object[] returnArr = turn.checkScore(Farkle.hold);//check score with selected dice
						int diceScore = (int) returnArr[0];//score with selected dice
						boolean allUsed = (boolean) returnArr[1];//boolean to make sure all selected dice are being used
							turnScore.setText((turn.getTurnScore() + diceScore) + "");//update turnscore label
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){//check if hold at least 1, score > 0, and all dice selected dice contribute to score
							enableButtons();
						}else{
							disableButtons();
						}
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
        player1Hold2 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[1] && turn.getCurrentRollHold()[1]){
						Farkle.hold.remove((Object)1);
						dice[1].setIcon(new ImageIcon(diceImages[turn.getDice()[1]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						
							player1Hold2.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player1Hold3 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold3.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[2] && turn.getCurrentRollHold()[2]){
						Farkle.hold.remove((Object)2);
						dice[2].setIcon(new ImageIcon(diceImages[turn.getDice()[2]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						
							player1Hold3.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player1Hold4 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold4.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[3] && turn.getCurrentRollHold()[3]){
						Farkle.hold.remove((Object)3);
						dice[3].setIcon(new ImageIcon(diceImages[turn.getDice()[3]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						
							player1Hold4.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player1Hold5 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold5.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[4] && turn.getCurrentRollHold()[4]){
						Farkle.hold.remove((Object)4);
						dice[4].setIcon(new ImageIcon(diceImages[turn.getDice()[4]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						
							player1Hold5.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player1Hold6 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player1Hold6.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[5] && turn.getCurrentRollHold()[5]){
						Farkle.hold.remove((Object)5);
						dice[5].setIcon(new ImageIcon(diceImages[turn.getDice()[5]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						
							player1Hold6.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        /* END - Dan Kelly*/
        /* BEGIN - Dylan Vander Berg*/
        BufferedImage dice1Image = ImageIO.read(new File("src/farkle/dice_1.png"));
        JLabel dice1 = new JLabel(new ImageIcon(dice1Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        dice1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CurrentTurn turn = CurrentTurn.currentTurn;
				if(turn != null && !turn.getHoldDice()[0]){//make sue you are in a turn and you aren't holding the current die
					Farkle.hold.add(0);//add index 0 to dice being held
					dice1.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));//set middle die icon to empty
					if(Farkle.isPlayer1Turn()){//set hold die icon for appropriate side
						player1Hold1.setIcon(new ImageIcon(diceImages[turn.getDice()[0]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					else{
						player2Hold1.setIcon(new ImageIcon(diceImages[turn.getDice()[0]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					Object[] returnArr = turn.checkScore(Farkle.hold);//check score for current held dice
					int diceScore = (int) returnArr[0];
					boolean allUsed = (boolean) returnArr[1];
					turnScore.setText((turn.getTurnScore() + diceScore) + "");//set turnscore to reflect scored dice

       				if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){//make sure at least 1 die is held, score > 0, and all held dice contributed to score
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
        /* END - Dylan Vander Berg*/
        /* BEGIN - Dan Kelly*/
        JLabel dice2 = new JLabel(new ImageIcon(dice2Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        dice2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CurrentTurn turn = CurrentTurn.currentTurn;
				if(turn != null && !turn.getHoldDice()[1]){
					Farkle.hold.add(1);
					dice2.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
					if(Farkle.isPlayer1Turn()){
						player1Hold2.setIcon(new ImageIcon(diceImages[turn.getDice()[1]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					else{
						player2Hold2.setIcon(new ImageIcon(diceImages[turn.getDice()[1]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					Object[] returnArr = turn.checkScore(Farkle.hold);
					int diceScore = (int) returnArr[0];
					boolean allUsed = (boolean) returnArr[1];
					turnScore.setText((turn.getTurnScore() + diceScore) + "");

					if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
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
        BufferedImage dice3Image = ImageIO.read(new File("src/farkle/dice_3.png"));
        JLabel dice3 = new JLabel(new ImageIcon(dice3Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        dice3.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CurrentTurn turn = CurrentTurn.currentTurn;
				if(turn != null && !turn.getHoldDice()[2]){
					Farkle.hold.add(2);
					dice3.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
					if(Farkle.isPlayer1Turn()){
						player1Hold3.setIcon(new ImageIcon(diceImages[turn.getDice()[2]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					else{
						player2Hold3.setIcon(new ImageIcon(diceImages[turn.getDice()[2]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					Object[] returnArr = turn.checkScore(Farkle.hold);
					int diceScore = (int) returnArr[0];
					boolean allUsed = (boolean) returnArr[1];
					turnScore.setText((turn.getTurnScore() + diceScore) + "");

					if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
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
        BufferedImage dice4Image = ImageIO.read(new File("src/farkle/dice_4.png"));
        JLabel dice4 = new JLabel(new ImageIcon(dice4Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        dice4.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CurrentTurn turn = CurrentTurn.currentTurn;
				if(turn != null && !turn.getHoldDice()[3]){
					Farkle.hold.add(3);
					dice4.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
					if(Farkle.isPlayer1Turn()){
						player1Hold4.setIcon(new ImageIcon(diceImages[turn.getDice()[3]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					else{
						player2Hold4.setIcon(new ImageIcon(diceImages[turn.getDice()[3]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					Object[] returnArr = turn.checkScore(Farkle.hold);
					int diceScore = (int) returnArr[0];
					boolean allUsed = (boolean) returnArr[1];
					turnScore.setText((turn.getTurnScore() + diceScore) + "");

					if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
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
        BufferedImage dice5Image = ImageIO.read(new File("src/farkle/dice_5.png"));
        JLabel dice5 = new JLabel(new ImageIcon(dice5Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        dice5.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CurrentTurn turn = CurrentTurn.currentTurn;
				if(turn != null && !turn.getHoldDice()[4]){
					Farkle.hold.add(4);
					dice5.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
					if(Farkle.isPlayer1Turn()){
						player1Hold5.setIcon(new ImageIcon(diceImages[turn.getDice()[4]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					else{
						player2Hold5.setIcon(new ImageIcon(diceImages[turn.getDice()[4]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					Object[] returnArr = turn.checkScore(Farkle.hold);
					int diceScore = (int) returnArr[0];
					boolean allUsed = (boolean) returnArr[1];
					turnScore.setText((turn.getTurnScore() + diceScore) + "");

					if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
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
        BufferedImage dice6Image = ImageIO.read(new File("src/farkle/dice_6.png"));
        JLabel dice6 = new JLabel(new ImageIcon(dice6Image.getScaledInstance(100, 100, Image.SCALE_FAST)));
        dice6.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CurrentTurn turn = CurrentTurn.currentTurn;
				if(turn != null && !turn.getHoldDice()[5]){
					Farkle.hold.add(5);
					dice6.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
					if(Farkle.isPlayer1Turn()){
						player1Hold6.setIcon(new ImageIcon(diceImages[turn.getDice()[5]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					else{
						player2Hold6.setIcon(new ImageIcon(diceImages[turn.getDice()[5]].getScaledInstance(100, 100, Image.SCALE_FAST)));
					}
					Object[] returnArr = turn.checkScore(Farkle.hold);
					int diceScore = (int) returnArr[0];
					boolean allUsed = (boolean) returnArr[1];
					turnScore.setText((turn.getTurnScore() + diceScore) + "");

					if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
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
        diceImages = new BufferedImage[] {diceEmptyImage, dice1Image, dice2Image, dice3Image, dice4Image, dice5Image, dice6Image};
        dice = new JLabel[] {dice1, dice2, dice3, dice4, dice5, dice6};
        player2Hold1 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[0] && turn.getCurrentRollHold()[0]){
						Farkle.hold.remove((Object)0);
						dice[0].setIcon(new ImageIcon(diceImages[turn.getDice()[0]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						if(!Farkle.isPlayer1Turn())
							player2Hold1.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
						
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player2Hold2 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[1] && turn.getCurrentRollHold()[1]){
						Farkle.hold.remove((Object)0);
						dice[1].setIcon(new ImageIcon(diceImages[turn.getDice()[1]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						if(!Farkle.isPlayer1Turn())
							player2Hold2.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
							
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player2Hold3 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold3.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[2] && turn.getCurrentRollHold()[2]){
						Farkle.hold.remove((Object)2);
						dice[2].setIcon(new ImageIcon(diceImages[turn.getDice()[2]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						if(!Farkle.isPlayer1Turn())
							player2Hold3.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
							
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player2Hold4 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold4.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[3] && turn.getCurrentRollHold()[3]){
						Farkle.hold.remove((Object)3);
						dice[3].setIcon(new ImageIcon(diceImages[turn.getDice()[3]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						if(!Farkle.isPlayer1Turn())
							player2Hold4.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
						
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player2Hold5 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold5.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[4] && turn.getCurrentRollHold()[4]){
						Farkle.hold.remove((Object)4);
						dice[4].setIcon(new ImageIcon(diceImages[turn.getDice()[4]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						if(!Farkle.isPlayer1Turn())
							player2Hold5.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
							
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        player2Hold6 = new JLabel(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
        player2Hold6.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!Farkle.isPlayer1Turn()){
					CurrentTurn turn = CurrentTurn.currentTurn;
					if(turn != null && !turn.getHoldDice()[5] && turn.getCurrentRollHold()[5]){
						Farkle.hold.remove((Object)5);
						dice[5].setIcon(new ImageIcon(diceImages[turn.getDice()[5]].getScaledInstance(100, 100, Image.SCALE_FAST)));
						
						player2Hold6.setIcon(new ImageIcon(diceImages[0].getScaledInstance(100, 100, Image.SCALE_FAST)));
							
						Object[] returnArr = turn.checkScore(Farkle.hold);
						int diceScore = (int) returnArr[0];
						boolean allUsed = (boolean) returnArr[1];
						
						turnScore.setText((turn.getTurnScore() + diceScore) + "");
	
						if(Farkle.hold.size() > 0 && diceScore > 0 && allUsed){
							enableButtons();
						}else{
							disableButtons();
						}
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
        /* END - Dan Kelly*/
        /* BEGIN - Dylan Vander Berg*/
        mainOut = new JLabel();
        
        mainOut.setFont(smallFont);
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
	
	//set up layout
	private void createLayout(JComponent... args) {

        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());//use GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(args[7], c);//player 1 icon
        c.gridx = 2;
        c.gridheight = 1;
        pane.add(args[0], c);//roll button
        c.gridy = 1;
        pane.add(args[1], c);//bank button
        c.gridx = 4;
        c.gridy = 0;
        c.gridheight = 2;
        pane.add(args[8], c);//player 2 icon
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        pane.add(args[2], c);//player 1 label
        c.gridx = 4;
        pane.add(args[3], c);//player 2 label
        c.gridy = 4;
        c.gridx = 0;
        pane.add(args[4], c);//player 1 score
        c.gridx = 2;
        pane.add(args[5], c);//turn score
        c.gridx = 4;
        pane.add(args[6], c);//player 2 score
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
	
	public void disableBank(){
		bankButton.setEnabled(false);
	}

	//add to main message output
	public void appendMessage(String string) {
		mainOut.setText("<html>" + currentText + "<br>" + string + "</html>");
		currentText += "<br>" + string;
	}
	
	public void clearMessage(){
		mainOut.setText("");
		currentText = "";
	}

	//reset images to black dice in hold spots
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
/* END - Dylan Vander Berg*/
