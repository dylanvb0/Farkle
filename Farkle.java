/*Dylan Vander Berg and Dan Kelly
*Farkle
*Main game class
*Code written primarily by Dan Kelly
 */

package farkle;

import java.util.ArrayList;
import java.util.Scanner; 

public class Farkle {
	

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
		
		
		
		int player1 = 0;
		int player2 = 0;
		boolean player1Turn = false;
		while (player1 < 10000 && player2 < 10000){
			player1Turn = !player1Turn;
			if(player1Turn){
				System.out.println("Player 1 turn.");
			}else{
				System.out.println("Player 2 turn.");
			}
			System.out.println("Player 1 points: " + player1);
			System.out.println("Player 2 points: " + player2);
			CurrentTurn roll = new CurrentTurn();
			while(true){
				if (roll.rollDice(1,6,6,1,6,6)){
					ArrayList<Integer>hold = new ArrayList<Integer>();
					System.out.println("Which dice would you like to remove>>");
					String inputString = input.nextLine();
					System.out.print(inputString);
					while(!inputString.equals("")){
						hold.add(Integer.parseInt(inputString.substring(0, 1)));
						if(inputString.length() > 2){
							inputString = inputString.substring(2);
						}else{
							break;
						}
					}
					roll.holdDice(hold);
					roll.scoreTurn();
					System.out.println("Do you want to roll again? y for yes and n for no");
					String answer = input.nextLine();
					if(answer.equals("n")){
						if(player1Turn){
							player1 += roll.getTurnScore();
						}
						else{
							player2 += roll.getTurnScore();
						}
						break;
					}
				}
				//if person farkled
				else{
					System.out.println("Farkle");
					break;
				}
			}
		}

	}
	
	//method selectDie
	//method hold
	//method roll

}
