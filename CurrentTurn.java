/*Dylan Vander Berg and Dan Kelly
*Farkle
*Current Turn Class
*Code written primarily by Dylan Vander Berg
*Note - Even though there is more code here, it was a lot of similiar code with copy/paste and small revisions between methods
 */

package farkle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.script.ScriptException;

/*Scoring Guidelines:
 * 1 - 100 points
 * 5 - 50 points
 * 3 ones - 1000 points
 * 3 twos-sixes - 100*number on dice
 * 1 through 6 straight - 3000 points
 * 3 pairs - 1500 points (including 4-of-a-kind and a pair)
 * 4 of a kind - 1000 points
 * 5 of a kind - 2000 points
 * 6 of a kind - 3000 points
 */

public class CurrentTurn {
	private int[] dice;//int array for value of dice
	private boolean[] holdDice;
	private boolean[] currentRollHold;
	private int turnScore;
	public static CurrentTurn currentTurn;
	
	//constructor
	public CurrentTurn(){
		dice = new int[6];
		holdDice = new boolean[6];
		currentRollHold = new boolean[6];
		CurrentTurn.currentTurn = this;
	}
	
	/**
	 * @return the dice
	 */
	public int[] getDice() {
		return dice;
	}

	
	public boolean rollDice(){
		System.out.print("Dice are: ");
		for(int i = 0; i < 6; i++){
			if(!holdDice[i]){
				dice[i] = (int)(Math.random() * 6) + 1;
				System.out.print(dice[i] + ", ");
			}else{
				System.out.print("X, ");
			}
		}
		FarkleFrame.frame.setDiceImages(dice);
		for(int i = 0; i < currentRollHold.length; i++){
			currentRollHold[i] = false;
		}
		return !checkFarkle();
	}
	
	public boolean rollDice(int a, int b, int c, int d, int e, int f){
		System.out.print("Dice are: ");
			dice[0] = a;
			dice[1] = b;
			dice[2] = c;
			dice[3] = d;
			dice[4] = e;
			dice[5] = f;
			for(int die : dice){
				System.out.print(die + ", ");
			}
			int[] imageDice = dice;
			for(int i = 0; i < dice.length; i++){
				if(holdDice[i]){
					imageDice[i] = 0;
				}
			}
			for(int i = 0; i < currentRollHold.length; i++){
				currentRollHold[i] = false;
			}
			FarkleFrame.frame.setDiceImages(dice);

		return !checkFarkle();
	}
	
	public void holdDice(ArrayList<Integer> holdList){
		for(int i = 0; i < holdList.size(); i++){
			currentRollHold[holdList.get(i)] = true;
		}
	}
	
	public int getTurnScore(){
		return this.turnScore;
	}
	
	
	//check farkle
	private boolean checkFarkle(){
		boolean farkle = true;
		farkle = (checkThreeOfAKind()) ? false : farkle;
		farkle = (checkOnes()) ? false : farkle;
		farkle = (checkFives()) ? false : farkle;
		farkle = (checkStraight()) ? false : farkle;
		farkle = (checkThreePairs()) ? false : farkle;
		farkle = (checkFourOfAKind()) ? false : farkle;
		farkle = (checkFiveOfAKind()) ? false : farkle;
		farkle = (checkSixOfAKind()) ? false : farkle;
		
		return farkle;
	}
	
	/*BEGIN - Check Farkle Methods*/

	/**
	 * @return the holdDice
	 */
	public boolean[] getHoldDice() {
		return holdDice;
	}

	/**
	 * @return the currentTurn
	 */
	public static CurrentTurn getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * @return the currentRollHold
	 */
	public boolean[] getCurrentRollHold() {
		return currentRollHold;
	}

	private boolean checkSixOfAKind() {
		int[] numOfNums = getNumOfNumsCheck();
		for(int i = 0; i < 6; i++){
			if(numOfNums[i] == 6){
				return true;
			}
		}
		return false;
	}

