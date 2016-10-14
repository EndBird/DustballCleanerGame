package ui;

import java.util.Scanner;

import game.VacuumGame;

public class TextUI implements UI {
	private VacuumGame game;

	public TextUI(VacuumGame game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}
	
	public void launchGame() {
	while (!this.game.gameOver()){
    Scanner user_input = new Scanner(System.in);
	System.out.println(this.game.getGrid().toString());
	System.out.println("Enter your move: "); String move = user_input.next();
	this.game.move(move.charAt(0));}
	
	this.displayWinner();
	
	}
	
	public void displayWinner() {

	    if (!this.game.gameOver()) {
	        return;
	    }
	    
	    int won = this.game.getWinner();
		String message;

		if (won == 1) {
			message = "Congratulations Player 1! You won the game with a score of " + 
					this.game.getVacuumOne().getScore() + ".";
			System.out.println(message);
		} else { 
			message = "Congratulations Player 2! You won the game with a score of " + 
					this.game.getVacuumTwo().getScore() + "."; 
			System.out.println(message);
		}}

}
