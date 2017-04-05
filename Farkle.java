/*Dylan Vander Berg and Dan Kelly
*Farkle
*Main game class
*Code written primarily by Dan Kelly
 */

package farkle;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Scanner; 

public class Farkle {
	private static boolean player1Turn;
	private static int player1 = 0;
	private static int player2 = 0;
	private static CurrentTurn roll;
	static FarkleFrame frame;
	static ArrayList<Integer>hold;
	
	public static void main(String[] args) {		
		Scanner input = new Scanner(System.in);
		
		//setup gui
		//setup game variables (points, turn, etc.)
		//while points < 10,000
		//roll dice
		//select dice to keep
		//add points to current turn
		//user can roll again or bank points
		//on farkle, toggle turn
		//on bank points, add turn score to point, toggle turn

		frame = new FarkleFrame();
        frame.setVisible(true);
		
		
		
		setPlayer1Turn(true);
//		while (player1 < 10000 && player2 < 10000){
//			setPlayer1Turn(!isPlayer1Turn());
//			if(isPlayer1Turn()){
//				System.out.println("Player 1 turn.");
//			}else{
//				System.out.println("Player 2 turn.");
//			}
//			System.out.println("Player 1 points: " + player1);
//			System.out.println("Player 2 points: " + player2);
//			CurrentTurn roll = new CurrentTurn();
//			while(true){
//				if (roll.rollDice(1,6,6,1,6,6)){
//					frame.disableButtons();
//					hold = new ArrayList<Integer>();
//					System.out.println("Which dice would you like to remove>>");
//					String inputString = input.nextLine();
//					System.out.print(inputString);
//					while(!inputString.equals("")){
//						hold.add(Integer.parseInt(inputString.substring(0, 1)));
//						if(inputString.length() > 2){
//							inputString = inputString.substring(2);
//						}else{
//							break;
//						}
//					}
//					roll.holdDice(hold);
//					roll.scoreTurn();
//					System.out.println("Do you want to roll again? y for yes and n for no");
//					frame.enableButtons();
//				}
//				//if person farkled
//				else{
//					System.out.println("Farkle");
//					break;
//				}
//			}
//		}

	}
	public static void rollDice(){
		if(CurrentTurn.currentTurn == null){
			hold = new ArrayList<Integer>();
			roll = new CurrentTurn();
			frame.mainOut.setText("");
		}
		if (roll.rollDice(1,2,4,4,5,2)){
			frame.disableButtons();
			frame.selectDice = true;
			int[] dice = CurrentTurn.currentTurn.getDice();
			String msg = "";
			for(int i = 0; i < dice.length; i++){
				if(i != 0){
					msg += ", " + dice[i];
				}else{
					msg += dice[i];
				}
			}
			frame.appendMessage("Rolled " + msg);
		}else{
			frame.selectDice = false;
			frame.enableButtons();
			player1Turn = !player1Turn;
			frame.appendMessage("Farkle");
			frame.appendMessage("Player " + (player1Turn ? 1 : 2) + " Turn");
			CurrentTurn.currentTurn = null;
			frame.resetDice();
		}
	}
	
	public static void bankPoints(){
		if(isPlayer1Turn()){
			player1 += roll.getTurnScore();
			if(player1 > 10000){
				frame.appendMessage("Player 1 wins");
				frame.disableButtons();
			}
			player1Turn = false;
			frame.appendMessage("Player 2 Turn");
			CurrentTurn.currentTurn = null;
		}
		else{
			player2 += roll.getTurnScore();
			if(player2 > 10000){
				frame.appendMessage("Player 2 wins");
				frame.disableButtons();
			}
			player1Turn = true;
			frame.appendMessage("Player 1 Turn");
			CurrentTurn.currentTurn = null;
		}
		
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
