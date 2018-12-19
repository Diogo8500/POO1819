import java.util.TreeSet;

public interface Element {
	String value();
	
	TreeSet<Position> using();
	
	TreeSet<Position> usedBy();
}
