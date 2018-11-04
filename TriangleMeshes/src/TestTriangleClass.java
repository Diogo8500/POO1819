import static org.junit.Assert.*;

import org.junit.Test;

public class TestTriangleClass {

	@Test (expected = IllegalArgumentException.class)
	public void testVertexA() {
		Triangle a = new TriangleClass(new PointClass(0,0), new PointClass(2,0), new PointClass(1,1));
		Point aux = new PointClass(0,1);
		a.setVertexA(aux);
		assertEquals(a.getVertexA(), aux);
		Point aux2 = new PointClass(0,2);
		a.setVertexA(aux2); // colineares = exception
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testVertexB() {
		Triangle a = new TriangleClass(new PointClass(0,0), new PointClass(2,0), new PointClass(0,2));
		Point aux = new PointClass(1,1);
		a.setVertexB(aux);
		assertEquals(a.getVertexB(), aux);
		Point aux2 = new PointClass(0,1);
		a.setVertexB(aux2); // colineares = exception
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testVertexC() {
		Triangle a = new TriangleClass(new PointClass(0,0), new PointClass(2,0), new PointClass(0,2));
		Point aux = new PointClass(1,1);
		a.setVertexC(aux);
		assertEquals(a.getVertexC(), aux);
		Point aux2 = new PointClass(0,1);
		a.setVertexB(aux2); // colineares = exception
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor() {
		assertNotNull(new TriangleClass(new PointClass(0,0), new PointClass(2,0), new PointClass(1,1)));
		Poligon a = new TriangleClass(new PointClass(2,0), new PointClass(2,0), new PointClass(1,1));
		Poligon b = new TriangleClass(new PointClass(0,0), new PointClass(1,1), new PointClass(2,2));
	}

}
