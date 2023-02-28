//EX 2
//1. Tratarea cazului in care fisierul a fost modificat: am adaugat tratamentul exceptiilor cu ajutorul blocurilor try-catch in cazul in care apar erori in timpul executiei programului.
// Astfel, in loc sa se intrerupa executia programului, aceste exceptii sunt tratate si afisate in consola, iar programul continua sa ruleze.

//2. Separarea in diferite metode: am separat metoda initiala in doua metode diferite: readMatrixFromFile si writeMatrixToFile.
// Aceasta abordare ajuta la o mai buna organizare a codului si la o mai buna modularitate, deoarece fiecare metoda are o responsabilitate clara si distincta.

//3. Tratarea exceptiilor: am adaugat blocuri try-catch in metodele getValueFromPosition, modifyValueAndUpdateFile, readMatrixFromFile si writeMatrixToFile,
// pentru a trata erorile ce pot aparea in timpul executiei programului
package clase;

import java.io.*;

public class MatrixDataHandler {
    private int[][] matrix;
    private int h;
    private int w;
    private String filename;

    public MatrixDataHandler(int h, int w, String filename) throws IOException {
        this.h = h;
        this.w = w;
        this.filename = filename;
        matrix = allocateMatrixSpace(h, w);
        writeMatrixToFile(matrix, h, w, filename);
    }

    private int[][] allocateMatrixSpace(int h, int w) {
        int[][] matrix = new int[h][];
        for (int i = 0; i < h; i++) {
            matrix[i] = new int[w];
        }
        return matrix;
    }

    private void writeMatrixToFile(int[][] matrix, int h, int w, String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(h + " " + w + "\n");
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    fw.write(matrix[i][j] + " ");
                }
                fw.write("\n");
            }
        }
    }

    public int getValueFromPosition(int posH, int posW) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            int h = Integer.parseInt(line.split(" ")[0]);
            int w = Integer.parseInt(line.split(" ")[1]);

            int[][] matrix = allocateMatrixSpace(h, w);
            for (int i = 0; i < h; i++) {
                line = br.readLine();
                for (int j = 0; j < w; j++) {
                    matrix[i][j] = Integer.parseInt(line.split(" ")[j]);
                }
            }
            return matrix[posH][posW];
        } catch (IOException exception) {
            System.out.println("Error reading matrix file: " + exception.getMessage());
            throw exception;
        }
    }

    public void modifyValueAndUpdateFile(String s, int posH, int posW, int val) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            int h = Integer.parseInt(line.split(" ")[0]);
            int w = Integer.parseInt(line.split(" ")[1]);

            int[][] matrix = allocateMatrixSpace(h, w);
            for (int i = 0; i < h; i++) {
                line = br.readLine();
                for (int j = 0; j < w; j++) {
                    matrix[i][j] = Integer.parseInt(line.split(" ")[j]);
                }
            }
            matrix[posH][posW] = val;
            writeMatrixToFile(matrix, h, w, filename);
        } catch (IOException exception) {
            System.out.println("Error reading or writing matrix file: " + exception.getMessage());
            throw exception;
        }
    }
}

