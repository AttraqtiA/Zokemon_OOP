/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zokemon2024;

/**
 * @author ASUS
 */
public class Fire extends Zokemon {

    private int blaze;

    public int getBlaze() {
        return blaze;
    }

    public Fire() {
        super();
        type = "Fire";
        level_atk = 3;
        level_hp = 1;
        blaze = r.nextInt(31) + 20;
    }

    public int DealExtraDamage(Zokemon enemy) {
        if (enemy.getType().equals("Light")) {
            int gacha = r.nextInt(100) + 1;
            if (gacha <= 50) {
                return 0; // enemy dodged!
            } else {
                enemy.MinusHP((attack + (blaze * attack)));
            }
        } else if (enemy.getType().equals("Dark")) {
            int gacha = r.nextInt(100) + 1;
            if (gacha <= 75) {
                currhp -= ((attack + (blaze * attack)) / 2); // reflect damage to self!
            }
            enemy.MinusHP((attack + (blaze * attack)));

        } else if (enemy.getType().equals("Water")) {
            enemy.MinusHP((attack * 3) / 4);
        } else if (enemy.getType().equals("Grass")) {
            enemy.MinusHP((attack * 3) / 2);
        } else {
            enemy.MinusHP(attack);
        }
        return attack + (blaze * attack);
    }

}
