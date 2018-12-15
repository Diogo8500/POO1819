import java.math.BigDecimal;
import java.util.Iterator;

public class Sum1 extends Function {

	private static final long serialVersionUID = -954297921599532437L;
	private String argument;
	private Sheet<Cell> sheet;
	
	public Sum1(String _arg, Sheet<Cell> _sheet) {
		argument = _arg;
		sheet = _sheet;
	}

	@Override
	public Number value() {
		BigDecimal result = new BigDecimal(0);
		if(isNumeric(argument)) {
			int row = Integer.parseInt(argument);
			Iterator<Cell> rowIterator = sheet.rowIterator(row);
			while(rowIterator.hasNext()) {
				Cell toAdd = rowIterator.next();
				result = result.add(new BigDecimal(toAdd.value().toString()));
			}
		}else {
			Iterator<Cell> columnIterator = sheet.columnIterator(argument);
			Cell toAdd;
			while(columnIterator.hasNext()) {
				toAdd = columnIterator.next();
				result = result.add(new BigDecimal(toAdd.value().toString()));
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "=SUM " + argument;
	}
	
	private boolean isNumeric(String _arg) {
		return !_arg.matches("^[A-Z]+$");
	}

}
