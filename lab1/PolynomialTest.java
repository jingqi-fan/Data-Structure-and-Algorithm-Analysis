package lab1;

public class PolynomialTest {
    public static void main(String[] args) {
        Polynomial poly1 = new Polynomial();
        poly1.addTerm(2, 3);  // 2x^3
        poly1.addTerm(-4, 1); // -4x

        Polynomial poly2 = new Polynomial();
        poly2.addTerm(5, 2);  // 5x^2
        poly2.addTerm(7, 0);  // 7

        Polynomial result = poly1.sum(poly2);
        System.out.println("Result: " + result);
    }

}
