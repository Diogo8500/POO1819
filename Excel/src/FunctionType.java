import java.lang.reflect.Constructor;

public enum FunctionType {
	SUM1("Sum1", new Class<?>[] {String.class, SpreadSheet.class}),
	SUM2("Sum2", new Class<?>[] {Element.class, Element.class}),
	MULT2("Mult2", new Class<?>[] {Element.class, Element.class}),
	DIV2("Div2", new Class<?>[] {Element.class, Element.class});
	
	private String name;
	private Class<?>[] argumentTypes;
	
	private FunctionType(String _name, Class<?>... _argumentTypes) {
		name = _name;
		argumentTypes = _argumentTypes;
	}
	
	public Constructor<?> constructor(){
		try {
			return Class.forName(name).getConstructor(argumentTypes);
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
