
public class SegmentoReta {
	private Ponto a, b;
	
	public SegmentoReta(Ponto _a, Ponto _b) {
		if (_a.equal(_b)) {
			System.out.println("shoarb");
			System.exit(0);
		}
		
		a = _a;
		b = _b;
	}
	
	public String pontoMedio() {
		double xDif = a.getX() + b.getX();
		double yDif = a.getY() + b.getY();
		
		double mX = xDif/2;
		double mY = yDif/2;
		
		return (int)mX + "@" + (int)mY;
	}
}
