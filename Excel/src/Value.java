
public class Value implements Element {

	private static final long serialVersionUID = -909962329479825495L;
	private Number value;

	public Value(Number _val) {
		value = _val;
	}
	
	@Override
	public Number value() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

}
