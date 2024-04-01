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
public class Pyromaniac extends Trainer {

    private int attack_boost, revive, volcaball;

    public int getAttack_boost() {
        return attack_boost;
    }

    public int getRevive() {
        return revive;
    }

    public int getVolcaball() {
        return volcaball;
    }

    public Pyromaniac() {
        super();
    }

    public void UseRevive(int minus, Zokemon woke) {
        revive -= minus;
        woke.UseRevive();
        for (int a = 0; a < list_fainted.size(); a++) {
            if (woke == list_fainted.get(a)) {
                list_fainted.remove(a);
                break;
            }
        }
    }

    public boolean AddRevive(int plus) {
        boolean nomoney = false;
        if (money < 100) {
            nomoney = true;
        } else {
            revive += plus;
            money -= 100;
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

    public void MinusVolcaball(int minus) {
        volcaball -= minus;
    }

    public boolean AddVolcaball(int plus) {
        boolean nomoney = false;
        if (money < 20) {
            nomoney = true;
        } else {
            volcaball += plus;
            money -= 20;
        }
        return nomoney;
    }
}
