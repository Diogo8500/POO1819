
public abstract class Function implements Element {

	private static final long serialVersionUID = -470012339998576818L;
	private Element[] arguments;
	
	@Override
	public abstract Number value();
	
	public int setArguments(Element... args) {
		arguments = args;
		return args.length;
	}
	
	public Element[] arguments() {
		return arguments;
	}

}
