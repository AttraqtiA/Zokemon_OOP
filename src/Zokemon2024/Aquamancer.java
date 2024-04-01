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
public class Aquamancer extends Trainer {

    private int potion, attack_boost, oceball;

    public Aquamancer() {
        super();
        potion = 0;
        attack_boost = 0;
        oceball = 0;
    }

    public int getPotion() {
        return potion;
    }

    public int getAttack_boost() {
        return attack_boost;
    }

    public int getOceball() {
        return oceball;
    }

    public void UsePotion(int minus, Zokemon woke) {
        potion -= minus;
        woke.UsePotion();
    }

    public boolean AddPotion(int plus) {
        boolean nomoney = false;
        if (money < 40) {
            nomoney = true;
        } else {
            potion += plus;
            money -= 40;
        }
        return nomoney;
    }

    public void UseBoost(int minus, Zokemon woke) {
        attack_boost -= minus;
        woke.AttackBoost();
    }

    public boolean AddBoost(int plus) {
        boolean nomoney = false;
        if (money < 30) {
            nomoney = true;
        } else {
            attack_boost += plus;
            money -= 30;
        }
        return nomoney;
    }

    public void MinusOceball(int minus) {
        oceball -= minus;
    }

    public boolean AddOceball(int plus) {
        boolean nomoney = false;
        if (money < 20) {
            nomoney = true;
        } else {
            oceball += plus;
            money -= 20;
        }
        return nomoney;
    }
}
