package com.fitsay.droidbattle;
import java.util.Random;
import com.fitsay.droidbattle.Printer;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import com.fitsay.droidbattle.droid.BaseDroid;
public class DroidBattle {
    private BaseDroid firstDroid;
    private BaseDroid secondDroid;
    private BaseDroid attacking;
    private BaseDroid defender;
    private String[][] Map = new String[10][10];
    private BaseDroid t1[];
    private BaseDroid t2[];
    private  int N_F1;
    private  int N_F2;
    private int round=0;

    public DroidBattle(BaseDroid firstDroid,BaseDroid secondDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
    }
    public DroidBattle() {


    }
    public BaseDroid startFight() throws InterruptedException {
        mapcreate();
        StartPos();
        do {
            if((round & 1) == 0){attacking  = firstDroid;
            defender = secondDroid;}
            else{attacking  = secondDroid;
            defender = firstDroid;}
            round++;
            doFight();
        } while (attacking .isAlive()&&defender.isAlive());
        return attacking ;
    }
    public String startFight(BaseDroid ar1[],BaseDroid ar2[]) throws IllegalArgumentException {
        N_F1 = ar1.length;
        N_F2 = ar2.length;
        int in;
        mapcreate();
        StartPos(ar1, ar2);
        Scanner xx = new Scanner(System.in);
        do {
            if((round & 1) == 0){t1 = ar1;
            t2 = ar2;
            Printer.print("Виберіть бійця 1 команди");}
            else{t1 = ar2;
            t2 = ar1;
            Printer.print("Виберіть бійця 2 команди");}
            in = xx.nextInt();
            if(!(t1[in-1].isAlive())) throw new IllegalArgumentException();
            attacking  = t1[in-1];
            doFight();
            round++;
        } while (N_F1>0 && N_F2>0);
        if(N_F1>0){
            return "Team 1";
        }
        else {
            return "Team 2";
        }
    }
    private void StartPos(){
        Scanner xx = new Scanner(System.in);
        Printer.print("ST firstDroid");
        System.out.print("X: ");
        firstDroid.setX(xx.nextInt());
        System.out.print("Y: ");
        firstDroid.setY(xx.nextInt());
        Printer.printInputValue("x: "+firstDroid.getX()+" y:"+ firstDroid.getY());
        Printer.print("ST secondDroid");
        secondDroid.setX(xx.nextInt());
        secondDroid.setY(xx.nextInt());
        Printer.printInputValue("x: "+secondDroid.getX()+" y:"+ secondDroid.getY());
        Map[firstDroid.getX()][firstDroid.getY()] = firstDroid.Ik;
        Map[secondDroid.getX()][secondDroid.getY()] = secondDroid.Ik;
        printmap();
    }
    private void StartPos(BaseDroid ar1[],BaseDroid ar2[]){
        Printer.print("ST Team1");
        Scanner xx = new Scanner(System.in);
        for(int k=0;k<ar1.length;k++) {
            System.out.print("X: ");
            ar1[k].setX(xx.nextInt());
            System.out.print("Y: ");
            ar1[k].setY(xx.nextInt());
            Map[ar1[k].getX()][ar1[k].getY()] = ar1[k].Ik;
        }
        Printer.print("ST Team2");
        for(int k=0;k<ar2.length;k++) {
            System.out.print("X: ");
            ar2[k].setX(xx.nextInt());
            System.out.print("Y: ");
            ar2[k].setY(xx.nextInt());
            if (!(Map[ar2[k].getX()][ar2[k].getY()] == "   ")) throw new IllegalArgumentException("You entered an incorrect COR");
            Map[ar2[k].getX()][ar2[k].getY()] = ar2[k].Ik;
        }
        printmap();
    }
    private void doFight() {
        String value = "0";
        printmap();
        attacking .setP(attacking .getP()+5);
        while(attacking .getP()>0 &&(!value.equals("3"))) {
            Printer.print(attacking .getIk());
            Printer.print("P: " + attacking .getP());
            Printer.print("1.Атакувати");
            Printer.print("2.Піти до точки");
            Printer.print("3.Пропустити хід\n");
            Scanner xx = new Scanner(System.in);
            value = xx.nextLine();
            Printer.printInputValue(value);
            switch (value) {
                case "1" -> {
                    if(N_F1>0){
                        Printer.print("Атакуємо: ");
                        int in = xx.nextInt();
                        if(!(t2[in-1].isAlive())) throw new IllegalArgumentException();
                        defender = t2[in-1];
                    }
                    Printer.print("\nHealth " + defender.getIk()+ " : "+defender.getHealth()+"\n");
                    Printer.print("The "+defender.getHit(attacking .attack(getDistance((int)Distance(attacking .getX(),defender.getX(),attacking .getY(),defender.getY()))))+" damage!\n");
                    if(!defender.isAlive()){
                        Map[defender.getX()][defender.getY()] = "   ";
                        if((round & 1) == 0){N_F2 -= 1;}
                        else{N_F1 -= 1;};
                    }
                    attacking .setP(attacking .getP()-1);
                    Printer.print("Health " + defender.getIk()+ " : "+defender.getHealth()+"\n");
                    if(!defender.isAlive()){
                        value = "3";
                    }
                }
                case "2" -> {
                    int X = xx.nextInt();
                    xx.nextLine();
                    int Y = xx.nextInt();
                    if (attacking.getP() < Distance(attacking .getX(),X,attacking .getY(),Y)){
                        Printer.print("Не вистачає очків");
                        break;
                    }
                    if (!(Map[X][Y].equals("   "))){
                        Printer.print("Ця позиція вже зайнята іншим дроїдом");
                        break;
                    }
                    attacking .setP(attacking .getP()-(int)Distance(attacking .getX(),X,attacking .getY(),Y));
                    int x2 = attacking .getX();
                    int y2 = attacking .getY();
                    attacking .setX(X);
                    attacking .setY(Y);
                    Map[X][Y] = attacking .Ik;
                    Map[x2][y2] = "   ";
                    printmap();
                }
                case "3" -> {
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
            Printer.print((i)+" "+"[" + Map[1][i] + "][" + Map[2][i] + "][" + Map[3][i] + "][" + Map[4][i] + "][" + Map[5][i] + "][" + Map[6][i] + "][" + Map[7][i] + "][" + Map[8][i] + "][" + Map[9][i] + "]");
        }
        Printer.print("    1    2    3    4    5    6    7    8    9  ");
        Printer.print("_________________________________________________________________________________________________");
    }
    public double Distance(int x1, int x2, int y1, int y2) {
        double D = Math.sqrt(((Math.pow((x2-x1),2) + (Math.pow((y2-y1),2)))));
        return D;
    }
    public char getDistance(int Distance){
        char D;
        if (Distance>=6){
            D = 'L';
        }
        if (Distance<=3 && Distance>1){
            D = 'N';
        }
        else{
            D = 'M';
        }
        return D;
    }
}