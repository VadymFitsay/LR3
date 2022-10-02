package com.fitsay.droidbattle.droid;
public class SniperDroid extends BaseDroid {
    private char Dist;
    public SniperDroid(){
        super(70,20,"SD");
        Dist = 'L';
        Ik = " S ";
    }
    public int attack(char D) {
        int damage = getDamage();
        if (D == Dist){damage *= 1.5;}
        return damage;
    }
}
