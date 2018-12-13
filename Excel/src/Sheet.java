import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class Sheet implements Serializable, Iterable<Cell> {

	private static final long serialVersionUID = -7511537596728391794L;
	private HashMap<String, HashMap<Integer, Node<Cell>>> sheet;

	public Sheet() {
		sheet = new HashMap<String, HashMap<Integer, Node<Cell>>>();
	}
	
	public void addNode(Position _pos, Cell _cell) {
		try {
			if(sheet.get(_pos.column()).containsKey(_pos.row()))
				throw new IllegalArgumentException();
		}catch(NullPointerException e) {
			sheet.put(_pos.column(), new HashMap<Integer, Node<Cell>>());
		}
		sheet.get(_pos.column()).put(_pos.row(), new Node<Cell>(_cell));
	}
	
	public void removeNode(Position _pos) {
		if(!sheet.containsKey(_pos.column())) 
			throw new IllegalArgumentException();
		if(!sheet.get(_pos.column()).containsKey(_pos.row()))
			throw new IllegalArgumentException();
		
	}
	
	@Override
	public Iterator<Cell> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
