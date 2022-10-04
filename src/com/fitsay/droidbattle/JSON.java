package com.fitsay.droidbattle;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class JSON {
    public static JSONObject main = new JSONObject();
    public static FileReader reader;
    public static JSONObject jsonObject;
    public static boolean Y_N = false;
    public final static String path = "C:\\Users\\Admin\\IdeaProjects\\LR3\\data.json";
    public JSON(){
    }
    public static void writer() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(JSON.path));
        out.write(JSON.main.toString());
        out.close();
    }
}
