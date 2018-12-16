import java.util.Iterator;
import java.util.Scanner;

public class Client {
	private static Sheet<Cell> excel = new Sheet<Cell>();
	private static Scanner in;
	private static String input[];
	
	private static void printAllCells() {
		if(!excel.isEmpty()) {
			int currentRow = excel.positions().get(0).row();
			boolean newLine = true;
			for(Position p : excel) {
				Cell c = excel.get(p);
				if(c.position().row() != currentRow) {
					System.out.println();
					newLine = true;
					currentRow = c.position().row();
				}
				if(!newLine){
					System.out.print(" ");
				}
				else {
					newLine = false;
				}
				System.out.print(c + " " + c.element() + " " + c.value());
			}
			System.out.println();
		}
	}
	
	private static void deleteOptions() {
		if(input[1].matches("^[A-Z]+$")) { //delete column
			deleteColumn();
		}else if(input[1].matches("^[0-9]+$")) { //delete row
			deleteRow();
		}else if(input[1].matches("^[A-Z]+[0-9]+$")) { //delete cell
			deleteCell();
		}
	}
	
	private static void deleteColumn() {
		Iterator<Position> column = excel.columnIterator(input[1]);
		while(column.hasNext()) {
			Position current = column.next();
			if(excel.canRemoveNode(current))
				excel.removeNode(current);
			else 
				excel.get(current).set(new Value(0));
		}
	}
	
	private static void deleteRow() {
		Iterator<Position> row = excel.rowIterator(Integer.parseInt(input[1]));
		while(row.hasNext()) {
			Position current = row.next();
			if(excel.canRemoveNode(current))
				excel.removeNode(current);
			else 
				excel.get(current).set(new Value(0));
		}
	}
	
	private static void deleteCell() {
		Position toRemove = parsePosition(input[1]);
		if(excel.canRemoveNode(toRemove))
			excel.removeNode(toRemove);
		else 
			excel.get(toRemove).set(new Value(0));
	}
	
	private static void cellOptions() {
		if(input.length == 1) {
			printCell();
		}else if(input[1].matches("^[0-9]+$")) {
			setValue();
		}else if(input[1].matches("^[A-Z]+[0-9]+$")) {
			setCell();
		}else {
			setFormula();
		}
		
	}
	
	private static void printCell() {
		Position toPrint = parsePosition(input[0]);
		Cell c = excel.get(toPrint);
		System.out.println(c + " " + c.element() + " " + c.value());
	} 
	
	private static void setValue() { //Parte do principio que nao existe a celula
		Position toSet = parsePosition(input[0]);
		excel.addNode(toSet);
		excel.set(toSet, new Cell(toSet, new Value(Integer.parseInt(input[1]))));
	}
	
	private static void setCell() { //Parte do principio que a celula do argumento 2 existe
		Position toSet = parsePosition(input[0]);
		Position toAdd = parsePosition(input[1]);
		
		excel.addNode(toSet);
		excel.set(toSet, new Cell(toSet, excel.get(toAdd)));
		excel.setDependency(toSet, toAdd);
		
	}
	
	private static void setFormula() {
		// TODO Auto-generated method stub
		
	}
	
	private static Position parsePosition(String _loc) {
		String column="", row="";
		for(int i=0; i<_loc.length(); i++) {
			String letter = String.valueOf(_loc.charAt(i));
			if(letter.matches("^[A-Z]$"))
				column = column.concat(letter);
			else 
				row = row.concat(letter);
		}
		return new Position(column, Integer.parseInt(row));
	}

	public static void main(String[] args) {
		
		in = new Scanner(System.in);
		while(in.hasNextLine()) {
			input = in.nextLine().split(" ");
			if(input[0].matches("^[A-Z]+[0-9]+$")) { // Opcoes com celulas
				cellOptions();
			}else if(input[0].equals("d*")) { // Opcoes para apagar
				deleteOptions();
			}else if(input[0].equals("p*")) { // Printar as celulas todas
				printAllCells();
			}
		}
		in.close();
	}

	
}
