import java.util.TreeSet;

public interface Element {
	Number value();
	
	TreeSet<Position> using();
	
	TreeSet<Position> usedBy();
}
