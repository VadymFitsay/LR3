package com.fitsay.droidbattle;
import com.fitsay.droidbattle.arena.Arena;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws InterruptedException {
        String value;
        System.out.println("1. Битва 1 на 1");
        System.out.println("2. Команда на команду");
        System.out.println("3. Зчитати битву з файла");
        System.out.println("4. Вихід");
        Scanner xx = new Scanner(System.in);
        value = xx.nextLine();
        switch (value) {
            case "1" -> {
                new Arena().battle();
            }
            case "2" -> {
                System.out.println("Введіть кількість бійців");
                new Arena().battleTeam(xx.nextInt());
            }
            case "3" -> {
                System.out.println("Enter the file path:");
                String fileName = xx.nextLine();
                Printer.readFromFile(fileName);
            }
            case "4" -> {
                System.out.println("Completion of the program!");
            }
        }
    }
}