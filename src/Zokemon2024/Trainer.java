/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zokemon2024;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Trainer {

    protected String name;
    protected int money, zokeball;
    protected ArrayList<Zokemon> list_zoke = new ArrayList<Zokemon>();
    protected ArrayList<Zokemon> list_fainted = new ArrayList<Zokemon>();

    public Trainer() {
        money = 200;
        zokeball = 0;
    }

    public void addZokemon(Zokemon zoke) {
        list_zoke.add(zoke);
    }


    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getZokeball() {
        return zokeball;
    }

    public ArrayList<Zokemon> getList_woke() {
        return list_zoke;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setZokeball(int zokeball) {
        this.zokeball = zokeball;
    }

    public void setList_woke(ArrayList<Zokemon> list_zoke) {
        this.list_zoke = list_zoke;
    }

    public void MinusMoney(int minus) {
        money -= minus;
    }

    public void AddMoney(int plus) {
        money += plus;
    }

    public Zokemon getZoke(String zokename) {
        int index = -1;
        for (int a = 0; a < list_zoke.size(); a++) {
            if (list_zoke.get(a).getName().equals(zokename)) {
                index = a;
                break;
            }
        }
        return list_zoke.get(index);
    }

    public ArrayList<String> getAllWokeNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Zokemon a : list_zoke) {
            names.add(a.getName());
        }
        return names;
    }

    public ArrayList<Zokemon> getList_fainted() {
        return list_fainted;
    }

    public ArrayList<Zokemon> ExtractDead() {
        for (int a = 0; a < list_zoke.size(); a++) {
            Zokemon CheckingWokemon = list_zoke.get(a);
            if (CheckingWokemon.isFaint() == true) {
                if (list_fainted.size() == 0) {
                    list_fainted.add(list_zoke.get(a));
                } else {
                    boolean alreadyAdded = false;
                    for (int b = 0; b < list_fainted.size(); b++) {
                        if (CheckingWokemon == list_fainted.get(b)) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (alreadyAdded == false) {
                        list_fainted.add(CheckingWokemon);
                    }
                }
            }
        }
        return list_fainted;
    }

    public void MinusWokeball(int minus) {
        zokeball -= minus;
    }

    public boolean AddWokeball(int plus) {
        boolean nomoney = false;
        if (money < 10) {
            nomoney = true;
        } else {
            zokeball += plus;
            money -= 10;
        }
        return nomoney;
    }
}
