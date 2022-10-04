package com.fitsay.droidbattle;
import com.fitsay.droidbattle.arena.Arena;
import java.io.FileReader;
import java.util.Scanner;
import org.json.simple.JSONObject;
import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ParseException {
        long k ;
        new JSON();
        String value;
        System.out.println("1. Битва 1 на 1");
        System.out.println("2. Команда на команду");
        System.out.println("3. Зчитати битву з файла");
        System.out.println("4. Вихід");
        Scanner scanner = new Scanner(System.in);
        value = scanner.nextLine();
        switch (value) {
            case "1" -> {
                JSON.main.put("TB",value);
                new Arena().battle();
                JSON.writer();
            }
            case "2" -> {
                JSON.main.put("TB",value);
                System.out.println("Введіть кількість бійців");
                k = scanner.nextInt();
                JSON.main.put("KB",k);
                new Arena().battleTeam((int)k);
                JSON.writer();
            }
            case "3" -> {
                JSON.Y_N = true;
                JSON.reader = new FileReader(JSON.path);
                JSONParser jsonParser = new JSONParser();
                JSON.jsonObject = (JSONObject)jsonParser.parse(JSON.reader);
                value = (String)JSON.jsonObject.get("TB");
                switch (value) {
                    case "1" -> {
                        new Arena().battle();
                    }
                    case "2" -> {
                        System.out.println("Введіть кількість бійців");
                        k = (long)JSON.jsonObject.get("KB");
                        new Arena().battleTeam((int)k);
                    }
                }
            }
            case "4" -> {
                System.out.println("Завершення програми");
            }
        }
    }
}