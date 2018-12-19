import java.util.ArrayList;
import java.util.TreeSet;

public abstract class Function implements Element {
	
	private ArrayList<Object> arguments = new ArrayList<Object>(1);
	
	public void set(Object... _arguments) {
		for(Object o : _arguments)
			arguments.add(o);
	}
	
	public ArrayList<Object> arguments() {
		return arguments;
	}
	
	@Override
	public abstract Number value();
	
	@Override
	public abstract TreeSet<Position> using();	
	
	@Override
	public abstract TreeSet<Position> usedBy();

}
