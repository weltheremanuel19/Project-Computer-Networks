//EX 1
package clase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Se defineste clasa "Calculator".
public class Calculator {

    // Se defineste o metoda publica "calculateX5PlusY7()" ce arunca o exceptie in cazul unei erori de tip "NumberFormatException" sau "IOException"
    public double calculateX5PlusY7() throws NumberFormatException, IOException {
        // Se citește valoarea pentru "x".
        double x = readDouble("Tastați valoarea pentru X: ");
        // Se citeste valoarea pentru "y".
        double y = readDouble("Tastați valoarea pentru Y: ");

        // Se calculează "x5" folosind funcția "power()" definită mai jos.
        double x5 = power(x, 5);
        // Se calculează "y7" folosind funcția "power()" definită mai jos.
        double y7 = power(y, 7);

        // Se returnează suma valorilor calculate "x5" si "y7".
        return x5 + y7;
    }

    private double readDouble(String message) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print(message);
                String input = reader.readLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ati introdus o valoare nevalida. Va rugam sa incercati din nou.");
            }
        }
    }

    // Se defineste o metoda privata "power()" ce calculeaza si returneaza puterea unei baze la un exponent specificat.
    private double power(double base, int exponent) {
        // Se inițializeaza variabila "result" cu valoarea 1.0.
        double result = 1.0;
        // Se folosește un ciclu "for" pentru a calcula puterea bazei la exponent.
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        // Se returnează valoarea calculata.
        return result;
    }
}