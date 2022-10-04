package com.fitsay.droidbattle;
import java.util.Scanner;
import com.fitsay.droidbattle.droid.BaseDroid;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Iterator;

public class DroidBattle {
    private BaseDroid firstDroid;
    private BaseDroid secondDroid;
    private BaseDroid attacking;
    private BaseDroid defender;
    private String[][] Map = new String[10][10];
    private BaseDroid t1[];
    private BaseDroid t2[];
    private  int KB1;
    private  int KB2;
    private int round=0;

    public DroidBattle(BaseDroid firstDroid,BaseDroid secondDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
    }
    public DroidBattle() {}
    public BaseDroid startFight() {
        mapcreate();
        StartPos();
        printmap();
        do {
            if((round & 1) == 0){attacking  = firstDroid;
            defender = secondDroid;}
            else{attacking  = secondDroid;
            defender = firstDroid;}
            round++;
            doFight();
        } while (attacking.isAlive()&&defender.isAlive());
        return attacking;
    }
    public String startFight(BaseDroid ar1[],BaseDroid ar2[]) throws IllegalArgumentException {
        KB1 = ar1.length;
        KB2 = ar2.length;
        mapcreate();
        StartPos(ar1, ar2);
        Scanner xx = new Scanner(System.in);
        do {
            if((round & 1) == 0){t1 = ar1;
            t2 = ar2;
            System.out.println("Виберіть бійця 1 команди");}
            else{t1 = ar2;
            t2 = ar1;
            System.out.println("Виберіть бійця 2 команди");}
            int in = xx.nextInt();
            if(!(t1[in-1].isAlive())) throw new IllegalArgumentException();
            attacking  = t1[in-1];
            doFight();
            round++;
        } while (KB1>0 && KB2>0);
        if(KB1>0){
            return "Team 1";
        }
        else {
            return "Team 2";
        }
    }
    private void StartPos(){
        new JSON();
        int x=0;
        int y=0;
        long t;
        JSONObject ST1 = new JSONObject();
        JSONObject ST2 = new JSONObject();
        Scanner xx = new Scanner(System.in);
        System.out.println("ST firstDroid");
        if(JSON.Y_N == false){
            System.out.print("X: ");
            x = xx.nextInt();
            System.out.print("Y: ");
            y = xx.nextInt();
            firstDroid.setX(x);
            firstDroid.setY(y);
            ST1.put("X",x);
            ST1.put("Y",y);
            JSON.main.put("ST1",ST1);
        }
        else{
            JSONObject structure = (JSONObject)JSON.jsonObject.get("ST1");
            t = (long)structure.get("X");
            firstDroid.setX((int)t);
            System.out.print("Y: ");
            t = (long)structure.get("Y");
            firstDroid.setY((int)t);
        }
        System.out.println("ST secondDroid");
        if(JSON.Y_N == false){
            System.out.print("X: ");
            x = xx.nextInt();
            System.out.print("Y: ");
            y = xx.nextInt();
            secondDroid.setX(x);
            secondDroid.setY(y);
            ST2.put("X",x);
            ST2.put("Y",y);
            JSON.main.put("ST2",ST2);
        }
        else{
            JSONObject structure = (JSONObject)JSON.jsonObject.get("ST2");
            t = (long)structure.get("X");
            secondDroid.setX((int)t);
            System.out.print("Y: ");
            t = (long)structure.get("Y");
            secondDroid.setY((int)t);
        }
        if (!Map[x][y].equals("   ")) throw new IllegalArgumentException();
        Map[firstDroid.getX()][firstDroid.getY()] = firstDroid.Ik;
        Map[secondDroid.getX()][secondDroid.getY()] = secondDroid.Ik;
    }
    private void StartPos(BaseDroid ar1[],BaseDroid ar2[]){
        new JSON();
        JSONArray lang1 = null;
        Iterator i1 = null;
        JSONArray lang2 = null;
        Iterator i2 = null;
        int x;
        long t;
        int y;
        JSONArray list1 = new JSONArray();
        JSONArray list2 = new JSONArray();
        JSONObject ST1 = new JSONObject();
        JSONObject ST2 = new JSONObject();
        System.out.println("ST Team1");
        Scanner xx = new Scanner(System.in);
        for(int k=0;k<ar1.length;k++) {
            if(JSON.Y_N == false){
                System.out.print("X: ");
                x = xx.nextInt();
                ar1[k].setX(x);
                ST1.put("X",x);
                System.out.print("Y: ");
                y = xx.nextInt();
                ar1[k].setY(y);
                ST1.put("Y",y);
                list1.add(ST1.clone());
                ST1.clear();
            }
            else{
                if(k==0) {
                    lang1 = (JSONArray) JSON.jsonObject.get("ST1");
                    i1 = lang1.iterator();
                }
                lang1 = (JSONArray) JSON.jsonObject.get("ST1");
                if (k ==0) i1 = lang1.iterator();
                JSONObject innerObj = (JSONObject) i1.next();
                t = (long)innerObj.get("X");
                ar1[k].setX((int)t);
                t = (long)innerObj.get("Y");
                ar1[k].setY((int)t);
            }
            Map[ar1[k].getX()][ar1[k].getY()] = ar1[k].Ik;
        }
        System.out.println("ST Team2");
        for(int k=0;k<ar2.length;k++) {
            if(JSON.Y_N == false){
                System.out.print("X: ");
                x = xx.nextInt();
                ar2[k].setX(x);
                ST2.put("X",x);
                System.out.print("Y: ");
                y = xx.nextInt();
                ar2[k].setY(y);
                ST2.put("Y",y);
                list2.add(ST2.clone());
                ST2.clear();
            }
            else{
                if(k==0) {
                    lang2 = (JSONArray)JSON.jsonObject.get("ST2");
                    i2 = lang2.iterator();
                }
                JSONObject innerObj = (JSONObject) i2.next();
                t = (long)innerObj.get("X");
                ar2[k].setX((int)t);
                t = (long)innerObj.get("Y");
                ar2[k].setY((int)t);
            }
            Map[ar2[k].getX()][ar2[k].getY()] = ar2[k].Ik;
        }
        JSON.main.put("ST1",list1);
        JSON.main.put("ST2",list2);
    }
    private void doFight() {
        new JSON();
        String value = "0";
        printmap();
        attacking.setP(attacking.getP()+5);
        while(attacking.getP()>0 && (!value.equals("3"))) {
            System.out.println(attacking.getIk());
            System.out.println("P: " + attacking.getP());
            System.out.println("1.Атакувати");
            System.out.println("2.Піти до точки");
            System.out.println("3.Пропустити хід\n");
            Scanner xx = new Scanner(System.in);
            value = xx.nextLine();
            switch (value) {
                case "1" -> {
                    if(!(attacking.getIk().charAt(0) == ' ')){
                        System.out.println("Атакуємо: ");
                        int in = xx.nextInt();
                        if(!(t2[in-1].isAlive())) throw new IllegalArgumentException();
                        defender = t2[in-1];
                    }
                    System.out.println("\nHealth " + defender.getIk()+ " : "+defender.getHealth()+"\n");
                    System.out.println("The "+defender.getHit(attacking.attack(getDistance((int)Distance(attacking .getX(),defender.getX(),attacking .getY(),defender.getY()))))+" damage!\n");
                    if(!defender.isAlive()){
                        Map[defender.getX()][defender.getY()] = "   ";
                        if((round & 1) == 0){KB2 -= 1;}
                        else{KB1 -= 1;};
                    }
                    attacking.setP(attacking.getP()-1);
                    System.out.println("Health " + defender.getIk()+ " : "+defender.getHealth()+"\n");
                    if(!defender.isAlive()){
                        value = "3";
                    }
                }
                case "2" -> {
                    int X = xx.nextInt();
                    xx.nextLine();
                    int Y = xx.nextInt();
                    if (attacking.getP() < Distance(attacking .getX(),X,attacking .getY(),Y)){
                        System.out.println("Не вистачає очків");
                        break;
                    }
                    if (!(Map[X][Y].equals("   "))){
                        System.out.println("Ця позиція вже зайнята іншим дроїдом");
                        break;
                    }
                    attacking .setP(attacking.getP()-(int)Distance(attacking.getX(),X,attacking.getY(),Y));
                    Map[attacking.getX()][ attacking.getY()] = "   ";
                    attacking.setX(X);
                    attacking.setY(Y);
                    Map[X][Y] = attacking.Ik;
                    printmap();
                }
                case "3" -> {
                    System.out.println("Хід пропущено");
                }
            }
        }
    }
    public void mapcreate() {
        for (int i = 0; i < 10; i++) {
            for (int i2 = 0; i2 < 10; i2++) {
                Map[i][i2] = "   ";
            }
        }
    }
    public void printmap()  {
        for (int i = 9;i>=1;i--) {
            System.out.println((i)+" "+"[" + Map[1][i] + "][" + Map[2][i] + "][" + Map[3][i] + "][" + Map[4][i] + "][" + Map[5][i] + "][" + Map[6][i] + "][" + Map[7][i] + "][" + Map[8][i] + "][" + Map[9][i] + "]");
        }
        System.out.println("    1    2    3    4    5    6    7    8    9  ");
        System.out.println("_________________________________________________________________________________________________");
    }
    public double Distance(int x1, int x2, int y1, int y2) {
        double D = Math.sqrt(((Math.pow((x2-x1),2)+(Math.pow((y2-y1),2)))));
        return D;
    }
    public char getDistance(int Distance){
        char D;
        if(Distance == 1){D = 'M';}
        else if (Distance<5) {D = 'N';}
        else{D = 'L';}
        return D;
    }
}