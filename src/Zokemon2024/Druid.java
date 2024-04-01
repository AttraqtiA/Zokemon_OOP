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
public class Druid extends Trainer {

    private int potion, revive, gaiaball;

    public int getPotion() {
        return potion;
    }

    public int getRevive() {
        return revive;
    }

    public int getGaiaball() {
        return gaiaball;
    }

    public Druid() {
        super();
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

    public void MinusGaiaball(int minus) {
        gaiaball -= minus;
    }

    public boolean AddGaiaball(int plus) {
        boolean nomoney = false;
        if (money < 20) {
            nomoney = true;
        } else {
            gaiaball += plus;
            money -= 20;
        }
        return nomoney;
    }
}
