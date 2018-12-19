import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class SpreadSheet implements Iterable<SpreadSheet.Cell> {
	
	private TreeMap<Position, Cell> sheetMap;
	private boolean forceRemoveFlag;
	
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
	
	public Cell get(String _column, int _row) {
		Cell toReturn = sheetMap.get(new Position(_column, _row));
		if(toReturn == null)
			throw new IllegalArgumentException("Cell " + _column + _row + " does not exist!");
		return toReturn;
	}
	
	//Set will always return true
	public boolean set(String _column, int _row, Element _element) {
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
	
	public int remove(String _column, int _row) {
		Position toRemovePosition = new Position(_column, _row);
		if(!sheetMap.containsKey(toRemovePosition))
			return -1;	
		Iterator<Position> it = sheetMap.get(toRemovePosition).using.iterator();
		while(it.hasNext()) {
			Position actualPosition = it.next();
			Cell actualCell = sheetMap.get(actualPosition);
			actualCell.usedBy.remove(toRemovePosition);
			if(actualCell.value().doubleValue() == 0 && actualCell.usedBy.isEmpty() && actualCell.using.isEmpty())
				sheetMap.remove(actualPosition);
			it.remove();	
		}
		if(sheetMap.get(toRemovePosition).usedBy.isEmpty() || forceRemoveFlag) {
			sheetMap.remove(toRemovePosition);
			return 1;
		}
		else {
			sheetMap.get(toRemovePosition).setContent(new Value(0));
			return 0;
		}	
	}
	
	public int remove(String _column) {
		int removed = 0;
		ArrayList<Position> toCheck = new ArrayList<Position>(sheetMap.keySet());
		for(Position p : toCheck) 
			if(p.column() == _column)
				removed += remove(_column, p.row());
		return removed;
	}
	
	public int remove(int _row) {
		int removed = 0;
		ArrayList<Position> toCheck = new ArrayList<Position>(sheetMap.keySet());
		for(Position p : toCheck) 
			if(p.row() == _row)
				removed += remove(p.column(), _row);
		return removed;
	}
	
	@Override
	public Iterator<Cell> iterator() {
		return sheetMap.values().iterator();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public class Cell implements Element {
		
		private Position position;
		private Element content;
		private TreeSet<Position> usedBy, using;
		
		public Cell(String _column, int _row, Element _content){
			setPosition(_column, _row);
			setContent(_content);
			usedBy = new TreeSet<Position>();
			using = new TreeSet<Position>();
		}
		
		public void setPosition(String _column, int _row) {
			position = new Position(_column, _row);
		}

		public void setContent(Element _content) {
			if(_content instanceof CellReference && !sheetMap.containsKey(_content))
				throw new IllegalArgumentException("Cell " + _content + " does not exist!");
			if(_content instanceof Cell)
				throw new IllegalArgumentException();
			content = _content;
		}
		
		public Position position() {
			return position;
		}
		
		public Element content() {
			return content;
		}

		@Override
		public Number value() {
			if(content instanceof CellReference)
				return sheetMap.get(content).value();
			else
				return content.value();
		}
	
		@Override
		public TreeSet<Position> using() {
			return using;
		}
		
		@Override
		public TreeSet<Position> usedBy() {
			return usedBy;
		}
		
		@Override
		public String toString() {
			return position.toString();
		}
		
	}
}
