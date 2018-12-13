import java.util.ArrayList;

public class Node<T> {
	private T object;
	private ArrayList<Position> using, usedBy;
	
	public Node(T _object) {
		setObject(_object);
		using = new ArrayList<Position>();
		usedBy = new ArrayList<Position>();
	}
	
	public void setObject(T _object) {
		object = _object;
	}
	
	public T object() {
		return object;
	}
	
	public ArrayList<Position> using(){
		return using;
	}
	
	public ArrayList<Position> usedBy(){
		return usedBy;
	}
	
	public void addUsing(Position _location) {
		using.add(_location);
	}
	
	public void addUsedBy(Position _location) {
		usedBy.add(_location);
	}
}
