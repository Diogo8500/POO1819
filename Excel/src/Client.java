import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Client {
	private static SpreadSheet excel = new SpreadSheet();
	
	public static void interpreter(String _input) {
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
	
	public static void cellOptions(List<String> _list) {
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
	
	public static Element getElement(List<String> _expression) {
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
	
	
	public static Function getBinaryFunction(List<String> _expression) {
		if(_expression.get(0).equals("=SUM")) {
			return getSum2(_expression.subList(1, _expression.size()));
		}
		else 
			throw new IllegalArgumentException("Unary function " + _expression.get(0) + " does not exist!");
	}

	public static Sum2 getSum2(List<String> _arguments) {
		if(_arguments.size() == 2)
			return new Sum2(getElement(_arguments.subList(0, 1)), getElement(_arguments.subList(1, 2)));
		if(_arguments.size() == 3)
			if(_arguments.get(0).contains("="))
				return new Sum2(getElement(_arguments.subList(0, 2)), getElement(_arguments.subList(2, 3)));
			else
				return new Sum2(getElement(_arguments.subList(0, 1)), getElement(_arguments.subList(1, 3)));
		int lastSumIndex = 1;
		for(int i=_arguments.size()-1; ; i--)
			if(!_arguments.get(i).contains("=")) lastSumIndex++; else break;
		
		if(lastSumIndex > 3)
			return new Sum2(getElement(_arguments.subList(0, _arguments.size()-1)), getElement(_arguments.subList(_arguments.size()-1, _arguments.size()))); 
		return new Sum2(getElement(_arguments.subList(0, _arguments.size() - lastSumIndex)), getElement(_arguments.subList(_arguments.size() - lastSumIndex, _arguments.size())));  
	}

	public static Function getUnaryFunction(List<String> _expression) {
		if(_expression.get(0).equals("=SUM"))
			return getSum1(_expression.get(1));
		else 
			throw new IllegalArgumentException("Unary function " + _expression.get(0) + " does not exist!");
	}
	
	public static Sum1 getSum1(String _argument) {
		return new Sum1(_argument, excel);
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()) {
			interpreter(in.nextLine());
		}
		in.close();	
	}
}