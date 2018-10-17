import java.util.*;

public class PirateSolver {
	
	private List<Character> pirateList;
	private int noEnemies;
	
	public PirateSolver(String s) {
		pirateList = new ArrayList<Character>();
		int len = s.length();
		noEnemies = 0;
		
		for(int i=0; i<len; i++) {
			int num = Character.digit(s.charAt(i), 10);
			for(int j=0; j<num; j++) {
				if(i%2 == 0) {
					pirateList.add('E');
					noEnemies++;
				}
				else
					pirateList.add('F');
			}
		}
	}
	
	public List<Character> getCrew() {
		return pirateList;
	}
	
	public boolean throwOverBoardWithStep(int n) {
		int num = noEnemies;
		List<Character> pirateListAux= new ArrayList<Character>(pirateList.size());
		
		for(int i=0; i<pirateList.size(); i++) {
			pirateListAux.add(pirateList.get(i));
		}
		
		for(int i=n-1; num > 0; i+=n-1) {
			while(i >= pirateListAux.size()) {
				i -= pirateListAux.size();
			}
			if(pirateListAux.get(i) == 'F') return true;
			pirateListAux.remove(i);
			num--;
		}
		return false;
		
	}
	
	public int solution() {
		for(int i=1;;i++) 
			if(!throwOverBoardWithStep(i)) return i;
	}
}
