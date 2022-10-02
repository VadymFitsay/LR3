package com.fitsay.droidbattle;
import org.json.*;
import java.io.*;
public class Printer {
    private static FileWriter myWriter;
    private static boolean writeToFile = false;

    /**
     * Конструктор з параметром
     * @param fileName ім'я файлу
     */
    public Printer(String fileName) {
        try {
            myWriter = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для запису строки у файл з виводом її на екран
     * @param st строка яку потрібно вивести на екран та записати у файл
     */
    public static void print(String st) {
        System.out.println(st);
        if (writeToFile) {
            try {
                myWriter.write(st + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод для запису строки без виводу на екран
     * @param value строка яку потрібно записати у файл
     */
    public static void printInputValue(String value) {
        if (writeToFile) {
            try {
                myWriter.write(value + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод для зчитування інформації з файлу
     * @param fileName назва файлу
     */
    public static void readFromFile(String fileName) {

        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
            }
            fstream.close();
        } catch (IOException e) {
            System.out.println("The wrong way to file!");
            e.printStackTrace();
        }
    }

    /**
     * Сеттер для поля writeToFile
     * @param writeToFile поле яке визначає чи йде запис у файл
     */
    public static void setWriteToFile(boolean writeToFile) {
        Printer.writeToFile = writeToFile;
    }

    /**
     * Геттер для поля writeToFile
     * @return writeToFile
     */
    public static boolean getWriteToFile() {
        return writeToFile;
    }

    /**
     * Метод для закриття FileWriter'a
     */
    public void closePrinter() {
        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
