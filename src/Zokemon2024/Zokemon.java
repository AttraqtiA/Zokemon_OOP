/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zokemon2024;

import java.util.Random;

/**
 *
 * @author ASUS
 */
public class Zokemon {

    protected String name, type;
    protected int attack;
    protected int maxhp;
    protected int currhp;
    protected int level;
    protected int rarity;
    protected int level_atk;
    protected int level_hp;
    protected boolean faint;

    Random r = new Random();

    public Zokemon() {
        attack = r.nextInt(5) + 3;
        maxhp = r.nextInt(26) + 25;
        currhp = maxhp;
        level = 1;
        rarity = r.nextInt(4) + 3;
        faint = false;
    }

    public void levelUp(int level) {
        attack += level_atk;
        currhp += level_hp;
        maxhp += level_hp;
    }

    public void MinusHP(int minus) {
        currhp -= minus;
    }

    public void HealHP(int heal) {
        currhp += heal;
        if (currhp >= maxhp) {
            currhp = maxhp;
        }
    }

    public void addLevel(int level) {
        this.level += level;
    }

    public void UseRevive() {
        faint = true;
        currhp = 20;
        if (currhp >= maxhp) {
            currhp = maxhp;
        }
    }

    public void AttackBoost() {
        attack += 5;
    }

    public void RevertBoost() {
        attack -= 5;
    }

    public void UsePotion() {
        currhp += 20;
        if (currhp >= maxhp) {
            currhp = maxhp;
        }
    }

    public int getCurrHP() {
        return currhp;
    }

    public void setCurrHP(int currhp) {
        this.currhp = currhp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setMaxHP(int maxhp) {
        this.maxhp = maxhp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setFaint(boolean faint) {
        this.faint = faint;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getMaxHP() {
        return maxhp;
    }

    public int getLevel() {
        return level;
    }

    public int getRarity() {
        return rarity;
    }

    public boolean isFaint() {
        return faint;
    }

    public int getLevel_atk() {
        return level_atk;
    }

    public void setLevel_atk(int level_atk) {
        this.level_atk = level_atk;
    }

    public int getLevel_hp() {
        return level_hp;
    }

    public void setLevel_hp(int level_hp) {
        this.level_hp = level_hp;
    }
}
