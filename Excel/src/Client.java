import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Client {
	private static SpreadSheet excel = new SpreadSheet();
	private static int sumInRow = 0;
	
	public static void interpreter(String _input) {
		String[] temp = _input.split(" ");
		ArrayList<String> tokens = new ArrayList<String>(temp.length);
		Collections.addAll(tokens, temp);
		switch(tokens.get(0)) {
		case "p*":
			printSheet(excel);
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
			Element value = null;
			try {
				value = getElement(_list.subList(1, _list.size()));
			} catch (Exception e) {
				System.out.print("Somthing went wrong...");
				e.printStackTrace();
			}
			Position p = new Position(_list.get(0));
			excel.set(p.column(), p.row(), value);
		}
	}


	public static void printSheet(SpreadSheet _sheet) {
		int currentRow = 0;
		for(SpreadSheet.Cell c : _sheet) {
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
		try {
			if(_expression.size() == 1) {
				sumInRow = 0;
				if(_expression.get(0).matches("^[A-Z]+[1-9]+$")) 
					return new CellReference(_expression.get(0), excel);
				else 
					return new Value(_expression.get(0));
			}
			else if(_expression.size() == 2) 
				return getUnaryFunction(_expression);
			else
				return getBinaryFunction(_expression);
		}catch (Exception _e) {
			throw _e;
		}
	}
	
	
	public static Function getBinaryFunction(List<String> _expression) throws Exception {
		sumInRow++;
		if(_expression.get(0).equals("=SUM"))
			return getSum2(_expression.subList(1, _expression.size()));
		else 
			throw new IllegalArgumentException("Unary function " + _expression.get(0) + " does not exist!");
	}

	
	
	//BICHO DE 7 CABECAS
	public static Sum2 getSum2(List<String> _arguments) throws Exception {
		try {
			if(_arguments.size() == 2) {
				return new Sum2(getElement(_arguments.subList(0, 1)), getElement(_arguments.subList(1, 2)));
			}else /*if(_arguments.size() == 3)*/ {
				Scanner sc = new Scanner(_arguments.get(0));
				if(_arguments.get(0).matches("^[A-Z]+[1-9]+$") || sc.hasNextDouble()) {
					sc.close();
					return new Sum2(getElement(_arguments.subList(0, 1)), getElement(_arguments.subList(1, _arguments.size())));
				}else {
					sc.close();
					int count = 0;
					for(String s : _arguments.subList(1, _arguments.size())) {
						if(s.startsWith("=")) break;
						count++;
					}
					if(++sumInRow  >= count && count != 0) {
						return new Sum2(getElement(_arguments.subList(0, 2)), getElement(_arguments.subList(2, _arguments.size())));
					}else {
						return new Sum2(getElement(_arguments.subList(0, 3)), getElement(_arguments.subList(3, _arguments.size())));
					}
				}
			}
		}catch (Exception _e) {
			throw _e;
		}
	}

	public static Function getUnaryFunction(List<String> _expression) throws Exception {
		try {
			if(_expression.get(0).equals("=SUM"))
				return getSum1(_expression.get(1));
			else 
				throw new IllegalArgumentException("Unary function " + _expression.get(0) + " does not exist!");
		}catch (Exception _e) {
			throw _e;
		}
	}
	
	public static Sum1 getSum1(String _argument) throws Exception {
		try {
			return new Sum1(_argument, excel);
		} catch (Exception _e) {
			throw _e;
		}
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()) {
			interpreter(in.nextLine());
		}
		in.close();
		
		/*excel.set("A", 1, new Sum2(new Value("1"), new Value("2")));
		excel.set("A", 2, new Sum2(new CellReference("A",1, excel), new Value("2")));
		excel.set("C", 1, new Sum2(new CellReference("A",1, excel), new CellReference("A",2, excel)));
		excel.set("A", 1, new Sum2(new Value("1"), new Value("2")));
		excel.set("A", 2, new Sum2(new CellReference("A",1, excel), new Value("2")));
		excel.set("C", 1, new Sum2(new CellReference("A",1, excel), new CellReference("A",2, excel)));
		excel.remove(2);
		excel.set("A", 1, new Sum2(new Value("1.0"), new Value("2.0")));
		excel.set("A", 2, new Sum2(new CellReference("A",1, excel), new Value("3.0")));
		excel.set("A", 3, new Sum2(new CellReference("A",1, excel), new CellReference("A",2, excel)));
		excel.set("A", 4, new Sum2(new Sum2(new Value("1.0"), new CellReference("A",1, excel)), new Sum2(new CellReference("A",2, excel), new CellReference("A",3, excel))));
		excel.set("A", 1, new Value("1"));
		excel.set("IC", 1, new Value("1"));
		excel.set("B", 4, new CellReference("IC",1,excel));
		excel.set("N", 23, new Value("1"));
		excel.set("A", 1, new Value("1"));
		excel.set("C", 3, new Value("2.1"));
		excel.set("AG", 1, new Value("1"));
		excel.set("Q", 1, new Value("4.5"));
		excel.set("G", 2, new Sum2(new CellReference("A", 1, excel), new CellReference("AG", 1, excel)));
		excel.set("F", 2, new Sum2(new CellReference("A", 1, excel), new CellReference("G", 2, excel)));
		excel.set("J", 3, new Sum1("2", excel));
		excel.set("L", 1, new Sum2(new CellReference("C", 3, excel), new CellReference("Q", 1, excel)));
		excel.set("C", 1, new Value("5"));
		excel.set("N", 23, new Value("1"));
		excel.set("A", 1, new Value("1"));
		excel.set("C", 3, new Value("2.1"));
		excel.set("AG", 1, new Value("1"));
		excel.set("Q", 1, new Value("4.5"));
		excel.set("G", 2, new Sum2(new CellReference("A", 1, excel), new CellReference("AG", 1, excel)));
		excel.set("F", 2, new Sum2(new CellReference("A", 1, excel), new CellReference("G", 2, excel)));
		excel.set("J", 3, new Sum1("2", excel));
		excel.set("L", 1, new Sum2(new CellReference("C", 3, excel), new CellReference("Q", 1, excel)));
		excel.set("C", 1, new Value("5"));
		excel.set("N", 23, new Value("1"));
		excel.set("A", 1, new Value("1"));
		excel.set("C", 3, new Value("2.1"));
		excel.set("AG", 1, new Value("1"));
		excel.set("Q", 1, new Value("4.5"));
		excel.set("G", 2, new Sum2(new CellReference("A", 1, excel), new CellReference("AG", 1, excel)));
		excel.set("F", 2, new Sum2(new CellReference("A", 4, excel), new CellReference("G", 2, excel)));
		excel.set("J", 3, new Sum1("2", excel));
		excel.set("L", 1, new Sum2(new CellReference("C", 3, excel), new CellReference("Q", 1, excel)));
		excel.set("C", 1, new Value("5"));
		excel.set("N", 23, new Value("1"));
		excel.set("A", 1, new Value("1"));
		excel.set("C", 3, new Value("2.1"));
		excel.set("AG", 1, new Value("1"));
		excel.set("Q", 1, new Value("4.5"));
		excel.set("G", 2, new Sum2(new CellReference("A", 1, excel), new CellReference("AG", 1, excel)));
		excel.set("F", 2, new Sum2(new CellReference("A", 4, excel), new CellReference("G", 2, excel)));
		excel.set("J", 3, new Sum1("2", excel));
		excel.set("L", 1, new Sum2(new CellReference("C", 3, excel), new CellReference("Q", 1, excel)));
		excel.set("C", 1, new Value("5"));
		excel.remove("C");
		excel.set("N", 23, new Value("1"));
		excel.set("A", 1, new Value("1"));
		excel.set("C", 3, new Value("2.1"));
		excel.set("AG", 1, new Value("1"));
		excel.set("Q", 1, new Value("4.5"));
		excel.set("C", 1, new Value("5"));
		excel.set("N", 23, new Value("3.5"));
		excel.set("A", 1, new Value("1"));
		excel.set("C", 3, new Value("2"));
		excel.set("Q", 1, new Value("4.5"));
		excel.set("BA", 1, new Value("1"));
		excel.set("A", 1, new CellReference("BA",1,excel));
		excel.set("B", 3999, new Value("10"));
		excel.set("A", 1, new Sum2(new CellReference("BA",1,excel), new CellReference("B",3999,excel)));
		
		
		printCell("A1");
		
		ArrayList<String> test = new ArrayList<String>();
		Collections.addAll(test, "=SUM", "=SUM", "=SUM", "5.8", "1.1", "1");
		
		try {
			Element toPrint = getElement(test);
			System.out.print(toPrint + " " + toPrint.value());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}