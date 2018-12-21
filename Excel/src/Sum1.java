import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Class representing a {@link SpreadSheet} function where the argument's column
 * or row are added together. Implements the Element interface so it can be target
 * of other spreadsheet functions and held by spreadsheet cells.
 * @author Grupo I, Turma A, POO 2018
 *
 */
public final class Sum1 extends Function {
	
		private SpreadSheet sheet;
		private String name;

		/**
		 * Creates a new Sum1 function with the given argument. The argument MUST
		 * either be a column letter(s) or a row number.
		 * @param _argument Column or row
		 * @param _sheet the spreadsheet containing the cells to add
		 * @throws IllegalArgumentException if the argument is invalid
		 */
		public Sum1(String _argument, SpreadSheet _sheet) {
			sheet = _sheet;
			set(_argument);
		}
		
		private void set(String _argument) {
			if(_argument.matches("^[A-Z]+$")) 
				set(columnReferences(_argument));
			else if(_argument.matches("^[1-9]+$"))
				set(rowReferences(Integer.parseInt(_argument)));
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
				if(c.position().column().equals(_argument)) 
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