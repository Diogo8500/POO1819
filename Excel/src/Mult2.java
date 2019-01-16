import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class Mult2 extends Function {

	public Mult2(Element _argument1, Element _argument2) {
		set(_argument1, _argument2);
	}
	
	private void set(Element _argument1, Element _argument2) {
		ArrayList<Element> toAdd = new ArrayList<Element>(2);
		Collections.addAll(toAdd, _argument1, _argument2);
		set(toAdd);
	}
	
	@Override
	public String value() {
		BigDecimal result = new BigDecimal(1);
		for(Element e : arguments())
			result = result.multiply(new BigDecimal(e.value()));
		return result.toString();
	}
	
	@Override
	public String toString() {
		String toReturn = "=MULT";
		for(Element e : arguments())
			toReturn = toReturn.concat(" " + e);
		return toReturn;
	}
}
