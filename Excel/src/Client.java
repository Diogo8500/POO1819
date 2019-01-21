import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Client {
	private static SpreadSheet excel = new SpreadSheet();
	
	public static void interpreter(String _input) throws Exception {
		String[] temp = _input.split(" ");
		ArrayList<String> tokens = new ArrayList<String>(temp.length);
		Collections.addAll(tokens, temp);
		switch(tokens.get(0)) {
		case "p*":
			printSheet(excel);
			System.out.println();
			break;
		case "d*":
			deleteOptions(tokens);
			break;
		default:
			cellOptions(tokens);
			break;
		}
	}
	
	public static void deleteOptions(List<String> _list) {
		if(_list.get(1).matches("^[0-9]+$"))
			deleteRow(_list.get(1));
		else if(_list.get(1).matches("^[A-Z]+$"))
			deleteColumn(_list.get(1));
		else
			deleteCell(_list.get(1));
	}
	
	public static void cellOptions(List<String> _list) throws Exception {
		if(_list.size() == 1)
			printCell(_list.get(0));
		else {
			Element value = getElement(_list.subList(1, _list.size()));
			Position p = new Position(_list.get(0));
			excel.set(p.column(), p.row(), value);
		}
	}


	public static void printSheet(SpreadSheet _sheet) {
		int currentRow = 0;
		for(SpreadSheet.Cell c : _sheet) 
			if(currentRow == 0) {
				System.out.print(c + " " + c.content() + " " + c.value());
				currentRow = c.position().row();
			}else {
				if(c.position().row() > currentRow) {
					System.out.println();
					currentRow = c.position().row();
				}else 
					System.out.print(" ");
				System.out.print(c + " " + c.content() + " " + c.value());
			}
	}
	
	public static void printCell(String _cell) {
		CellReference c = new CellReference(_cell, excel);
		SpreadSheet.Cell cell = excel.get(c.column(), c.row());
		System.out.println(cell + " " + cell.content() + " " + cell.value());
	}
	
	public static void deleteCell(String _cell) {
		CellReference c = new CellReference(_cell, excel);
		excel.remove(c.column(), c.row());
	}
	
	public static void deleteRow(String _row) {
		excel.remove(Integer.parseInt(_row));
	}
	
	public static void deleteColumn(String _column) {
		excel.remove(_column);
	}
	
	public static Element getElement(List<String> _expression) throws Exception {
		if(_expression.size() == 1)
			if(_expression.get(0).matches("^[A-Z]+[0-9]+$")) 
				return new CellReference(_expression.get(0), excel);
			else 
				return new Value(_expression.get(0));
		else if(_expression.size() == 2) 
			return getUnaryFunction(_expression);
		else
			return getBinaryFunction(_expression);
	}
	
	
	public static Function getBinaryFunction(List<String> _expression) throws Exception {
		String aux = _expression.get(0);
		String name = aux.subSequence(1, aux.length()).toString();
		int size = _expression.size();
		FunctionType functionToReturn = FunctionType.valueOf(name.concat("2"));
	
		if(size == 3)
			return (Function) functionToReturn.constructor().newInstance(getElement(_expression.subList(1, 2)), getElement(_expression.subList(2, 3)));
		if(_expression.get(size-2).contains("="))
			return (Function) functionToReturn.constructor().newInstance(getElement(_expression.subList(1, size-2)), getElement(_expression.subList(size-2, size)));
		if(_expression.get(size-3).contains("=")) {
			if(!_expression.get(size-4).contains("="))
				return (Function) functionToReturn.constructor().newInstance(getElement(_expression.subList(1, size-3)), getElement(_expression.subList(size-3, size)));
		}			
		return (Function) functionToReturn.constructor().newInstance(getElement(_expression.subList(1, size-1)), getElement(_expression.subList(size-1, size)));
	}

	public static Function getUnaryFunction(List<String> _expression) throws Exception {
		String aux = _expression.get(0);
		String name = aux.subSequence(1, aux.length()).toString();
		
		FunctionType functionToReturn = FunctionType.valueOf(name.concat("1"));
		
		return (Function) functionToReturn.constructor().newInstance(_expression.get(1), excel);
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()) {
			interpreter(in.nextLine());
		}
		in.close();	
	}
}