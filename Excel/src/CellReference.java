import java.util.TreeSet;

public class CellReference extends Position implements Element {
	private SpreadSheet sheet;
	
	public CellReference(String _column, int _row, SpreadSheet _sheet) {
		super(_column, _row);
		if(!_sheet.hasCell(_column, _row))
			throw new IllegalArgumentException("Cell " + _column + _row + " does not exist!");
		sheet = _sheet;
	}
	
	public CellReference(String _coordinate, SpreadSheet _sheet) {
		super(_coordinate);
		if(!_sheet.hasCell(super.column(), super.row()))
			throw new IllegalArgumentException("Cell " + super.column() + super.row() + " does not exist!");
		sheet = _sheet;
	}
	
	@Override
	public String value() {
		return sheet.get(column(), row()).value();
	}

	@Override
	public TreeSet<Position> using() {
		TreeSet<Position> toReturn = new TreeSet<Position>();
		toReturn.add(this);
		return toReturn;
	}

	@Override
	public TreeSet<Position> usedBy() {
		return new TreeSet<Position>();
	}
}