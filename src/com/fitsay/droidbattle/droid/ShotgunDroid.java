package com.fitsay.droidbattle.droid;
public class ShotgunDroid extends BaseDroid {
    private char Dist;
    public ShotgunDroid(){
        super(70,20,"SGD");
        Dist = 'M';
        Ik = " ψ ";
    }
    public int attack(char D) {
        int damage = getDamage();
        if (D == Dist){damage *= 2;}
        if (D == 'L'){damage *= 0.5;}
        return damage;
    }
}