import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Sheet<T> implements Serializable, Iterable<T> {

	private static final long serialVersionUID = -7511537596728391794L;
	private HashMap<Position, Node<T>> sheet;

	public Sheet() {
		sheet = new HashMap<Position, Node<T>>();
	}
	
	public void addNode(Position _pos) {
		sheet.put(_pos, new Node<T>());
	}
	
	public void removeNode(Position _pos) {
		for(Position p : sheet.get(_pos).using) 
			sheet.get(p).usedBy.remove(_pos);
		sheet.remove(_pos);
	}
	
	public boolean hasNode(Position _pos) {
		return sheet.containsKey(_pos);
	}
	
	public boolean canRemoveNode(Position _pos) {
		return sheet.get(_pos).usedBy.isEmpty();
	}
	
	public void set(Position _pos, T _content) {
		sheet.get(_pos).content = _content;
	}
	
	public T get(Position _pos) {
		return sheet.get(_pos).content;
	}
	
	public Set<Position> positions(){
		return sheet.keySet();
	}
	
	public boolean isDependent(Position _this, Position _that) {
		return sheet.get(_this).using.contains(_that);
	}
	
	public void setDependency(Position _using, Position _usedBy) {
		sheet.get(_using).using.add(_usedBy);
		sheet.get(_usedBy).usedBy.add(_using);
		
	}
	
	public void removeDependency(Position _using, Position _usedBy) {
		sheet.get(_using).using.remove(_usedBy);
		sheet.get(_usedBy).usedBy.remove(_using);
	}
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {

			private int current = 0;
			private List<Position> cells = new ArrayList<Position>(sheet.keySet());
			
			@Override
			public boolean hasNext() {
				if(current == 0) cells.sort(null);	
				return current < cells.size();
			}

			@Override
			public T next() {
				return sheet.get(cells.get(current++)).content;
			}	
		};
		return it;
	}

	public Iterator<T> rowIterator(int row) {
		
		Iterator<T> it = new Iterator<T>() {

			private int current = 0;
			private List<Position> cells = new ArrayList<Position>(sheet.keySet());
			
			@Override
			public boolean hasNext() {
				if(current == 0) 
					for(int i=0; i<cells.size(); i++) 
						if(cells.get(i).row() != row) cells.remove(i--);
					cells.sort(null);	
				return current < cells.size();
			}

			@Override
			public T next() {
				return sheet.get(cells.get(current++)).content;
			}	
		};
		return it;
	}

	public Iterator<T> columnIterator(String column) {
		Iterator<T> it = new Iterator<T>() {

			private int current = 0;
			private List<Position> cells = new ArrayList<Position>(sheet.keySet());
			
			@Override
			public boolean hasNext() {
				if(current == 0) 
					for(int i=0; i<cells.size(); i++) 
						if(!cells.get(i).column().equals(column)) cells.remove(i--);
					cells.sort(null);	
				return current < cells.size();
			}

			@Override
			public T next() {
				return sheet.get(cells.get(current++)).content;
			}	
		};
		return it;
	}
	
	@SuppressWarnings("hiding")
	private static class Node<T> {
		T content;
		Set<Position> using, usedBy;
		
		Node() {
			using = new HashSet<Position>();
			usedBy = new HashSet<Position>();
		}
	}
}
