import java.util.TreeSet;

public final class Value implements Element {
	private Number value;
	
	public Value(Number _value) {
		setValue(_value);
	}
	
	private void setValue(Number _value) {
		value = _value;
	}
	
	@Override
	public Number value() {
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