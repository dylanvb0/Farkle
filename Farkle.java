/*Dylan Vander Berg and Dan Kelly
*Farkle
*Main game class
* Code written primarily by Dan Kelly
* Dan - 70 lines
* Dylan - 50 lines
 */

package farkle;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane; 

public class Farkle {
	private static boolean player1Turn;
	private static int player1 = 0;
	private static int player2 = 0;
	private static CurrentTurn roll;
	static FarkleFrame frame;
	static ArrayList<Integer>hold;
	
	public static void main(String[] args) {		
		frame = new FarkleFrame();
        frame.setVisible(true);	
		setPlayer1Turn(true);
		frame.disableBank();

	}
	public static void rollDice(){
		if(CurrentTurn.currentTurn == null){//if first roll of turn, set up turn
			hold = new ArrayList<Integer>();
			roll = new CurrentTurn();
			frame.clearMessage();
			frame.appendMessage("Player " + (player1Turn?1:2) + " Turn");
			frame.resetDice();
		}
		roll.scoreTurn();
		//check if all dice were used
		boolean allUsed = true;
		for(boolean b : roll.getHoldDice()){
			allUsed = !b ? false : allUsed;
		}
		//if all used, let them roll all 5 again
		if(allUsed){
			roll.clearHoldDice();
			frame.resetDice();
		}
		if (roll.rollDice()){//roll dice
			frame.disableButtons();
			frame.selectDice = true;
			int[] dice = CurrentTurn.currentTurn.getDice();
			//create string from dice
			String msg = "";
			boolean first = true;
			for(int i = 0; i < dice.length; i++){
				if(!roll.getHoldDice()[i]){
					if(!first){
						msg += ", " + dice[i];
					}else{
						msg += dice[i];
						first = false;
					}
				}
			}
			frame.appendMessage("Rolled " + msg);//output msg to main message output
			hold.clear();
		}else{//farkled
			frame.selectDice = false;
			frame.enableButtons();
			frame.disableBank();//don't allow player to bank points from farkled turn
			player1Turn = !player1Turn;
			frame.appendMessage("Farkle");
			frame.appendMessage("Player " + (player1Turn ? 1 : 2) + " Turn");
			CurrentTurn.currentTurn = null;
			frame.player1Label.setForeground(player1Turn?Color.RED:Color.BLACK);
			frame.player2Label.setForeground(player1Turn?Color.BLACK:Color.RED);
			
			
		}
	}
	
	public static void bankPoints(){
		roll.scoreTurn();//score turn based on selected dice
		frame.disableBank();
		if(isPlayer1Turn()){
			player1 += roll.getTurnScore();
			frame.player1Score.setText(player1 + "");
			frame.player1Label.setForeground(Color.BLACK);
			frame.player2Label.setForeground(Color.RED);
			if(player1 > 10000){//if player won
				frame.appendMessage("Player 1 wins");
				frame.disableButtons();
				JOptionPane.showMessageDialog(frame, "Player 1 wins!");//prompt winner
				System.exit(0);
			}
		}
		else{
			player2 += roll.getTurnScore();
			frame.player2Score.setText(player2 + "");
			frame.player1Label.setForeground(Color.RED);
			frame.player2Label.setForeground(Color.BLACK);
			if(player2 > 10000){
				frame.appendMessage("Player 2 wins");
				JOptionPane.showMessageDialog(frame, "Player 2 wins!");
				System.exit(0);
			}
		}
		player1Turn = !player1Turn;//toggle turn
		frame.appendMessage("Player " + (player1Turn?1:2) + " Turn");//display turn
		CurrentTurn.currentTurn = null;
	}
	//method selectDie
	//method hold
	//method roll
	/**
	 * @return the player1Turn
	 */
	public static boolean isPlayer1Turn() {
		return player1Turn;
	}
	/**
	 * @param player1Turn the player1Turn to set
	 */
	public static void setPlayer1Turn(boolean player1Turn) {
		Farkle.player1Turn = player1Turn;
	}


}
