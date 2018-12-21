import java.util.TreeSet;

/**
 * Interface defining an element capable of being processed by a
 * {@link SpreadSheet}.
 * All methods have to be implemented but the {@link toString()} method which is optional.
 * The string returned by the {@link value()} method should NOT throw any exceptions
 * if passed to {@link Double#parseDouble(String)}.
 * @author Grupo I, Turma A, POO 2018
 *
 */
public interface Element {
	/**
	 * Method defining the numerical value of this element. The string returned by this method
	 * must be either a valid integer or floating point number.
	 * @return string containing the numeric value of this element
	 */
	String value();
	
	/**
	 * Method that returns the cell's {@link Position}s of a {@link SpreadSheet} this element is using
	 * to calculate its {@link value()}. It's possible for this method to return an empty {@link TreeSet},
	 * if it doesn't depend on any cells.
	 *  
	 * @return the cell's positions of a {@link SpreadSheet} this element is using
	 */
	TreeSet<Position> using();
	
	/**
	 * Method that returns the cell's {@link Position}s of a {@link SpreadSheet} this element
	 * is being used on. It's possible for this method to return an empty {@link TreeSet},
	 * if it not being used by any other cells.
	 * @return the cell's positions of a {@link SpreadSheet} this element is being used by
	 */
	TreeSet<Position> usedBy();
	
	/**
	 * Returns a visual text representation of this element. (Optional)
	 * @return a visual text representation of this element
	 */
	String toString();
}
