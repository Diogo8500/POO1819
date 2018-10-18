import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class PirateUnitTests {

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
	
	@Test (expected=CloneNotSupportedException.class)
	public void testCloneNotSupportedException() throws CloneNotSupportedException {
		PirateSolver b1 = new PirateSolver("3151");
		PirateSolver b2 = (PirateSolver) b1.clone();
	}
	
	@Test (timeout=3000)
	public void testEficientSolve() {
		PirateSolver p = new PirateSolver("4444");
		assertEquals(p.solution(), 5851);
	}
}
