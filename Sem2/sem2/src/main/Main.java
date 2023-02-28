//Main
package main;

import clase.Calculator;
import clase.MatrixDataHandler;

import java.io.IOException;

// Definim clasa "Main"
public class Main {

    // Definim o metoda pentru a calcula puterea unei baze date la un exponent dat
    private static double power(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    // Metoda principala a programului
    public static void main(String[] args) throws IOException {
        // Cream un obiect Calculator si apelam metoda calculateX5PlusY7() pentru a calcula rezultatul
        Calculator calculator = new Calculator();
        double result = calculator.calculateX5PlusY7();
        System.out.println("Rezultatul este: " + result);

        // Cream un obiect MatrixDataHandler si modificam o valoare din fisierul matrice
        MatrixDataHandler mH = new MatrixDataHandler(5, 7, "matrix.txt");
        mH.modifyValueAndUpdateFile("matrix.txt", 3, 4, 345);

        // Apelam metoda power() pentru a calcula 2 la puterea 5 si afisam rezultatul
        double base = 2.0;
        int exponent = 5;
        double powerResult = power(base, exponent);
        System.out.println(base + " la puterea " + exponent + " este egal cu " + powerResult);
    }
}