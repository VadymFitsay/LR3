package com.fitsay.droidbattle.arena;
import com.fitsay.droidbattle.Printer;
import com.fitsay.droidbattle.DroidBattle;
import com.fitsay.droidbattle.droid.BaseDroid;
import com.fitsay.droidbattle.DroidBattle;
import com.fitsay.droidbattle.droid.RifleDroid;
import com.fitsay.droidbattle.droid.ShotgunDroid;
import com.fitsay.droidbattle.droid.SniperDroid;
import java.util.Scanner;

public class Arena {
    public void battle() throws InterruptedException {
        BaseDroid ar[];
        Scanner xx  = new Scanner(System.in);
        Printer pr = null;
        System.out.println("Record the fight to a file? Yes/No: ");
        if (xx.nextLine().toLowerCase().equals("yes")) {
            pr = new Printer("file.txt");
            Printer.setWriteToFile(true);
        } else {
            Printer.setWriteToFile(false);
        }
        ar = init_1vs1();
        DroidBattle arena = new DroidBattle( ar[0],ar[1] );
        BaseDroid winner = arena.startFight();
        Printer.print("-----------------------------------");
        Printer.print("The winner is " + winner.getName());

        if (Printer.getWriteToFile() && pr != null) {
            pr.closePrinter();
        }
    }
    public void battleTeam(int i) throws InterruptedException {
        Scanner xx = new Scanner(System.in);
        Printer pr = null;
        System.out.println("Record the fight to a file? Yes/No: ");
        if (xx.nextLine().toLowerCase().equals("yes")) {
            pr = new Printer("file.txt");
            Printer.setWriteToFile(true);
        } else {
            Printer.setWriteToFile(false);
        }
        BaseDroid ar1[] = new BaseDroid[i];
        BaseDroid ar2[] = new BaseDroid[i];

        Printer.print("Team 1");
        ar1 = init_TEAMvsTEAM(i,"′");
        Printer.print("Team 2");
        ar2 = init_TEAMvsTEAM(i,"″");
        DroidBattle arena = new DroidBattle();
        String winner = arena.startFight(ar1,ar2);
        Printer.print("------------------------------------");
        Printer.print("The winner is " + winner);

        if (Printer.getWriteToFile() && pr != null) {
            pr.closePrinter();
        }
    }
    public BaseDroid[] init_1vs1(){
        Scanner xx  = new Scanner(System.in);
        BaseDroid ar[] = new BaseDroid[2];
        String value;
        Printer.print("1. Rifle");
        Printer.print("2. Sniper");
        Printer.print("3. Shotgun");
        Printer.print("Droid 1");
        value = xx.nextLine();
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
        Printer.print("Droid 2");
        value = xx.nextLine();
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
    public BaseDroid[] init_TEAMvsTEAM(int i,String T){
        Scanner xx = new Scanner(System.in);
        BaseDroid ar[] = new BaseDroid[i];
        String value;
        Printer.print("1. Rifle");
        Printer.print("2. Sniper");
        Printer.print("3. Shotgun");
        for(int k=0;k<i;k++){
            value = xx.nextLine();
            switch (value) {
                case "1" -> {
                    ar[k] = new RifleDroid();
                    ar[k].setIk(ar[k].getIk().replaceFirst(" ", ("" + (k + 1))));
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
        return ar;
    }
}