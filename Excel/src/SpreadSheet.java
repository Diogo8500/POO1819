import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Basic implementation of a spreadsheet. It contains a set of {@link Cell}s defined
 * by their {@link Position}s which
 * can hold various types of elements/values ({@link Element}) and comunicate between
 * themselves.
 * It supports the adding and removing of single cells and the removal of entire rows 
 * or columns at once.
 * Cells being dependent on are set the value of 0 if targeted for removal, being removed
 * definitely when no longer needed. Cells set the literal value of 0 by the user - 
 * or have the value 0 from the result of a function - will NOT be removed, even if the
 * function does not depend on other cells.
 * It is also possible to iterate over every cell in the sheet and see their contents.
 * 
 * @author Grupo I, Turma A, POO 2018
 *
 */
public class SpreadSheet implements Iterable<SpreadSheet.Cell> {
	
	private TreeMap<Position, Cell> sheetMap;
	private boolean forceRemoveFlag;
	
	/**
	 * Creates a new empty spreadsheet.
	 */
	public SpreadSheet() {
		sheetMap = new TreeMap<Position, Cell>();
		forceRemoveFlag = false;
	}
	
	//Add will return false if cell already exists
	private boolean add(String _column, int _row, Element _element) {
		if(_element instanceof Cell)
			throw new IllegalArgumentException("Invalid element!");
		Position toAdd = new Position(_column, _row);
		if(sheetMap.containsKey(toAdd))
			return false;
		sheetMap.put(toAdd, new Cell(_column, _row, _element));
		sheetMap.get(toAdd).using.addAll(_element.using());
		for(Position p : _element.using()) {
			sheetMap.get(p).usedBy.add(toAdd);
		}
		return true;
	}
	
	/**
	 * Returns the cell at _column, _row location.
	 * @param _column column of the cell
	 * @param _row row of the cell
	 * @return the {@link Cell} @ _column, _row, or null if the cell does not 
	 * exist.
	 */
	public Cell get(String _column, int _row) {
		Cell toReturn = sheetMap.get(new Position(_column, _row));
		return toReturn;
	}
	
	/**
	 * Sets a new cell at _column, _row location with the specified {@link Element}.
	 * If the cell does not exist, creates it. Else overwrites that cell content
	 * @param _column column of the cell
	 * @param _row row of the cell
	 * @param _element Element to add
	 * @return always true
	 */
	public boolean set(String _column, int _row, Element _element) { //Set will always return true
		Position toSet = new Position(_column, _row);
		if(!sheetMap.containsKey(toSet)) 
			return add(_column, _row, _element);
		TreeSet<Position> usedByActual = sheetMap.get(toSet).usedBy;
		forceRemoveFlag = true;
		remove(_column, _row);
		forceRemoveFlag = false;
		add(_column, _row, _element);
		sheetMap.get(toSet).usedBy = usedByActual;
		return true;
	}
	
	/**
	 * Removes the Cell at _column, _row location. If the cell is in use by some other cell
	 * it's value is set to 0, until no longer needed or overridden by the user.
	 * @param _column column of the cell to remove
	 * @param _row row of the cell to remove
	 * @return -1 if the cell does not exist, 1 if removed with success, 0 if in use so it's
	 * value was set to 0
	 */
	public int remove(String _column, int _row) {
		Position toRemovePosition = new Position(_column, _row);
		if(!sheetMap.containsKey(toRemovePosition))
			return -1;
		if(sheetMap.get(toRemovePosition).content instanceof Sum1)
			sheetMap.get(toRemovePosition).content.value();
		Iterator<Position> it = sheetMap.get(toRemovePosition).using.iterator();
		while(it.hasNext()) {
			Position actualPosition = it.next();
			Cell actualCell = sheetMap.get(actualPosition);
			actualCell.usedBy.remove(toRemovePosition);
			if(actualCell.failedRemoval && actualCell.usedBy.isEmpty()/* && actualCell.using.isEmpty()*/)
				sheetMap.remove(actualPosition);
			it.remove();	
		}
		if(sheetMap.get(toRemovePosition).usedBy.isEmpty() || forceRemoveFlag) {
			sheetMap.remove(toRemovePosition);
			return 1;
		}
		else {
			sheetMap.get(toRemovePosition).setContent(new Value("0"));
			sheetMap.get(toRemovePosition).failedRemoval = true;
			return 0;
		}	
	}
	
