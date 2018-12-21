import java.util.TreeSet;

/**
 * Subclass of {@link Position} representing a reference to other cells
 * in a {@link SpreadSheet}. Implements the {@link Element} interface so it can be target
 * of other spreadsheet functions and held by spreadsheet cells.
 * @author Grupo I, Turma A, POO 2018
 *
 */
public class CellReference extends Position implements Element {
	private SpreadSheet sheet;
	
	/**
	 * Creates a new CellReference to an existing {@link SpreadSheet} cell
	 * @param _column column of the referenced cell
	 * @param _row row of the referenced cell 
	 * @param _sheet the sheet containing the referenced cell
	 * @throws IllegalArgumentException if the cell does not exist on the spreadsheet
	 */
	public CellReference(String _column, int _row, SpreadSheet _sheet) {
		super(_column, _row);
		if(!_sheet.hasCell(_column, _row))
			throw new IllegalArgumentException("Cell " + _column + _row + " does not exist!");
		sheet = _sheet;
	}
	
	/**
	 * Creates a new CellReference to an existing {@link SpreadSheet} cell
	 * @param _coordinate Formated string with the referenced cell location 
	 * @param _sheet the sheet containing the referenced cell
	 * @throws IllegalArgumentException if the cell does not exist on the spreadsheet
	 */
	public CellReference(String _coordinate, SpreadSheet _sheet) {
		super(_coordinate);
		if(!_sheet.hasCell(column(), row()))
			throw new IllegalArgumentException("Cell " + column() + row() + " does not exist!");
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