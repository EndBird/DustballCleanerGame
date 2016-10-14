package game;
/**
 * An interface to be implemented by ArrayGrid
 *
 *
 * @param <T>
 */
public interface Grid<T> { 
	void setCell(int row,int column, T item);
	T getCell(int row,int column);
	int getNumRows();
	int getNumColumns();
	boolean equals(Object other);
	String toString();

}
