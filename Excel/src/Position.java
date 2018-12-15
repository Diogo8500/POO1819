
public class Position implements Comparable<Position> {
	private String column;
	private int row;
	
	public Position(String _column, int _row) {
		setColumn(_column);
		setRow(_row);
	}
	
	public void setColumn(String _column) {
		if(!_column.matches("^[A-Z]+$"))
			throw new IllegalArgumentException();
		column = _column;
	}
	
	public void setRow(int _row) {
		if(_row < 1)
			throw new IllegalArgumentException();
		row = _row;
	}
	
	public String column() {
		return column;
	}
	
	public int row() {
		return row;
	}
	
	@Override
	public String toString() {
		return column + row;
	}
	
	@Override
	public boolean equals(Object _o) {
		return column.equals(((Position)_o).column()) && row == ((Position)_o).row();
	}

	@Override
	public int compareTo(Position _o) {
		if(row < _o.row()) return -1;
		if(row == _o.row()) {
			if(column.length() == _o.column().length()) return column.compareTo(_o.column());
			if(column.length() < _o.column().length()) return -1;
		}
		return 1;
	}
	
	
}
