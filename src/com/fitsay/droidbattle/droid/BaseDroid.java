package com.fitsay.droidbattle.droid;
public class BaseDroid {
    private String name;
    private int damage;
    private int health;
    public String Ik;
    private int P;
    private int x;
    private int y;
    public BaseDroid(int health, int damage, String name) {
        this.health = health;
        this.damage = damage;
        this.name = name;
        P = 0;
        Ik = " D ";
    }
    public int getDamage() {
        return damage;
    }
    public int getHealth() {
        return health;
    }
    public int getP() {
        return P;
    }
    public String getIk() {
        return Ik;
    }
    public void setIk(String ik) {
        Ik = ik;
    }
    public boolean isAlive() {
        return health > 0;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int X){x = X;}
    public void setP(int p){P = p;}
    public void setY(int X){y = X;}
    public int attack(char D) {
        return damage;
    }
    public int getHit(int damage) {
        this.health -= damage;
        if (health < 0) {
            health = 0;
            System.out.println("The "+name+" was killed!");
        }
        return damage;
    }
    @Override
    public String toString() {
        return name + " health = " + health;
    }
}