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
public class Grass extends Zokemon {

    private int bloom;

    public int getBloom() { // healing in Zokemon/parent class
        return bloom;
    }

    public Grass() {
        super();
        type = "Grass";
        level_atk = 1;
        level_hp = 3;
        bloom = r.nextInt(11) + 5;
    }

    public int Heal() {
        return currhp += (bloom * maxhp / 100);
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

        } else if (enemy.getType().equals("Fire")) {
            enemy.MinusHP((attack * 3) / 4);
        } else if (enemy.getType().equals("Water")) {
            enemy.MinusHP((attack * 3) / 2);
        } else {
            enemy.MinusHP(attack);
        }
        return attack;
    }
    
}
