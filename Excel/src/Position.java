import java.util.Objects;

public class Position implements Comparable<Position> {
	
	private String column;
	private int row;
	
	public Position (String _column, int _row) {
		setColumn(_column);
		setRow(_row);
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
	
	public String column() {
		return column;
	}
	
	public int row() {
		return row;
	}
	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}
	
	@Override
	public boolean equals(Object _o) {
		return this.hashCode() == _o.hashCode();
	}
	
	@Override
	public int compareTo(Position _o) {
		if(row < _o.row) return -1;
		if(row == _o.row) {
			if(column.length() < _o.column.length()) return -1;
			if(column.length() == _o.column.length()) return column.compareTo(_o.column);
		}
		return 1;
	}
	
	@Override
	public String toString() {
		return column + row;
	}
	
}