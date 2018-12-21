import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public final class Sum2 extends Function {

	public Sum2(Element _argument1, Element _argument2) {
		set(_argument1, _argument2);
	}
	
	private void set(Element _argument1, Element _argument2) {
		ArrayList<Element> toAdd = new ArrayList<Element>(2);
		Collections.addAll(toAdd, _argument1, _argument2);
		set(toAdd);
	}
	
	@Override
	public String value() {
		BigDecimal result = new BigDecimal(0);
		for(Element e : arguments())
			result = result.add(new BigDecimal(e.value()));
		return result.toString();
	}

	@Override
	public String toString() {
		String toReturn = "=SUM";
		for(Element e : arguments())
			toReturn = toReturn.concat(" " + e);
		return toReturn;
	}
}