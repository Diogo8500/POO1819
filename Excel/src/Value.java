import java.util.TreeSet;

/**
 * Wrapper class of a numeric value. Implements the Element interface so it can
 * be target of spreadsheet functions and held by spreadsheet cells.
 * @author Grupo I, Turma A, POO 2018
 *
 */
public final class Value implements Element {
	private String value;
	
	/**
	 * Creates a new Value from a string. String must contain a valid integer
	 * or double number, i.e, "1", "23.5" 
	 * @throws NullPointerException - if the string is null
	 * @throws NumberFormatException - if the string does not contain a parsable number.
	 * @param _value String with the representation of the number
	 */
	public Value(String _value) {
		setValue(_value);
	}
	
	private void setValue(String _value) {
		Double.parseDouble(_value);
		value = _value;
	}
	
	@Override
	public String value() {
		return value;
	}
	
	@Override
	public TreeSet<Position> using() {
		return new TreeSet<Position>();
	}
	@Override
	public TreeSet<Position> usedBy() {
		return new TreeSet<Position>();
	}
	@Override
	public String toString() {
		return value.toString();
	}
}