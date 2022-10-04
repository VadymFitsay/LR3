package com.fitsay.droidbattle.arena;
import com.fitsay.droidbattle.DroidBattle;
import com.fitsay.droidbattle.JSON;
import com.fitsay.droidbattle.droid.BaseDroid;
import com.fitsay.droidbattle.droid.RifleDroid;
import com.fitsay.droidbattle.droid.ShotgunDroid;
import com.fitsay.droidbattle.droid.SniperDroid;
import org.json.simple.*;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class Arena {
    public void battle()  {
        new JSON();
        BaseDroid ar[];
        ar = init_1vs1();
        DroidBattle arena = new DroidBattle(ar[0],ar[1]);
        BaseDroid winner = arena.startFight();
        System.out.println("-----------------------------------");
        System.out.println("The winner is " + winner.getIk());
    }
    public void battleTeam(int i) {
        new JSON();
        BaseDroid ar1[] = new BaseDroid[i];
        BaseDroid ar2[] = new BaseDroid[i];
        System.out.println("Team 1");
        ar1 = init_TEAM(i,"′");
        System.out.println("Team 2");
        ar2 = init_TEAM(i,"″");
        DroidBattle arena = new DroidBattle();
        String winner = arena.startFight(ar1,ar2);
        System.out.println("------------------------------------");
        System.out.println("The winner is " + winner);
    }
    public BaseDroid[] init_1vs1(){
        new JSON();
        Scanner scanner  = new Scanner(System.in);
        BaseDroid ar[] = new BaseDroid[2];
        String value;
        System.out.println("1. Rifle");
        System.out.println("2. Sniper");
        System.out.println("3. Shotgun");
        System.out.println("Droid 1");
        if(JSON.Y_N == false){
            value = scanner.nextLine();
            JSON.main.put("1D",value);
        }
        else{
            value = (String)JSON.jsonObject.get("1D");
        }
        switch (value) {
            case "1" -> {
                ar[0] = new RifleDroid();
            }
            case "2" -> {
                ar[0] = new SniperDroid();
            }
            case "3" -> {
                ar[0] = new ShotgunDroid();
            }
        }
        System.out.println("Droid 2");
        if(JSON.Y_N == false){
            value = scanner.nextLine();
            JSON.main.put("2D",value);
        }
        else{
            value = (String)JSON.jsonObject.get("2D");
        }
        switch (value) {
            case "1" -> {
                ar[1] = new RifleDroid();
            }
            case "2" -> {
                ar[1] = new SniperDroid();
            }
            case "3" -> {
                ar[1] = new ShotgunDroid();
            }
        }
        return ar;
    }
    public BaseDroid[] init_TEAM(int i,String T){
        new JSON();
        Scanner scanner = new Scanner(System.in);
        BaseDroid ar[] = new BaseDroid[i];
        String value;
        System.out.println("1. Rifle");
        System.out.println("2. Sniper");
        System.out.println("3. Shotgun");
        JSONArray list1 = new JSONArray();
        JSONArray list2 = new JSONArray();
        JSONObject Type = new JSONObject();
        for(int k=0;k<i;k++){
            if(JSON.Y_N == false){
                value = scanner.nextLine();
                if (T.equals("′")){
                    Type.put("T",value);
                    list1.add(Type.clone());
                    Type.clear();
                }
                else {
                    Type.put("T",value);
                    list2.add(Type.clone());
                    Type.clear();
                }
            }
            else{
                JSONArray lang1 = (JSONArray)JSON.jsonObject.get("T1");
                JSONArray lang2 = (JSONArray)JSON.jsonObject.get("T2");
                if (T.equals("′")){
                    value = lang1.get(k).toString().substring(6,7);
                }
                else {
                    value = lang2.get(k).toString().substring(6,7);
                }
            }
            switch (value) {
                case "1" -> {
                    ar[k] = new RifleDroid();
                    ar[k].setIk(ar[k].getIk().replaceFirst(" ", ("" + (k+1))));
                    ar[k].setIk(ar[k].getIk().replaceFirst(" ", T));
                }
                case "2" -> {
                    ar[k] = new SniperDroid();
                    ar[k].setIk(ar[k].getIk().replaceFirst(" ",(""+(k+1))));
                    ar[k].setIk(ar[k].getIk().replaceFirst(" ",T));
                }
                case "3" -> {
                    ar[k] = new ShotgunDroid();
                    ar[k].setIk(ar[k].getIk().replaceFirst(" ",(""+(k+1))));
                    ar[k].setIk(ar[k].getIk().replaceFirst(" ",T));
                }
            }
        }
        if (T.equals("′")){
            JSON.main.put("T1",list1);
        }
        else{
            JSON.main.put("T2",list2);
        }
        return ar;
    }
}