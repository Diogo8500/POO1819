
public class Client {

	public static void main(String[] args) {
		SpreadSheet excel = new SpreadSheet();
		
		excel.set("A", 1, new Sum2(new Value("1"), new Value("2")));
		excel.set("A", 2, new Sum2(new CellReference("A",1, excel), new Value("2")));
		excel.set("C", 1, new Sum2(new CellReference("A",1, excel), new CellReference("A",2, excel)));
		excel.set("A", 1, new Sum2(new Value("1"), new Value("2")));
		excel.set("A", 2, new Sum2(new CellReference("A",1, excel), new Value("2")));
		excel.set("C", 1, new Sum2(new CellReference("A",1, excel), new CellReference("A",2, excel)));
		excel.remove(2);
		excel.set("A", 1, new Sum2(new Value("1.234"), new Value("2.45")));
		excel.set("A", 2, new Sum2(new CellReference("A",1, excel), new Value("3.1623")));
		excel.set("A", 3, new Sum2(new CellReference("A",1, excel), new CellReference("A",2, excel)));
		excel.set("A", 4, new Sum2(new Sum2(new Value("1.9999"), new CellReference("A",1, excel)), new Sum2(new CellReference("A",2, excel), new CellReference("A",3, excel))));
		
		
	
		
		
		
		for(SpreadSheet.Cell c : excel) {
			String string = c + " " + c.content() + " " + c.value();
			//System.out.printf("%-28s %s %s %s %s%n", string, "USEDBY", c.usedBy(), "-- USING", c.using());
			System.out.println(string);
		}
			
		
		
	}

}
