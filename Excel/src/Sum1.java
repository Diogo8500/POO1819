import java.math.BigDecimal;
import java.util.ArrayList;

public final class Sum1 extends Function {
	
		private SpreadSheet sheet;
		private String name;

		public Sum1(String _argument, SpreadSheet _sheet) {
			sheet = _sheet;
			set(_argument);
		}
		
		public void set(String _argument) {
			if(_argument.matches("^[A-Z]+$")) 
				super.set(columnReferences(_argument));
			else if(_argument.matches("^[1-9]+$"))
				super.set(rowReferences(Integer.parseInt(_argument)));
			else 
				throw new IllegalArgumentException("Argument must either be a row or column!");
			name = _argument;
		}
		
		private ArrayList<Element> rowReferences(int _argument) {
			ArrayList<Element> aux = new ArrayList<Element>();
			for(SpreadSheet.Cell c : sheet) 
				if(c.position().row() == _argument) 
					aux.add(new CellReference(c.position().column(), _argument, sheet));
			return aux;
		}

		private ArrayList<Element> columnReferences(String _argument) {
			ArrayList<Element> aux = new ArrayList<Element>();
			for(SpreadSheet.Cell c : sheet) 
				if(c.position().column() == _argument) 
					aux.add(new CellReference(_argument, c.position().row(), sheet));
			return aux;
		}

		@Override
		public String value() {
			BigDecimal toReturn = new BigDecimal(0);
			for(Element e : arguments())
				toReturn = toReturn.add(new BigDecimal(e.value()));
			return toReturn.toString();
		}
		
		@Override
		public String toString() {
			return "=SUM " + name;
		}		
	}