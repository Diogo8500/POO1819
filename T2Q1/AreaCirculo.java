import java.util.Scanner;

public class AreaCirculo {

	public static void main(String[] args) {
		float raio;
        double area;
   
        Scanner sc = new Scanner(System.in);
        raio = sc.nextFloat();
        sc.close();
        
        Circle c = new Circle(raio); 
        area = c.getA();
        System.out.println((int) area);

	}

}
