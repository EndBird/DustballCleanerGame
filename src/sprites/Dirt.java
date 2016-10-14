package sprites;

public class Dirt extends Sprite {
	protected int value;
	public Dirt(char symbol, int row, int column, int value) {
		super(symbol, row, column);
		this.value = value;
		
		// TODO Auto-generated constructor stub
	}
	
	
	/**return the Symbol of Dirt
	  * @return char
	  */
	public char getSymbol() {		
		return super.getSymbol();
		// TODO Auto-generated method stub
	}
	/**return the row of Dirt
	 * @return int 
	 */
	public int getRow() {return super.getRow();}
	/**return the column of Dirt
	 * @return int 
	 */
	 
	public int getColumn() {return super.getColumn();}

	/**return the str representation of Dirt
	 * @return String 
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	/**
     * return the value of Dirt
     * 
     * @return int
     */

	public int getValue() {return this.value;}

	
}
