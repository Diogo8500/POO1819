/**
 * Class implementing a position/location on a regular spreadsheet.
 * Columns are represented by a letter or an array of letters (A, AA, AB, ...)
 * Rows are represented by a natural positive number (1, 2, 34, ...)
 * @author Grupo I, Turma A, POO 2018
 *
 */
public class Position implements Comparable<Position> {
	
	private String column;
	private int row;
	
	/**
	 * Creates a new Position with _column as the column and
	 * _row as the row.
	 * @param _column Column
	 * @param _row Row
	 */
	public Position(String _column, int _row) {
		setColumn(_column);
		setRow(_row);
	}
	
	/**
	 * Creates a new Position using a string formated as "[A-Z][0-9]",
	 * where [A-Z] is the column and [0-9] is the row.
	 * @param _coordinate Position string
	 */
	public Position(String _coordinate) {
		if(!_coordinate.matches("^[A-Z]+[0-9]+$")) 
			throw new IllegalArgumentException("Wrong formating: " + _coordinate + "!");
		String _column = "";
		int _row = 0;
		for(int i=0; i<_coordinate.length(); i++) {
			if(!Character.isAlphabetic(_coordinate.charAt(i))) {
				_column = _coordinate.substring(0, i);
				_row = Integer.parseInt(_coordinate.substring(i));
				break;
			}
		}
		column = _column;
		row = _row;
	}
	
	private void setColumn(String _column) {
		if(!_column.matches("^[A-Z]+$"))
			throw new IllegalArgumentException("Column must have 'A...Z' format!");
		column = _column;
	}
	
	private void setRow(int _row) {
		if(_row < 1)
			throw new IllegalArgumentException("Row must be greater than 0!");
		row = _row;
	}
	
	/**
	 * Returns the column of this Position.
	 * @return Column
	 */
	public String column() {
		return column;
	}
	
	/**
	 * Returns the row of this Position.
	 * @return Row
	 */
	public int row() {
		return row;
	}
	
	/*@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}
	
	@Override
	public boolean equals(Object _o) {
		return this.hashCode() == _o.hashCode();
	}*/
	
	/**
	 * Compares 2 positions like so:
	 * Lower row smaller; If row is equal, lower column smaller.
	 * If both row and column are the same, the 2 positions are deemed equal.
	 * @return -1 if smaller, 0 if equal, 1 if greater
	 */
	@Override
	public int compareTo(Position _o) {
		if(row < _o.row) return -1;
		if(row == _o.row) {
			if(column.length() < _o.column.length()) return -1;
			if(column.length() == _o.column.length()) return column.compareTo(_o.column);
		}
		return 1;
	}
	
	/**
	 * Returns a string representation of this Position, i.e A1
	 * @return column|row
	 */
	@Override
	public String toString() {
		return column + row;
	}	
}