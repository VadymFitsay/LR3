package com.fitsay.droidbattle.droid;
import java.util.Random;
public class RifleDroid extends BaseDroid {
    private char Dist;
    public RifleDroid(){
        super(70,20,"RD");
        Dist = 'N';
        Ik = " ϟ ";
    }
    public int attack(char D) {
        int damage = getDamage();
        if (D == Dist){damage *= 1.5;}
        return damage;
    }
}