public class Main {

	public static void main(String[] args) {
		Position l1 = new Position("A",1);
		Position l2 = new Position("B",1);
		Position l3 = new Position("C",1);
		Value v1 = new Value(5.7);
		Value v2 = new Value(6);
		Sum2 f1 = new Sum2(v1, v2);
		Sum2 f2 = new Sum2(f1, v1);
		Cell c1 = new Cell(l1, f1);
		Cell c2 = new Cell(l2, f2);
		Sum2 f3 = new Sum2(c1, v2);
		Cell c3 = new Cell(l3, f3);
		
		
		System.out.println(c3 + " " + c3.element() + " " + c3.value());

		
		Sheet op = new Sheet();
		op.addNode(new Position("A",2), new Cell(null, null));
		op.addNode(new Position("B",1), new Cell(null, null));
	}

}