	/**
	 * Removes an entire column. All cells in use by other cells are set to 0
	 * as per {@link SpreadSheet#remove(String, int)} and therefore are not considered
	 * removed
	 * @param _column column to remove
	 * @return the number of removed cells
	 */
	public int remove(String _column) {
		int removed = 0;
		ArrayList<Position> toCheck = new ArrayList<Position>(sheetMap.keySet());
		for(Position p : toCheck) 
			if(p.column() == _column)
				removed += remove(_column, p.row());
		return removed;
	}
	
	/**
	 * Removes an entire row. All cells in use by other cells are set to 0
	 * as per {@link SpreadSheet#remove(String, int)} and therefore are not considered
	 * removed
	 * @param _row row to remove
	 * @return the number of removed cells
	 */
	public int remove(int _row) {
		int removed = 0;
		ArrayList<Position> toCheck = new ArrayList<Position>(sheetMap.keySet());
		for(Position p : toCheck) 
			if(p.row() == _row)
				removed += remove(p.column(), _row);
		return removed;
	}
	
	/**
	 * Checks whether a cell exists
	 * @param _column column of the cell
	 * @param _row row of the cell
	 * @return true if the cell exists, false otherwise
	 */
	public boolean hasCell(String _column, int _row) {
		return sheetMap.containsKey(new Position(_column, _row));
	}
	
	/**
	 * Returns an iterator over all the {@link Cell}s of this spreadsheet ordered by
	 * the natural order of {@link Position}
	 * @return an iterator of the cells of this spreadsheet
	 */
	@Override
	public Iterator<Cell> iterator() {
		return sheetMap.values().iterator();
	}
	
	/**
	 * Nested Class representing a cell of a spreadsheet. It is defined by a {@link Position},
	 * its content ({@link Element}), and respective dependencies. It's meant to be manipulated
	 * internally by its spreadsheet, containing only public methods to "peek" at it's contents.
	 * @author Grupo I, Turma A, POO 2018
	 *
	 */
	@SuppressWarnings("unlikely-arg-type")
	public class Cell /*implements Element*/ {
		
		private Position position;
		private Element content;
		private TreeSet<Position> usedBy, using;
		private boolean failedRemoval;
		
		/**
		 * Creates a new cell with _column, _row location with the specified {@link Element}.
		 * @param _column column of the cell
		 * @param _row row of the cell
		 * @param _content Element to add
		 */
		public Cell(String _column, int _row, Element _content){
			setPosition(_column, _row);
			setContent(_content);
			usedBy = new TreeSet<Position>();
			using = new TreeSet<Position>();
			failedRemoval = false;
		}
		
		private void setPosition(String _column, int _row) {
			position = new Position(_column, _row);
		}

		private void setContent(Element _content) {
			if(_content instanceof CellReference && !sheetMap.containsKey(_content))
				throw new IllegalArgumentException("Cell " + _content + " does not exist!");
			content = _content;
		}
		
		/**
		 * Returns the {@link Position} of this cell
		 * @return position of this cell
		 */
		public Position position() {
			return position;
		}
		
		/**
		 * Returns the content of this cell
		 * @return the content of this cell
		 */
		public Element content() {
			return content;
		}

		/**
		 * Returns the numeric value of this cell as a String
		 * @return the value of this cell as a string
		 */
		public String value() {
			if(content instanceof CellReference)
				return sheetMap.get(content).value();
			else
				return content.value();
		}
	
		/**
		 * Returns a {@link TreeSet} of the cells (positions) this cell is using
		 * to compute its value
		 * @return the cells this cell is using
		 */
		public TreeSet<Position> using() {
			return using;
		}
		
		/**
		 * Returns a {@link TreeSet} of the cells using this cell to compute their values
		 * @return the cells using this cell
		 */
		public TreeSet<Position> usedBy() {
			return usedBy;
		}
		
		/**
		 * Returns the position of this cell as a string
		 */
		@Override
		public String toString() {
			return position.toString();
		}
		
	}
}
