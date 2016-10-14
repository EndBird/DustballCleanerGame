package sprites;

public class Wall extends Sprite{
    
	
	/**initialize a Wall
	 * 
	 * @param symbol
	 * @param row
	 * @param column
	 * 
	 */
	public Wall(char symbol, int row, int column) { 		
		super(symbol, row, column);
		
		// TODO Auto-generated constructor stub
	}

	/**return the Symbol of Wall
	  * @return char
	  */
	public char getSymbol() {		
		return super.getSymbol();
		// TODO Auto-generated method stub
	}
	/**return the row of Wall
	 * @return int 
	 */
	public int getRow() {return super.getRow();}
	/**return the column of Wall
	 * @return int 
	 */
	 
	public int getColumn() {return super.getColumn();}

	/**return the str representation of Wall
	 * @return String 
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
