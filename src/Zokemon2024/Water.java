/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zokemon2024;

/**
 *
 * @author ASUS
 */
public class Water extends Zokemon {

    private int freezeChance;
    private boolean freeze;

    public Water() {
        super();
        type = "Water";
        level_atk = 2;
        level_hp = 2;
        freezeChance = r.nextInt(11) + 20;
    }

    public boolean getFreeze() {
        int freezeGacha = r.nextInt(100) + 1;
        if (freezeGacha <= freezeChance) {
            freeze = true;
        } else {
            freeze = false;
        }
        return freeze;
    }

    public int DealDamage(Zokemon enemy) { // Add if int returned was 0 then dodged by Light type Zokemon!!
        if (enemy.getType().equals("Light")) {
            int gacha = r.nextInt(100) + 1;
            if (gacha <= 50) {
                return 0; // enemy dodged!
            } else {
                enemy.MinusHP(attack);
            }
        } else if (enemy.getType().equals("Dark")) {
            int gacha = r.nextInt(100) + 1;
            if (gacha <= 75) {
                currhp -= (attack / 2); // reflect damage to self!
            }
            enemy.MinusHP(attack);

        } else if (enemy.getType().equals("Grass")) {
            enemy.MinusHP((attack * 3) / 4);
        } else if (enemy.getType().equals("Fire")) {
            enemy.MinusHP((attack * 3) / 2);
        } else {
            enemy.MinusHP(attack);
        }
        return attack;
    }
}
