import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class Div2 extends Function {

	public Div2(Element _argument1, Element _argument2) {
		set(_argument1, _argument2);
	}
	
	private void set(Element _argument1, Element _argument2) {
		ArrayList<Element> toAdd = new ArrayList<Element>(2);
		Collections.addAll(toAdd, _argument1, _argument2);
		set(toAdd);
	}
	
	@Override
	public String value() {
		BigDecimal result = new BigDecimal(arguments().get(0).value());
		return result.divide(new BigDecimal(arguments().get(1).value()), RoundingMode.HALF_UP).toString();
	}
	
	@Override
	public String toString() {
		String toReturn = "=DIV";
		for(Element e : arguments())
			toReturn = toReturn.concat(" " + e);
		return toReturn;
	}

}
