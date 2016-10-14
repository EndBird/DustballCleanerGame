package sprites;
import game.Constants;

public class Vacuum extends Sprite implements Moveable {
	private int capacity;
	private int score;
	private int fullness;
	private Sprite under;
	/**initialize a Vacuum
	 * 
	 * @param symbol
	 * @param row
	 * @param column
	 * @param capacity
	 */
	public Vacuum(char symbol, int row, int column,int capacity) {
		super(symbol, row, column);
		this.capacity = capacity;
		this.score = Constants.INIT_SCORE;
		this.fullness = Constants.EMPTY;
		
		
		// TODO Auto-generated constructor stub
	}
	
	 /**return the Symbol of Vacuum
	  * @return char
	  */
	public char getSymbol() {		
		return super.getSymbol();
		// TODO Auto-generated method stub
	}
	
	/**return the row of Vacuum
	 * @return int 
	 */
	public int getRow() {return super.getRow();}
	
	/**return the column of Vacuum
	 * @return int 
	 */
	 
	public int getColumn() {return super.getColumn();}

	@Override
	/**return the str representation of Vacuum
	 * @return String 
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();}
	/**
	 * check to see whether Vacuum can clean some dirt or DustBall. score is the number of points associated with cleaning the dirt/DustBall
	 * if yes, then increase Vacuum fullness and score.  If not, just return false.
	 * @param score
	 * @return boolean
	 */
	public boolean clean(int score) {if (this.fullness != this.capacity) { 
		
		this.fullness = this.fullness + Constants.FULLNESS_INC;
		this.score = this.score + score;
		return true;
	} //not sure if this code is correct but doing it this way to makeit easy possibly
	else {return false;} //is empty() possible?
	
	}
	/**empty the Vacuum of fullness
	 * 
	 * @return void 
	 * 
	 */
	public void empty() {this.fullness = Constants.EMPTY;}
	
	/**
	 * return the amount of points  by Vacuum 
	 * @return int 
	 */	
	public int getScore() {return this.score;}
	
	/** set vacuum.under with Sprite under.  
	 * 
	 * @param under
	 */
	public void setUnder(Sprite under) {this.under = under;}
	/**
	 * get the Sprite of the vacuum.under
	 * @param under
	 * @return Sprite
	 */
	public Sprite getUnder() {return this.under;}
	
	/** update vacuum's row and columns to row and column
	 * @param row, column
	 * @return void
	 */
	public void moveTo(int row, int column) {this.row = row; this.column = column;}
	/**check to see if Sprite other is the same in score, coordinates, what is underneath, fullness.
	 * @param other
	 * @return boolean
	 * 
	 */
	public boolean equals(Sprite other) {if (super.equals(other)){if (this.getScore() == ((Vacuum) other).getScore() && 
			this.fullness == ((Vacuum) other).fullness && this.getUnder().equals(((Vacuum) other).getUnder())) {return true;} 
	else{return false;}} else {return false;} }
	

}