	private boolean checkFiveOfAKind() {
		int[] numOfNums = getNumOfNumsCheck();
		for(int i = 0; i < 6; i++){
			if(numOfNums[i] >= 5){
				return true;
			}
		}
		return false;
	}

	private boolean checkThreePairs() {
		int[] numOfNums = getNumOfNumsCheck();
		int numPairs = 0;
		for(int i = 0; i < 6; i++){
			if(numOfNums[i] >= 2){
				numPairs++;
			}
		}
		return numPairs == 3;
	}

	private boolean checkStraight() {
		int[] numOfNums = getNumOfNumsCheck();
		for(int i = 0; i < 6; i++){
			if(numOfNums[i] != 1){
				return false;
			}
		}
		return true;
	}

	private boolean checkFourOfAKind() {
		int[] numOfNums = getNumOfNumsCheck();
		for(int i = 0; i < 6; i++){
			if(numOfNums[i] >= 4){
				return true;
			}
		}
		return false;
	}

	private boolean checkFives() {
		int[] numOfNums = getNumOfNumsCheck();
		return numOfNums[4] >= 1;//check if any 5s (index 4 is 5s)
	}

	private boolean checkOnes() {
		int[] numOfNums = getNumOfNumsCheck();
		return numOfNums[0] >= 1;//check if any 1s (index 0 is 1s)
	}

