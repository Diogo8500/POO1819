import java.math.BigDecimal;

public class Sum2 extends Function {

	private static final long serialVersionUID = 6724136444307113327L;

	public Sum2(Element... _args) {
		if(setArguments(_args) != 2)
			throw new IllegalArgumentException();
	}
	
	@Override
	public Number value() {
		BigDecimal um = new BigDecimal(arguments()[0].value().toString());
		BigDecimal dois = new BigDecimal(arguments()[1].value().toString());
		return um.add(dois);
	}
	
	@Override
	public String toString() {
		return "=SUM " + arguments()[0] + " " + arguments()[1];
	}

}
