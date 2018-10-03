import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Ponto a, b;

		a = new Ponto(sc.nextDouble(), sc.nextDouble());
		b = new Ponto(sc.nextDouble(), sc.nextDouble());

		sc.close();

		SegmentoReta sr = new SegmentoReta(a,b);

		System.out.println(sr.pontoMedio());

	}

}