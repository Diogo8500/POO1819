import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) {
		Scanner sc = new Scanner (System.in);
		PirateSolver solver = new PirateSolver(sc.next());
		System.out.println(solver.solution());
		sc.close();
	}
}

/*
@Test
public void testConstructor() {
	PirateSolver p = new PirateSolver("3151");
	List<Character> sa = new ArrayList<Character>(); // line 4 
	Collections.addAll(sa, 'E','E','E','F','E','E','E','E','E','F'); // line 5 
	assertEquals(p.getCrew(), sa);
	PirateSolver q = new PirateSolver("14115123"); 
	assertEquals(q.getCrew().size(), 18);
}

@Test
public void testThrowOverBoardWithStep() { 
	PirateSolver p = new PirateSolver("3151"); 
	assertTrue(p.throwOverBoardWithStep(1)); 
	assertFalse(p.throwOverBoardWithStep(3));
}

@Test
public void testSolution() { 
	PirateSolver p = new PirateSolver("3151"); 
	assertEquals(p.solution(), 3);
	p = new PirateSolver("14115123");
	assertEquals(p.solution(), 12);
}
*/