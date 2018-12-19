import java.util.TreeSet;

public class CellReference extends Position implements Element {
	private SpreadSheet sheet;
	
	public CellReference(String _column, int _row, SpreadSheet _sheet) {
		super(_column, _row);
		sheet = _sheet;
	}
	
	@Override
	public Number value() {
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
