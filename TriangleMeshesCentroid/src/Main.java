import java.lang.reflect.*;
import java.util.*;

public class Main {

	public static  String capital(String s) {
		String res = s.toLowerCase();
		Character initial = Character.toUpperCase(res.charAt(0));
		StringBuilder sb = new StringBuilder(res);
		sb.setCharAt(0, initial);
		final String answer = sb.toString();
		return answer;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner (System.in);
		Constructor<?> constructor;
		Class<?> cl;
		Polygon p;
		String s;
		String [] aos;
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			aos = s.split(" ");
			try {
				cl = Class.forName(capital(aos[0]));
				constructor = cl.getConstructor (new Class[] {String.class});
				p = (Polygon) constructor.newInstance(s);
				System.out.print(p.getClass().getName()+": ");
                System.out.print((int) p.perimeter()+" ");
                System.out.println(p.centroid());
			}catch (Exception e) {
                System.out.println("nwwalf");
            }
		}
		sc.close();
	}
}