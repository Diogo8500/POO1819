import java.util.TreeSet;

public final class Value implements Element {
	private String value;
	
	public Value(String _value) {
		setValue(_value);
	}
	
	private void setValue(String _value) {
		Double.parseDouble(_value);
		value = _value;
	}
	
	@Override
	public String value() {
		return value;
	}
	
	@Override
	public TreeSet<Position> using() {
		return new TreeSet<Position>();
	}
	@Override
	public TreeSet<Position> usedBy() {
		return new TreeSet<Position>();
	}
	@Override
	public String toString() {
		return value.toString();
	}

	

	
}