import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Abstract class implementing the basic concepts of a spreadsheet function.
 * This class aims to facilitate future function implementations
 * @author Grupo I, Turma A, POO 2018
 *
 */
public abstract class Function implements Element {
	
	private ArrayList<Element> arguments;

	protected void set(ArrayList<Element> _arguments) {
		arguments = new ArrayList<Element>(_arguments);
	}
	
	/**
	 * Returns the arguments of this function in the order they were set by this.set()
	 * as an {@link ArrayList}
	 * 
	 * @return the arguments of this function
	 */
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
