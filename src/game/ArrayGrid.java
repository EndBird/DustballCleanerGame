package game;


import sprites.Sprite;

/**A class to implement Grid<T>
 * 
 * 
 *
 * 
 */

public class ArrayGrid<T> implements Grid<T> {
	
	 private T[][] arraygrid;
	private int numRows;
	private int numColumns;
	
    /**
     * set up ArrayGrid
     * @param numRows
     * @param numColumns
     */
	public ArrayGrid(int numRows,int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.arraygrid = (T[][]) new Object[numRows][numColumns];
		
				
	}
	 

	/**
	 * set cell of ArrayGrid at row row and column column with item T
	 * @param row, column, item
	 */
	public void setCell(int row, int column, T item) { 
		this.arraygrid[row][column] = item;	
		
	}

	@Override
	/**return the thing of type T at cell row row, column column
	 * @param row, column
	 * @return T
	 * 
	 */
	public T getCell(int row, int column) {
		// TODO Auto-generated method stub
		return this.arraygrid[row][column];
	}

	@Override
	/**
	 * return the number of rows that this ArrayGrid has
	 * @return int
	 */
	public int getNumRows() {
		// TODO Auto-generated method stub
		return this.numRows;
	}

	/**
	 * return the number of columns that this ArrayGrid has
	 * @return int
	 */
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return this.numColumns;
	}	
	/**check to see whether object other is the same as this ArrayGrid
	 * same means it has the same dimension and contents at the respective cells
	 * @return boolean
	 * 
	 */
	public boolean equals(Object other) {
		
		if (other instanceof ArrayGrid) { if (((ArrayGrid) other).getNumColumns() == this.getNumColumns() 
		&& ((ArrayGrid) other).getNumRows() == this.getNumRows()){ 
			int i = 0; int j = 0; int k=this.numRows; int l = this.numColumns;
			// if the other and this are both grid of sprites, use the sprite equals method. 
			for (i=0; i<k;i++) {for (j=0;j<l;j++) {if (this.getCell(i,j) instanceof Sprite && 
					((ArrayGrid) other).getCell(i,j) instanceof Sprite ){
				 return this.getCell(i,j).equals(((ArrayGrid) other).getCell(i,j));
			} 
			if (this.getCell(i,j) != 
			 ((ArrayGrid) other).getCell(i,j)) { return false;}}}
			return true;
			
			
		} else {return false;}
		
		}
		
		else {return false;}
		
	
	}		
	
	/**return the str representation of ArrayGrid
	 * @return String
	 * 
	 */
	public String toString() {
		String board = "\n";
		int i = 0; int j = 0; int k=this.numRows; int l = this.numColumns;
		for (i=0; i<k;i++) {for (j=0;j<l;j++) {board = board + String.valueOf(this.arraygrid[i][j]);
		if (j+1 == l){board = board + "\n";}}
		}
		
		return board;}

}
