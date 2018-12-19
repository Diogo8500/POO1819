import java.math.BigDecimal;
import java.math.MathContext;
import java.util.TreeSet;

public final class Sum1 extends Function {
	
		private SpreadSheet sheet;

		public Sum1(String _argument, SpreadSheet _sheet) {
			set(_argument);
			sheet = _sheet;
		}
		
		public void set(String _argument) {
			if(!_argument.matches("^[A-Z]+$") && !_argument.matches("^[1-9]+$"))
				throw new IllegalArgumentException("Argument must either be a row or column!");
			super.set(_argument);
		}
		
		@Override
		public Number value() {
			String argument = (String)arguments().get(0);
			if(argument.matches("^[A-Z]+$"))
				return calculateColumn(argument);
			return calculateRow(Integer.parseInt(argument));
		}
		
		@Override
		public String toString() {
			return "=SUM " + arguments().get(0);
		}
		
		private Number calculateColumn(String _column) {
			BigDecimal result = new BigDecimal(0);
			for(SpreadSheet.Cell c : sheet) 
				if(c.position().column() == _column) {
					BigDecimal toAdd = new BigDecimal(c.value().toString());
					result = result.add(toAdd, new MathContext(Math.max(result.precision(), toAdd.precision())));
				}
			return result;
		}
		
		private Number calculateRow(int _row) {
			BigDecimal result = new BigDecimal(0);
			for(SpreadSheet.Cell c : sheet) 
				if(c.position().row() == _row) {
					BigDecimal toAdd = new BigDecimal(c.value().toString());
					result = result.add(toAdd, new MathContext(Math.max(result.precision(), toAdd.precision())));
				}
			return result;
		}

		@Override
		public TreeSet<Position> using() {
			String argument = (String)arguments().get(0);
			if(argument.matches("^[A-Z]+$"))
				return columnDependencies(argument);
			return rowDependencies(Integer.parseInt(argument));
		}
		
		private TreeSet<Position> columnDependencies(String _argument){
			TreeSet<Position> toReturn = new TreeSet<Position>();
			for(SpreadSheet.Cell c : sheet)
				if(c.position().column() == _argument)
					toReturn.add(c.position());
			return toReturn;
		}
		
		private TreeSet<Position> rowDependencies(int _argument){
			TreeSet<Position> toReturn = new TreeSet<Position>();
			for(SpreadSheet.Cell c : sheet)
				if(c.position().row() == _argument)
					toReturn.add(c.position());
			return toReturn;
		}

		@Override
		public TreeSet<Position> usedBy() {
			return new TreeSet<Position>();
		}
	}