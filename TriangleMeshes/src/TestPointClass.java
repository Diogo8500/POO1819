
import static org.junit.Assert.*;
import org.junit.Test;

public class TestPointClass {

	@Test  
	public void testConstructor() {
		assertNotNull(new PointClass(2,4));
		assertNotNull(new PointClass(-1,2));
	}
	
	@Test
	public void testGets() {
		Point p1 = new PointClass(2,9);
		assertEquals(p1.getX(),2,0);
		assertEquals(p1.getY(),9,0);
	}
	
	@Test
	public void testSetX() {
		Point p1 = new PointClass(2,2);
		p1.setX(1);
		assertEquals(p1.getX(),1,0);
		p1.setX(-1);
		assertEquals(p1.getX(),-1,0);
	}
	
	@Test
	public void testSetY() {
		Point p1 = new PointClass(2,2);
		p1.setY(5);
		assertEquals(p1.getY(),5,0);
		p1.setY(-10);
		assertEquals(p1.getY(),-10,0);
	}

	@Test
	public void testGetDist() {
		Point p1 = new PointClass(0,1);
		Point p2 = new PointClass(0,3);
		assertEquals(p1.getDist(p2),2,0);
		p1.setX(1); p2.setX(3);
		assertEquals(p1.getDist(p2),2*Math.sqrt(2),0);
	}
	
	@Test
	public void testGetCoTan() {
		Point p1 = new PointClass(0,1);
		Point p2 = new PointClass(0,3);
		assertEquals(p1.getCoTan(p2),0,0);
		p1.setX(1); p2.setX(3);
		assertEquals(p1.getCoTan(p2),-1,0);
	}
	
	@Test
	public void testIsCollinear() {
		Point p1 = new PointClass(0,1);
		Point p2 = new PointClass(0,3);
		Point p3 = new PointClass(1,1);
		assertFalse(p1.isCollinear(p2, p3));
		p3.setX(0); p3.setY(5);
		assertTrue(p1.isCollinear(p2, p3));
		p2.setY(1); p3.setY(1);
		assertTrue(p1.isCollinear(p2, p3));
	}
}
