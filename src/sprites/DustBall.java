package sprites;

public class DustBall extends Dirt implements Moveable {
   
	
	/**initialize DustBall.
	 * 
	 * @param symbol
	 * @param row
	 * @param column
	 * @param value
	 */
	public DustBall(char symbol, int row, int column, int value) {
		super(symbol, row, column, value);
		// TODO Auto-generated constructor stub
	}
    
	
	/**return the Symbol of DustBall
	  * @return char
	  */
	public char getSymbol() {		
		return super.getSymbol();
		// TODO Auto-generated method stub
	}
	/**return the row of DustBall
	 * @return int 
	 */
	public int getRow() {return super.getRow();}
	/**return the column of DustBall
	 * @return int 
	 */
	 
	public int getColumn() {return super.getColumn();}

	/**return the str representation of DustBall
	 * @return String 
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
    /**
     * return the value of DustBall
     * 
     * @return int
     */
	public int getValue() {return super.getValue();}
	
	/**update the DustBall row to row, and column to column
	 * 
	 * @param row
	 * @param column
	 * @return void
	 */
	public void moveTo(int row, int column) {this.column=column; this.row=row;}
	
	

}
