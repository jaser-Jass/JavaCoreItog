package org.example;
import java.io.FileOutputStream;
import java.io.IOException;

public class TicTacToe {
    public static void main(String[] args) {

        int[] field = {0, 1, 2, 0, 1, 3, 2, 0, 0};

        try {
            writeFieldToFile(field, "tictactoe.dat");
            System.out.println("Состояние поля записано в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void writeFieldToFile(int[] field, String filename) throws IOException {
        if (field.length != 9) {
            throw new IllegalArgumentException("Поле должно содержать 9 элементов.");
        }

        try (FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] bytes = new byte[3];

            bytes[0] = (byte) ((field[0] & 3) | ((field[1] & 3) << 2) | ((field[2] & 3) << 4));
            bytes[1] = (byte) ((field[3] & 3) | ((field[4] & 3) << 2) | ((field[5] & 3) << 4));
            bytes[2] = (byte) ((field[6] & 3) | ((field[7] & 3) << 2) | ((field[8] & 3) << 4));

            fos.write(bytes);
        }
    }
}
