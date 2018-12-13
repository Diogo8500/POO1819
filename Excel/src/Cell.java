public class Cell implements Element {

	private static final long serialVersionUID = 6931360159982200663L;
	private Position name;
	private Element element;

	public Cell(Position _l1, Element _element) {
		name = _l1;
		element = _element;
	}
	
	public Element element() {
		return element;
	}
	
	@Override
	public Number value() {
		return element.value();
	}
	
	@Override
	public String toString() {
		return name.toString();
	}

}
