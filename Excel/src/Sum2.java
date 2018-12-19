import java.math.BigDecimal;
import java.util.TreeSet;

public final class Sum2 extends Function {

	public Sum2(Element _argument1, Element _argument2) {
		set(_argument1, _argument2);
	}
	
	private void set(Element _argument1, Element _argument2) {
		if(_argument1 instanceof SpreadSheet.Cell || _argument2 instanceof SpreadSheet.Cell)
			throw new IllegalArgumentException();
		super.set(_argument1, _argument2);
	}
	
	@Override
	public String value() {
		BigDecimal arg1 = new BigDecimal(((Element)arguments().get(0)).value());
		BigDecimal arg2 = new BigDecimal(((Element)arguments().get(1)).value());
		return arg1.add(arg2).toString();
	}

	@Override
	public TreeSet<Position> using() {
		TreeSet<Position> toReturn = new TreeSet<Position>();
		toReturn.addAll(((Element)arguments().get(0)).using());
		toReturn.addAll(((Element)arguments().get(1)).using());
		return toReturn;
	}

	@Override
	public TreeSet<Position> usedBy() {
		return new TreeSet<Position>();
	}

	@Override
	public String toString() {
		return "=SUM " + ((Element)arguments().get(0)) + " " + ((Element)arguments().get(1));
	}
}