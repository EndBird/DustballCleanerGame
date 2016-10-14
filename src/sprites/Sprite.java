package sprites;
/**
 * 
 * Sprite class is the parent class of all the different elements that will be seen in the game.  
 *
 */
public abstract class Sprite {
	protected char symbol;
	protected int row;
	protected int column;
	/**initialize Sprite
	 * 
	 * @param symbol
	 * @param row
	 * @param column
	 */
	public Sprite(char symbol, int row, int column) {
		this.row = row; 
	    this.column = column;
	    this.symbol = symbol;
	}
	/**return the Symbol of Sprite
	  * @return char
	  */
	public char getSymbol() {return this.symbol;}
	/**return the row of Sprite
	 * @return int 
	 */
	public int getRow() {return this.row;}
	/**return the column of Sprite
	 * @return int 
	 */
	public int getColumn() {return this.column;}
	/**return the str representation of Sprite
	 * @return String 
	 * 
	 */
	public String toString() {return String.valueOf(this.symbol);}
	
	/**check to see whether Sprite other is equal to this Sprite.  Equal means, same symbol, row, column
	 * Needed for comparing ArrayGrids of Sprites and checking if a list has a certain sprite.  
	 * @param other
	 * @return boolean
	 */
	public boolean equals(Sprite other) {if (this.getSymbol()==other.getSymbol() && this.getRow()==other.getRow() && this.getColumn()==other.getColumn()){
		return true;
	} else{return false;}}
	

}