	private boolean checkThreeOfAKind() {
		int[] numOfNums = getNumOfNumsCheck();
		for(int i = 0; i < 6; i++){
			if(numOfNums[i] >= 3){
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<Integer> findAllIndices(int num){
		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		for(int i = 0; i < 6; i++){
			if(dice[i] == num){
				returnArray.add(i);
			}
		}
		return returnArray;
	}
	
	private int[] getNumOfNumsCheck(){
		int[] numOfNums = new int[6];//array to count the amount of each number(i.e. index 0 is num of 1s)
		for(int i = 0; i < dice.length; i++){
			if(!holdDice[i]){
				numOfNums[dice[i] - 1]++;
			}
		}
		return numOfNums;
	}
	
	/*END - Check Farkle Methods*/
	
	/*BEGIN - Scoring Methods*/
	
	private int[] getNumOfNumsScore(){
		int[] numOfNums = new int[6];//array to count the amount of each number(i.e. index 0 is num of 1s)
		for(int i = 0; i < dice.length; i++){
			if(currentRollHold[i]){
				numOfNums[dice[i] - 1]++;
			}
		}
		return numOfNums;
	}
	
	private int scoreSixOfAKind() {
		int[] numOfNums = getNumOfNumsScore();
		for(int i = 0; i < numOfNums.length; i++){
			if(numOfNums[i] == 6){
				for(int ii = 0; ii < dice.length; ii++){
					holdDice[ii] = true;
					currentRollHold[ii] = false;
				}
				return 3000;
			}
		}
		return 0;
	}
	
	private int scoreFiveOfAKind() {
		int[] numOfNums = getNumOfNumsScore();
		for(int i = 0; i < numOfNums.length; i++){
			if(numOfNums[i] == 5){
				for(int ii = 0; ii < dice.length; ii++){
					if(dice[ii] == i + 1){
						holdDice[ii] = true;
						currentRollHold[ii] = false;
					}
				}
				return 2000;
			}
		}
		return 0;
	}
	
	private int scoreFourOfAKind() {
		int[] numOfNums = getNumOfNumsScore();
		for(int i = 0; i < numOfNums.length; i++){
			if(numOfNums[i] == 4){
				int numUsed = 0;
				for(int ii = 0; ii < dice.length; ii++){
					if(dice[ii] == i + 1 && currentRollHold[ii] && numUsed < 4){
						holdDice[ii] = true;
						currentRollHold[ii] = false;
						numUsed++;
					}
				}
				return 1000;
			}
		}
		return 0;
	}
	
	private int scoreThreeOfAKind(){
		int combinationScore = 0;
		int[] numOfNums = getNumOfNumsScore();
		if(numOfNums[0] >= 3){
			combinationScore += 1000;//award 1000 points for 3 ones
			int numUsed = 0;
			for(int i = 0; i < dice.length; i++){
				if(dice[i] == 1 && currentRollHold[i] && numUsed < 3){
					holdDice[i] = true;
					currentRollHold[i] = false;//remove dice so they aren't scored twice
					numUsed++;
				}
			}
		}
		for(int i = 1; i < numOfNums.length; i++){
			if(numOfNums[i] >= 3){
				combinationScore += (i + 1) * 100; //add 100 times dice value for other 3 of a kinds
				int numUsed = 0;
				for(int ii = 0; ii < dice.length; ii++){
					if(dice[ii] == i + 1 && currentRollHold[ii] && numUsed < 3){
						holdDice[ii] = true;
						currentRollHold[ii] = false;//remove dice so they aren't scored twice
						numUsed++;
					}
				}
			}
		}
		return combinationScore;
		
	}
	
	public int scoreStraight(){
		int[] numOfNums = getNumOfNumsScore();
		for(int i = 0; i < numOfNums.length; i++){
			if(numOfNums[i] != 1){
				return 0;
			}
		}
		for(int i = 0; i < holdDice.length; i++){
			holdDice[i] = true;
			currentRollHold[i] = false;
		}
		return 3000;
	}
	
	private int scoreThreePairs() {
		int numPairs = 0;
		int[] numOfNums = getNumOfNumsScore();
		for(int i = 0; i < numOfNums.length; i++){
			if(numOfNums[i] == 4){
				numPairs += 2;
			}else if(numOfNums[i] == 2){
				numPairs++;
			}
		}
		if(numPairs == 3){
			for(int i = 0; i < numOfNums.length; i++){
				if(numOfNums[i] == 4){
					numPairs += 2;
					for(int ii = 0; ii < dice.length; ii++){
						if(dice[ii] == i + 1 && currentRollHold[ii]){
							holdDice[ii] = true;
							currentRollHold[ii] = false;
						}
					}
				}else if(numOfNums[i] == 2){
					numPairs++;
					for(int ii = 0; ii < dice.length; ii++){
						if(dice[ii] == i + 1 && currentRollHold[ii]){
							holdDice[ii] = true;
							currentRollHold[ii] = false;
						}
					}
				}
			}
			return 1500;
		}
		return 0;
	}
	
	private int scoreOnes() {
		int combinationScore = 0;
		for(int i = 0; i < dice.length; i++){
			if(dice[i] == 1 && currentRollHold[i]){
				holdDice[i] = true;
				currentRollHold[i] = false;
				combinationScore += 100;
			}
		}
		return combinationScore;
	}
	
	private int scoreFives() {
		int combinationScore = 0;
		for(int i = 0; i < dice.length; i++){
			if(dice[i] == 5 && currentRollHold[i]){
				holdDice[i] = true;
				currentRollHold[i] = false;
				combinationScore += 50;
			}
		}
		return combinationScore;
	}
	
	/*END - Scoring Methods*/

	public void scoreTurn() {
		turnScore += scoreSixOfAKind();
		turnScore += scoreStraight();
		turnScore += scoreFiveOfAKind();
		turnScore += scoreThreePairs();
		turnScore += scoreFourOfAKind();
		turnScore += scoreThreeOfAKind();
		turnScore += scoreOnes();
		turnScore += scoreFives();
		System.out.println("Current Score is: " + turnScore);
		FarkleFrame.frame.turnScore.setText(turnScore + "");
	}

	public int checkScore(ArrayList<Integer> holdDice) {
		holdDice(holdDice);
		int rollScore = 0;
		rollScore += scoreSixOfAKind();
		rollScore += scoreStraight();
		rollScore += scoreFiveOfAKind();
		rollScore += scoreThreePairs();
		rollScore += scoreFourOfAKind();
		rollScore += scoreThreeOfAKind();
		rollScore += scoreOnes();
		rollScore += scoreFives();
		for(int i = 0; i < holdDice.size(); i++){
			currentRollHold[holdDice.get(i)] = false;
		}
		return rollScore;
	}

	

	//score roll based on user selection
	
	
}
