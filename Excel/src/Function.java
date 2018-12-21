import java.util.ArrayList;
import java.util.TreeSet;

public abstract class Function implements Element {
	
	private ArrayList<Element> arguments;

	public void set(Element... _arguments) {
		arguments = new ArrayList<Element>();
		for(Element e : _arguments) 
			arguments.add(e);
	}
	
	public ArrayList<Element> arguments() {
		return arguments;
	}
	
	@Override
	public TreeSet<Position> using(){
		TreeSet<Position> toReturn = new TreeSet<Position>();
		for(Element e : arguments)
			toReturn.addAll(e.using());
		return toReturn;
	}
	
	@Override
	public TreeSet<Position> usedBy(){
		return new TreeSet<Position>();
	}
	
	@Override
	public abstract String value();
}
