import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing a {@link SpreadSheet} function where the 2 arguments
 * are added together. Implements the Element interface so it can be target
 * of other spreadsheet functions and held by spreadsheet cells.
 * @author Grupo I, Turma A, POO 2018
 *
 */
public final class Sum2 extends Function {

	/**
	 * Creates a new Sum2 function with the given 2 arguments. The arguments can
	 * be any objects that implement the {@link Element} interface
	 * @param _argument1 first argument
	 * @param _argument2 second argument
	 */
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