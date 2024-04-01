/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zokemon2024;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class Game {
    private Trainer torena;
    private Zokemon CurrentWoke, enemy_woke;
    private Random r = new Random();

    public void BecomeTorena() {
        Scanner s;
        s = new Scanner(System.in);
        System.out.print("==========WELCOME TO ZOKEMON==========\nInput your name : ");
        String name = s.next() + s.nextLine();
        torena = new Trainer();
        while (true) {
            System.out.print("\nChoose your Trainer Class : \n1. Aquamancer\n2. Pyromaniac\n3. Druid\nYour Option : ");
            String torena_input = s.next() + s.nextLine();
            if (torena_input.equalsIgnoreCase("1")) {
                torena = new Aquamancer();
                break;
            } else if (torena_input.equalsIgnoreCase("2")) {
                torena = new Pyromaniac();
                break;
            } else if (torena_input.equalsIgnoreCase("3")) {
                torena = new Druid();
                break;
            } else {
                System.out.println("Wrong input!");
            }
        }
        torena.setName(name);

        Zokemon woke = new Zokemon();
        while (true) {
            System.out.print("\n==========Choose your new Zokemon!==========\nChoose your Zokemon Type : \n1. Water\n2. Fire\n3. Grass\nYour Option : ");
            String woke_input = s.next() + s.nextLine();
            if (woke_input.equalsIgnoreCase("1")) {
                woke = new Water();
                break;
            } else if (woke_input.equalsIgnoreCase("2")) {
                woke = new Fire();
                break;
            } else if (woke_input.equalsIgnoreCase("3")) {
                woke = new Grass();
                break;
            } else {
                System.out.println("Unknown input!");
            }
        }
        System.out.print("\nInput the name of your very first Zokemon : ");
        String wokename = s.next() + s.nextLine();
        woke.setName(wokename);
        torena.addZokemon(woke);
        CurrentWoke = torena.getZoke(wokename);

        while (true) {
            //====================================================CHECK/SWITCH
            if (torena.ExtractDead().size() == torena.getList_woke().size()) {
                        System.out.println("Bad Ending : No Zokemon Found, All Presumely DEAD");
                        System.exit(0);
            }
            System.out.println("\n==========Welcome " + torena.getName() + "!==========");
            System.out.print("1. Check Zokemon\n2. Wild Encounter\n3. Tamer Battle\n4. Shop\n0. Quit\nYour Option : ");
            String input1 = s.next() + s.nextLine();
            if (input1.equalsIgnoreCase("1")) {
                while (true) {
                    System.out.println("==========Your Zokemon List==========");
                    for (int a = 0; a < torena.getList_woke().size(); a++) {

                        System.out.print("[" + (a + 1) + "]" + torena.getAllWokeNames().get(a));
                        if (torena.getList_woke().get(a) instanceof Fire) {
                            System.out.print(" || Fire");
                        } else if (torena.getList_woke().get(a) instanceof Water) {
                            System.out.print(" || Water");
                        } else if (torena.getList_woke().get(a) instanceof Grass) {
                            System.out.print(" || Grass");
                        }
                        if (torena.getList_woke().get(a).isFaint() == true) {
                            System.out.print("[DEAD]");
                        }
                    }

                    System.out.println("\nCheck Zokemon Index (0 to go Back) : ");
                    int index = 0;
                    while (true) {
                        index = 0;
                        boolean validInput = false;
                        while (!validInput) {
                            if (s.hasNextInt()) {
                                index = s.nextInt();
                                validInput = true;
                            } else {
                                System.out.println("Please enter an integer!");
                                s.next();
                            }
                        }
                        if (index >= 0 && index <= torena.getList_woke().size()) {
                            if (index == 0) {
                                break;
                            }
                            if (torena.getList_woke().get(index - 1).isFaint() == true) {
                                System.out.println("The chosen zokemon is unalive, revive it first!");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Index not found!");
                        }
                    }
                    if (index == 0) {
                        System.out.println("Back to main menu");
                        break;
                    }

                    while (true) {
                        int x = index - 1;
                        System.out.println("==========Chosen Zokemon==========");
                        System.out.println("Name : " + torena.getList_woke().get(x).getName());
                        if (torena.getList_woke().get(x) instanceof Water) {
                            System.out.println("Type : " + ((Water) torena.getList_woke().get(x)).getType());
                        } else if (torena.getList_woke().get(x) instanceof Fire) {
                            System.out.println("Type : " + ((Fire) torena.getList_woke().get(x)).getType());
                        } else if (torena.getList_woke().get(x) instanceof Grass) {
                            System.out.println("Type : " + ((Grass) torena.getList_woke().get(x)).getType());
                        }
                        System.out.println("Level : " + torena.getList_woke().get(x).getLevel() + "\nHealth : " + torena.getList_woke().get(x).getCurrHP() + "/" + torena.getList_woke().get(x).getMaxHP()
                                + "\nAttack : " + torena.getList_woke().get(x).getAttack() + "\nRarity : " + torena.getList_woke().get(x).getRarity() + "\n==============================");

                        System.out.print("1. Rename Zokemon\n2. Set Active Zokemon\n0. Back\nYour Option : ");
                        String input2 = s.next() + s.nextLine();
                        if (input2.equalsIgnoreCase("1")) {
                            System.out.print("New Zokemon name : ");
                            String newname = s.next() + s.nextLine();
                            torena.getList_woke().get(x).setName(newname);
                        } else if (input2.equalsIgnoreCase("2")) {
                            CurrentWoke = torena.getList_woke().get(x);
                            System.out.println(CurrentWoke.getName() + " is now The Active Zokemon!");
                        } else if (input2.equalsIgnoreCase("0")) {
                            break;
                        } else {
                            System.out.println("Unknown Input!");
                        }
                    }

                }

                //==========================================================================================================================
                //==========================================================================================================================
                //==========================================================================================================================
            } else if (input1.equalsIgnoreCase("2")) {
                int enemytype = r.nextInt(3) + 1;
                if (enemytype == 1) {
                    enemy_woke = new Water();
                    enemy_woke.setType("Water");
                    enemy_woke.setName("Watermon");
                } else if (enemytype == 2) {
                    enemy_woke = new Fire();
                    enemy_woke.setType("Fire");
                    enemy_woke.setName("Firemon");
                } else if (enemytype == 3) {
                    enemy_woke = new Grass();
                    enemy_woke.setType("Grass");
                    enemy_woke.setName("Grassmon");
                }
                int main_attack_boost = 0;
                while (true) {
                    if (torena.ExtractDead().size() == torena.getList_woke().size()) {
                        System.out.println("Bad Ending : No Zokemon Found, All Presumely DEAD");
                        System.exit(0);
                    } else if (CurrentWoke.isFaint() == true) {
                        while (true) {
                            boolean temp = false;
                            System.out.println("==========Your Zokemon List==========");
                            for (int a = 0; a < torena.getList_woke().size(); a++) {

                                System.out.print("[" + (a + 1) + "]" + torena.getAllWokeNames().get(a));
                                if (torena.getList_woke().get(a) instanceof Fire) {
                                    System.out.print(" || Fire");
                                } else if (torena.getList_woke().get(a) instanceof Water) {
                                    System.out.print(" || Water");
                                } else if (torena.getList_woke().get(a) instanceof Grass) {
                                    System.out.print(" || Grass");
                                }
                                if (torena.getList_woke().get(a).isFaint() == true) {
                                    System.out.print("[DEAD]");
                                }
                                System.out.println("");
                            }

                            System.out.println("\nCheck Zokemon Index : ");
                            int index = 0;
                            while (true) {
                                index = 0;
                                boolean validInput = false;
                                while (!validInput) {
                                    if (s.hasNextInt()) {
                                        index = s.nextInt();
                                        validInput = true;
                                    } else {
                                        System.out.println("Please enter an integer!");
                                        s.next();
                                    }
                                }
                                if (index > 0 && index <= torena.getList_woke().size()) {
                                    if (index == 0) {
                                        break;
                                    }
                                    if (torena.getList_woke().get(index - 1).isFaint() == true) {
                                        System.out.println("The chosen zokemon is unalive, revive it first!");
                                    } else {
                                        break;
                                    }
                                } else {
                                    System.out.println("Index not found!");
                                }
                            }

                            while (true) {
                                int x = index - 1;
                                System.out.println("==========Chosen Zokemon==========");
                                System.out.println("Name : " + torena.getList_woke().get(x).getName());
                                if (torena.getList_woke().get(x) instanceof Water) {
                                    System.out.println("Type : " + ((Water) torena.getList_woke().get(x)).getType());
                                } else if (torena.getList_woke().get(x) instanceof Fire) {
                                    System.out.println("Type : " + ((Fire) torena.getList_woke().get(x)).getType());
                                } else if (torena.getList_woke().get(x) instanceof Grass) {
                                    System.out.println("Type : " + ((Grass) torena.getList_woke().get(x)).getType());
                                }
                                System.out.println("Level : " + torena.getList_woke().get(x).getLevel() + "\nHealth : " + torena.getList_woke().get(x).getCurrHP() + "/" + torena.getList_woke().get(x).getMaxHP()
                                        + "\nAttack : " + torena.getList_woke().get(x).getAttack() + "\nRarity : " + torena.getList_woke().get(x).getRarity() + "\n==============================");

                                System.out.print("1. Set Active Zokemon\n0. Back\nYour Option : ");
                                String input3 = s.next() + s.nextLine();
                                if (input3.equalsIgnoreCase("1")) {
                                    CurrentWoke = torena.getList_woke().get(x);
                                    System.out.println(CurrentWoke.getName() + " is now The Active Zokemon!");
                                    temp = true;
                                    break;
                                } else if (input3.equalsIgnoreCase("0")) {
                                    break;
                                } else {
                                    System.out.println("Unknown Input!");
                                }
                            }
                            if (temp == true) {
                                break;
                            }
                        }
                    }
                    System.out.println("\n==========BATTLE!!==========");
                    System.out.println("Type Chart : -Water --> Fire --> Grass-");
                    System.out.println("####  Enemy Zokemon !!! ####");
                    System.out.println("Enemy name : " + enemy_woke.getName() + "\nEnemy level : " + enemy_woke.getLevel() + "\nEnemy Health : " + enemy_woke.getCurrHP() + "/" + enemy_woke.getMaxHP());

                    System.out.println("\n@@@ Your Zokemon !! @@@");
//                    if (CurrentWoke instanceof Water) {
//                        ((Water) CurrentWoke).ActivateMoist();
//                        System.out.println("$ Your Zokemon is Activating Moist! Level up + " + ((Water) CurrentWoke).GetMoist() + " $");
//                    }
                    System.out.println("Your name : " + CurrentWoke.getName() + "\nYour level : " + CurrentWoke.getLevel() + "\nYour health : " + CurrentWoke.getCurrHP() + "/" + CurrentWoke.getMaxHP());

                    System.out.println("====================\n1. Attack\n2. Catch\n3. Use Item!\n4. Change!\n0. Run!\nYour Turn : ");
                    String input2 = s.next() + s.nextLine();

                    // VIOLENCE
                    if (input2.equalsIgnoreCase("1")) {
                        if (CurrentWoke instanceof Fire) {
                            int dealt = ((Fire) CurrentWoke).DealExtraDamage(enemy_woke);
                            if (enemy_woke instanceof Grass) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (enemy_woke instanceof Water) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("Your " + CurrentWoke.getName() + " attacked the enemy with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                        } else if (CurrentWoke instanceof Water) {
                            int dealt = ((Water) CurrentWoke).DealDamage(enemy_woke);
                            if (enemy_woke instanceof Fire) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (enemy_woke instanceof Grass) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("Your " + CurrentWoke.getName() + " attacked the enemy! (" + dealt + " dealt!)");
                        } else if (CurrentWoke instanceof Grass) {
                            int dealt = ((Grass) CurrentWoke).DealDamage(enemy_woke);
                            if (enemy_woke instanceof Water) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (enemy_woke instanceof Fire) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("Your " + CurrentWoke.getName() + " attacked the enemy! (" + dealt + " dealt!)");
                        }
                        if (enemy_woke instanceof Fire) {
                            int dealt = ((Fire) enemy_woke).DealExtraDamage(CurrentWoke);
                            if (CurrentWoke instanceof Grass) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Water) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                        } else if (enemy_woke instanceof Water) {
                            int dealt = ((Water) enemy_woke).DealDamage(CurrentWoke);
                            if (CurrentWoke instanceof Fire) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Grass) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                        } else if (enemy_woke instanceof Grass) {
                            int dealt = ((Grass) enemy_woke).DealDamage(CurrentWoke);
                            if (CurrentWoke instanceof Water) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Fire) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                        }

                        if (CurrentWoke.getCurrHP() <= 0) {
                            CurrentWoke.setFaint(true);

                            if (main_attack_boost != 0) {
                                for (int a = 0; a < main_attack_boost; a++) {
                                    CurrentWoke.RevertBoost();
                                }
                            }
//                            if (CurrentWoke instanceof Water) {
//                                ((Water) CurrentWoke).RevertMoist();
//                            }
                            break;
                        }
                        if (enemy_woke.getCurrHP() <= 0) {
                            System.out.println("You Won!!! $200 and 1 level up to all!");
                            torena.AddMoney(200);
                            for (int a = 0; a < torena.getList_woke().size(); a++) {
                                torena.getList_woke().get(a).addLevel(1);
                            }
                            if (main_attack_boost != 0) {
                                for (int a = 0; a < main_attack_boost; a++) {
                                    CurrentWoke.RevertBoost();
                                }
                            }
//                            if (CurrentWoke instanceof Water) {
//                                ((Water) CurrentWoke).RevertMoist();
//                            }
                            break;
                        }
                        if (CurrentWoke instanceof Grass) {
                            CurrentWoke.HealHP(((Grass) CurrentWoke).Heal());
                        }
                        if (enemy_woke instanceof Grass) {
                            enemy_woke.HealHP(((Grass) enemy_woke).Heal());
                        }
//                        if (CurrentWoke instanceof Water) {
//                            ((Water) CurrentWoke).RevertMoist();
//                        }
                        //CATCH'EM ALL
                    } else if (input2.equalsIgnoreCase("2")) {
                        boolean temp = false;
                        if (torena.getZokeball() <= 0) {
                            if (torena instanceof Aquamancer) {
                                if (((Aquamancer) torena).getOceball() == 0) {
                                    System.out.println("Your Balls are non-existent");
                                    break;
                                }
                            } else if (torena instanceof Pyromaniac) {
                                if (((Pyromaniac) torena).getVolcaball() == 0) {
                                    System.out.println("Your Balls are non-existent");
                                    break;
                                }
                            } else if (torena instanceof Druid) {
                                if (((Druid) torena).getGaiaball() == 0) {
                                    System.out.println("Your Balls are non-existent");
                                    break;
                                }
                            }
                        }
                        System.out.println("==========Your Balls==========");
                        System.out.println("[1.] Wokeballs x " + torena.getZokeball());
                        if (torena instanceof Aquamancer) {
                            System.out.println("[2.] Oceballs x " + ((Aquamancer) torena).getOceball());
                        } else if (torena instanceof Pyromaniac) {
                            System.out.println("[2.] Volcaballs x " + ((Pyromaniac) torena).getVolcaball());
                        } else if (torena instanceof Druid) {
                            System.out.println("[2.] Treballs x " + ((Druid) torena).getGaiaball());
                        }
                        while (true) {
                            System.out.print("Your Option : ");
                            String input3 = s.next() + s.nextLine();
                            if (input3.equalsIgnoreCase("1") || input3.equalsIgnoreCase("2")) {
                                if (input3.equalsIgnoreCase("1")) {
                                    if (torena.getZokeball() == 0) {
                                        System.out.println("You don't have any Wokeball!");
                                    } else {
                                        torena.MinusWokeball(1);
                                        int chance = r.nextInt(enemy_woke.getRarity()) + 1;
                                        if (chance == 1) {
                                            System.out.println("You caught the wild Zokemon!!");
                                            System.out.print("Name this Zokemon : ");
                                            String caughtwoke = s.next() + s.nextLine();
                                            enemy_woke.setName(caughtwoke);
                                            torena.addZokemon(enemy_woke);
                                            System.out.println("Welcome to the party! " + caughtwoke + "!");
                                            temp = true;
                                        } else {
                                            System.out.println("Catch attempt failed!");
                                        }
                                        break;
                                    }
                                } else if (input3.equalsIgnoreCase("2")) {
                                    int rateup = 0;
                                    if (torena instanceof Aquamancer) {
                                        if (((Aquamancer) torena).getOceball() == 0) {
                                            System.out.println("You don't have any Oceball!");
                                        } else {
                                            if (enemy_woke instanceof Water) {
                                                rateup = 3;
                                            } else {
                                                rateup = 1;
                                            }
                                            ((Aquamancer) torena).MinusOceball(1);
                                            int chance = r.nextInt(enemy_woke.getRarity()) + 1;
                                            if (chance >= 1 && chance <= rateup) {
                                                System.out.println("You caught the wild Zokemon!!");
                                                System.out.print("Name this Zokemon : ");
                                                String caughtwoke = s.next() + s.nextLine();
                                                enemy_woke.setName(caughtwoke);
                                                torena.addZokemon(enemy_woke);
                                                System.out.println("Welcome to the party! " + caughtwoke + "!");
                                                temp = true;
                                            } else {
                                                System.out.println("Catch attempt failed!");
                                            }
                                            break;
                                        }
                                    } else if (torena instanceof Pyromaniac) {
                                        if (((Pyromaniac) torena).getVolcaball() == 0) {
                                            System.out.println("You don't have any Volcaball!");
                                        } else {
                                            if (enemy_woke instanceof Fire) {
                                                rateup = 3;
                                            } else {
                                                rateup = 1;
                                            }
                                            ((Pyromaniac) torena).MinusVolcaball(1);
                                            int chance = r.nextInt(enemy_woke.getRarity()) + 1;
                                            if (chance >= 1 && chance <= rateup) {
                                                System.out.println("You caught the wild Zokemon!!");
                                                System.out.print("Name this Zokemon : ");
                                                String caughtwoke = s.next() + s.nextLine();
                                                enemy_woke.setName(caughtwoke);
                                                torena.addZokemon(enemy_woke);
                                                System.out.println("Welcome to the party! " + caughtwoke + "!");
                                                temp = true;
                                            } else {
                                                System.out.println("Catch attempt failed!");
                                            }
                                            break;
                                        }
                                    } else if (torena instanceof Druid) {
                                        if (((Druid) torena).getGaiaball() == 0) {
                                            System.out.println("You don't have any Treball!");
                                        } else {
                                            if (enemy_woke instanceof Grass) {
                                                rateup = 3;
                                            } else {
                                                rateup = 1;
                                            }
                                            ((Druid) torena).MinusGaiaball(1);
                                            int chance = r.nextInt(enemy_woke.getRarity()) + 1;
                                            if (chance >= 1 && chance <= rateup) {
                                                System.out.println("You caught the wild Zokemon!!");
                                                System.out.print("Name this Zokemon : ");
                                                String caughtwoke = s.next() + s.nextLine();
                                                enemy_woke.setName(caughtwoke);
                                                torena.addZokemon(enemy_woke);
                                                System.out.println("Welcome to the party! " + caughtwoke + "!");
                                                temp = true;
                                            } else {
                                                System.out.println("Catch attempt failed!");
                                            }
                                            break;
                                        }
                                    }
                                }

                            } else {
                                System.out.println("Unknown Input!!");
                            }
                        }

                        if (temp == true) { // musuh tercatch
                            if (main_attack_boost != 0) {
                                for (int a = 0; a < main_attack_boost; a++) {
                                    CurrentWoke.RevertBoost();
                                }
                            }
//                            if (CurrentWoke instanceof Water) {
//                                ((Water) CurrentWoke).RevertMoist();
//                            }
                            break;
                        }

                        if (enemy_woke instanceof Fire) {
                            int dealt = ((Fire) enemy_woke).DealExtraDamage(CurrentWoke);
                            if (CurrentWoke instanceof Grass) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Water) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                        } else if (enemy_woke instanceof Water) {
                            int dealt = ((Water) enemy_woke).DealDamage(CurrentWoke);
                            if (CurrentWoke instanceof Fire) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Grass) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                        } else if (enemy_woke instanceof Grass) {
                            int dealt = ((Grass) enemy_woke).DealDamage(CurrentWoke);
                            if (CurrentWoke instanceof Water) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Fire) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                        }


                        if (CurrentWoke.getCurrHP() <= 0) {
                            CurrentWoke.setFaint(true);
                        }
                        if (CurrentWoke instanceof Grass) {
                            CurrentWoke.HealHP(((Grass) CurrentWoke).Heal());
                        }
                        if (enemy_woke instanceof Grass) {
                            enemy_woke.HealHP(((Grass) enemy_woke).Heal());
                        }
//                        if (CurrentWoke instanceof Water) {
//                            ((Water) CurrentWoke).RevertMoist();
//                        }

                        // ITEMS
                    } else if (input2.equalsIgnoreCase("3")) {
                        System.out.println("==========YOUR ITEM LIST==========");
                        if (torena instanceof Aquamancer) {
                            if (((Aquamancer) torena).getPotion() + ((Aquamancer) torena).getAttack_boost() != 0) {
                                while (true) {
                                    System.out.println("[1.] Potion x " + ((Aquamancer) torena).getPotion());
                                    System.out.println("[2.] Attack Boost x " + ((Aquamancer) torena).getAttack_boost());
                                    System.out.print("Your Option : ");
                                    String input3 = s.next() + s.nextLine();
                                    if (input3.equalsIgnoreCase("1")) {
                                        ((Aquamancer) torena).UsePotion(1, CurrentWoke);
                                        break;
                                    } else if (input3.equalsIgnoreCase("2")) {
                                        ((Aquamancer) torena).UseBoost(1, CurrentWoke);
                                        main_attack_boost++;
                                        break;
                                    } else {
                                        System.out.println("Unknown Input!");
                                    }
                                }
                            } else {
                                System.out.println("You Have no Item!");
                            }
                        } else if (torena instanceof Pyromaniac) {
                            if (((Pyromaniac) torena).getAttack_boost() + ((Pyromaniac) torena).getRevive() != 0) {
                                while (true) {
                                    System.out.println("[1.] Attack Boost x " + ((Pyromaniac) torena).getAttack_boost());
                                    System.out.println("[2.] Revive x " + ((Pyromaniac) torena).getRevive());
                                    String input3 = s.next() + s.nextLine();
                                    if (input3.equalsIgnoreCase("1")) {
                                        ((Pyromaniac) torena).UseBoost(1, CurrentWoke);
                                        main_attack_boost++;
                                        break;
                                    } else if (input3.equalsIgnoreCase("2")) {
                                        int dead_count = 0;
                                        for (int a = 0; a < torena.getList_woke().size(); a++) {
                                            if (torena.getList_woke().get(a).isFaint() == true) {
                                                dead_count++;
                                            }
                                        }
                                        if (dead_count == 0) {
                                            System.out.println("No Revivable Zokemon Available! You're doing great!");
                                        } else {
                                            int index_torevive = r.nextInt(torena.ExtractDead().size());
                                            ((Pyromaniac) torena).UseRevive(1, torena.ExtractDead().get(index_torevive)); // begitu dirubah jadi true, dia sudah otomatis masuk hidup
                                        }
                                        break;
                                    } else {
                                        System.out.println("Unknown Input!");
                                    }
                                }
                            } else {
                                System.out.println("You Have no Item!");
                            }
                        } else if (torena instanceof Druid) {
                            if (((Druid) torena).getPotion() + ((Druid) torena).getRevive() != 0) {
                                while (true) {
                                    System.out.println("[1.] Potion x " + ((Druid) torena).getPotion());
                                    System.out.println("[2.] Revive x " + ((Druid) torena).getRevive());
                                    String input3 = s.next() + s.nextLine();
                                    if (input3.equalsIgnoreCase("1")) {
                                        ((Druid) torena).UsePotion(1, CurrentWoke);
                                        break;
                                    } else if (input3.equalsIgnoreCase("2")) {
                                        int dead_count = 0;
                                        for (int a = 0; a < torena.getList_woke().size(); a++) {
                                            if (torena.getList_woke().get(a).isFaint() == true) {
                                                dead_count++;
                                            }
                                        }
                                        if (dead_count == 0) {
                                            System.out.println("No Revivable Zokemon Available! You're doing great!");
                                        } else {
                                            int index_torevive = r.nextInt(torena.ExtractDead().size());
                                            ((Pyromaniac) torena).UseRevive(1, torena.ExtractDead().get(index_torevive));
                                        }
                                        break;
                                    } else {
                                        System.out.println("Unknown Input!");
                                    }
                                }
                            }
                        } else {
                            System.out.println("You Have no Item!");
                        }

                        if (enemy_woke instanceof Fire) {
                            int dealt = ((Fire) enemy_woke).DealExtraDamage(CurrentWoke);
                            if (CurrentWoke instanceof Grass) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Water) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                        } else if (enemy_woke instanceof Water) {
                            int dealt = ((Water) enemy_woke).DealDamage(CurrentWoke);
                            if (CurrentWoke instanceof Fire) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Grass) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                        } else if (enemy_woke instanceof Grass) {
                            int dealt = ((Grass) enemy_woke).DealDamage(CurrentWoke);
                            if (CurrentWoke instanceof Water) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Fire) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                        }


                        if (CurrentWoke.getCurrHP() <= 0) {

                            CurrentWoke.setFaint(true);
                            if (main_attack_boost != 0) {
                                for (int a = 0; a < main_attack_boost; a++) {
                                    CurrentWoke.RevertBoost();
                                }
                            }
//                            if (CurrentWoke instanceof Water) {
//                                ((Water) CurrentWoke).RevertMoist();
//                            }
                            break;
                        }
                        if (CurrentWoke instanceof Grass) {
                            CurrentWoke.HealHP(((Grass) CurrentWoke).Heal());
                        }
                        if (enemy_woke instanceof Grass) {
                            enemy_woke.HealHP(((Grass) enemy_woke).Heal());
                        }
//                        if (CurrentWoke instanceof Water) {
//                            ((Water) CurrentWoke).RevertMoist();
//                        }
                        //CHANGE ACTIVE
                    } else if (input2.equalsIgnoreCase("4")) {
                        while (true) {
                            boolean temp = false;
                            System.out.println("==========Your Zokemon List==========");
                            for (int a = 0; a < torena.getList_woke().size(); a++) {

                                System.out.print("[" + (a + 1) + "]" + torena.getAllWokeNames().get(a));
                                if (torena.getList_woke().get(a) instanceof Fire) {
                                    System.out.print(" || Fire");
                                } else if (torena.getList_woke().get(a) instanceof Water) {
                                    System.out.print(" || Water");
                                } else if (torena.getList_woke().get(a) instanceof Grass) {
                                    System.out.print(" || Grass");
                                }
                                if (torena.getList_woke().get(a).isFaint() == true) {
                                    System.out.print("[DEAD]");
                                }
                                System.out.println("");
                            }

                            System.out.println("\nCheck Zokemon Index (0 to go Back) : ");
                            int index = 0;
                            while (true) {
                                index = 0;
                                boolean validInput = false;
                                while (!validInput) {
                                    if (s.hasNextInt()) {
                                        index = s.nextInt();
                                        validInput = true;
                                    } else {
                                        System.out.println("Please enter an integer!");
                                        s.next();
                                    }
                                }
                                if (index >= 0 && index <= torena.getList_woke().size()) {
                                    if (index == 0) {
                                        break;
                                    }
                                    if (torena.getList_woke().get(index - 1).isFaint() == true) {
                                        System.out.println("The chosen zokemon is unalive, revive it first!");
                                    } else {
                                        break;
                                    }
                                } else {
                                    System.out.println("Index not found!");
                                }
                            }
                            if (index == 0) {
                                System.out.println("Back to main menu");
                                break;
                            }
                            while (true) {
                                int x = index - 1;
                                System.out.println("==========Chosen Zokemon==========");
                                System.out.println("Name : " + torena.getList_woke().get(x).getName());
                                if (torena.getList_woke().get(x) instanceof Water) {
                                    System.out.println("Type : " + ((Water) torena.getList_woke().get(x)).getType());
                                } else if (torena.getList_woke().get(x) instanceof Fire) {
                                    System.out.println("Type : " + ((Fire) torena.getList_woke().get(x)).getType());
                                } else if (torena.getList_woke().get(x) instanceof Grass) {
                                    System.out.println("Type : " + ((Grass) torena.getList_woke().get(x)).getType());
                                }
                                System.out.println("Level : " + torena.getList_woke().get(x).getLevel() + "\nHealth : " + torena.getList_woke().get(x).getCurrHP() + "/" + torena.getList_woke().get(x).getMaxHP()
                                        + "\nAttack : " + torena.getList_woke().get(x).getAttack() + "\nRarity : " + torena.getList_woke().get(x).getRarity() + "\n==============================");

                                System.out.print("1. Set Active Zokemon\n0. Back\nYour Option : ");
                                String input3 = s.next() + s.nextLine();
                                if (input3.equalsIgnoreCase("1")) {
                                    CurrentWoke = torena.getList_woke().get(x);
                                    System.out.println(CurrentWoke.getName() + " is now The Active Zokemon!");
                                    temp = true;
                                    break;
                                } else if (input3.equalsIgnoreCase("0")) {
                                    break;
                                } else {
                                    System.out.println("Unknown Input!");
                                }
                            }
                            if (temp == true) {
                                break;
                            }
                        }

                        if (enemy_woke instanceof Fire) {
                            int dealt = ((Fire) enemy_woke).DealExtraDamage(CurrentWoke);
                            if (CurrentWoke instanceof Grass) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Water) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                        } else if (enemy_woke instanceof Water) {
                            int dealt = ((Water) enemy_woke).DealDamage(CurrentWoke);
                            if (CurrentWoke instanceof Fire) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Grass) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                        } else if (enemy_woke instanceof Grass) {
                            int dealt = ((Grass) enemy_woke).DealDamage(CurrentWoke);
                            if (CurrentWoke instanceof Water) {
                                System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                            } else if (CurrentWoke instanceof Fire) {
                                System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                            }
                            System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                        }


                        if (CurrentWoke.getCurrHP() <= 0) {
                            CurrentWoke.setFaint(true);

                            if (main_attack_boost != 0) {
                                for (int a = 0; a < main_attack_boost; a++) {
                                    CurrentWoke.RevertBoost();
                                }
                            }
//                            if (CurrentWoke instanceof Water) {
//                                ((Water) CurrentWoke).RevertMoist();
//                            }
                            break;
                        }
                        if (CurrentWoke instanceof Grass) {
                            CurrentWoke.HealHP(((Grass) CurrentWoke).Heal());
                        }
                        if (enemy_woke instanceof Grass) {
                            enemy_woke.HealHP(((Grass) enemy_woke).Heal());
                        }
//                        if (CurrentWoke instanceof Water) {
//                            ((Water) CurrentWoke).RevertMoist();
//                        }
                    } else if (input2.equalsIgnoreCase("0")) {
                        System.out.println("Dude, seriously?");
                        break;
                    } else {
                        System.out.println("Unknown Input!");
                    }
                    // SWITCH------------------
                }

                //==========================================================================================================================
                //==========================================================================================================================
                //==========================================================================================================================
            } else if (input1.equalsIgnoreCase("3")) {
                int enemy_size = r.nextInt(6) + 1;
                for (int b = 0; b < enemy_size; b++) {

                    int enemytype = r.nextInt(3) + 1;
                    if (enemytype == 1) {
                        enemy_woke = new Water();
                        enemy_woke.setType("Water");
                        enemy_woke.setName("Watermon");
                    } else if (enemytype == 2) {
                        enemy_woke = new Fire();
                        enemy_woke.setType("Fire");
                        enemy_woke.setName("Firemon");
                    } else if (enemytype == 3) {
                        enemy_woke = new Grass();
                        enemy_woke.setType("Grass");
                        enemy_woke.setName("Grassmon");
                    }

                    int main_attack_boost = 0;
                    while (true) {
                        if (torena.ExtractDead().size() == torena.getList_woke().size()) {
                            System.out.println("Bad Ending : No Zokemon Found, All Presumely DEAD");
                            System.exit(0);
                        } else if (CurrentWoke.isFaint() == true) {
                            while (true) {
                                boolean temp = false;
                                System.out.println("==========Your Zokemon List==========");
                                for (int a = 0; a < torena.getList_woke().size(); a++) {

                                    System.out.print("[" + (a + 1) + "]" + torena.getAllWokeNames().get(a));
                                    if (torena.getList_woke().get(a) instanceof Fire) {
                                        System.out.print(" || Fire");
                                    } else if (torena.getList_woke().get(a) instanceof Water) {
                                        System.out.print(" || Water");
                                    } else if (torena.getList_woke().get(a) instanceof Grass) {
                                        System.out.print(" || Grass");
                                    }
                                    if (torena.getList_woke().get(a).isFaint() == true) {
                                        System.out.print("[DEAD]");
                                    }
                                    System.out.println("");
                                }

                                System.out.println("\nCheck Zokemon Index : ");
                                int index = 0;
                                while (true) {
                                    index = 0;
                                    boolean validInput = false;
                                    while (!validInput) {
                                        if (s.hasNextInt()) {
                                            index = s.nextInt();
                                            validInput = true;
                                        } else {
                                            System.out.println("Please enter an integer!");
                                            s.next();
                                        }
                                    }
                                    if (index > 0 && index <= torena.getList_woke().size()) {
                                        if (index == 0) {
                                            break;
                                        }
                                        if (torena.getList_woke().get(index - 1).isFaint() == true) {
                                            System.out.println("The chosen zokemon is unalive, revive it first!");
                                        } else {
                                            break;
                                        }
                                    } else {
                                        System.out.println("Index not found!");
                                    }
                                }

                                while (true) {
                                    int x = index - 1;
                                    System.out.println("==========Chosen Zokemon==========");
                                    System.out.println("Name : " + torena.getList_woke().get(x).getName());
                                    if (torena.getList_woke().get(x) instanceof Water) {
                                        System.out.println("Type : " + ((Water) torena.getList_woke().get(x)).getType());
                                    } else if (torena.getList_woke().get(x) instanceof Fire) {
                                        System.out.println("Type : " + ((Fire) torena.getList_woke().get(x)).getType());
                                    } else if (torena.getList_woke().get(x) instanceof Grass) {
                                        System.out.println("Type : " + ((Grass) torena.getList_woke().get(x)).getType());
                                    }
                                    System.out.println("Level : " + torena.getList_woke().get(x).getLevel() + "\nHealth : " + torena.getList_woke().get(x).getCurrHP() + "/" + torena.getList_woke().get(x).getMaxHP()
                                            + "\nAttack : " + torena.getList_woke().get(x).getAttack() + "\nRarity : " + torena.getList_woke().get(x).getRarity() + "\n==============================");

                                    System.out.print("1. Set Active Zokemon\n0. Back\nYour Option : ");
                                    String input3 = s.next() + s.nextLine();
                                    if (input3.equalsIgnoreCase("1")) {
                                        CurrentWoke = torena.getList_woke().get(x);
                                        System.out.println(CurrentWoke.getName() + " is now The Active Zokemon!");
                                        temp = true;
                                        break;
                                    } else if (input3.equalsIgnoreCase("0")) {
                                        break;
                                    } else {
                                        System.out.println("Unknown Input!");
                                    }
                                }
                                if (temp == true) {
                                    break;
                                }
                            }
                        }
                        System.out.println("\n==========TAMER BATTLE!!==========");                    System.out.println("Type Chart : -Water --> Fire --> Grass-");

                        System.out.println("Type Chart : -Water --> Fire --> Grass-");

                        System.out.println("####  Enemy Zokemon !!! ####!");
                        System.out.println("Enemy name : " + enemy_woke.getName() + "\nEnemy level : " + enemy_woke.getLevel() + "\nEnemy Health : " + enemy_woke.getCurrHP() + "/" + enemy_woke.getMaxHP());
                        if (enemy_size != 1) {
                            System.out.println("Enemy's Total Passive Zokemon x " + (enemy_size - 1));
                        }

                        System.out.println("\n@@@ Your Zokemon !! @@@");
//                        if (CurrentWoke instanceof Water) {
//                            ((Water) CurrentWoke).ActivateMoist();
//                            System.out.println("$ Your Zokemon is Activating Moist! Level up + " + ((Water) CurrentWoke).GetMoist() + " $");
//                        }
                        System.out.println("Your name : " + CurrentWoke.getName() + "\nYour level : " + CurrentWoke.getLevel() + "\nYour health : " + CurrentWoke.getCurrHP() + "/" + CurrentWoke.getMaxHP());
                        if ((torena.getList_woke().size() - torena.ExtractDead().size()) != 1) {
                            System.out.println("Your Total Passive Zokemon x " + ((torena.getList_woke().size() - torena.ExtractDead().size()) - 1));
                        }

                        System.out.println("====================\n1. Attack\n2. Use Item!\n3. Change!\n0. Run!\nYour Turn : ");
                        String input2 = s.next() + s.nextLine();

                        // VIOLENCE
                        if (input2.equalsIgnoreCase("1")) {
                            if (CurrentWoke instanceof Fire) {
                                int dealt = ((Fire) CurrentWoke).DealExtraDamage(enemy_woke);
                                if (enemy_woke instanceof Grass) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (enemy_woke instanceof Water) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("Your " + CurrentWoke.getName() + " attacked the enemy with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                            } else if (CurrentWoke instanceof Water) {
                                int dealt = ((Water) CurrentWoke).DealDamage(enemy_woke);
                                if (enemy_woke instanceof Fire) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (enemy_woke instanceof Grass) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("Your " + CurrentWoke.getName() + " attacked the enemy! (" + dealt + " dealt!)");
                            } else if (CurrentWoke instanceof Grass) {
                                int dealt = ((Grass) CurrentWoke).DealDamage(enemy_woke);
                                if (enemy_woke instanceof Water) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (enemy_woke instanceof Fire) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("Your " + CurrentWoke.getName() + " attacked the enemy! (" + dealt + " dealt!)");
                            }
                            if (enemy_woke instanceof Fire) {
                                int dealt = ((Fire) enemy_woke).DealExtraDamage(CurrentWoke);
                                if (CurrentWoke instanceof Grass) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Water) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                            } else if (enemy_woke instanceof Water) {
                                int dealt = ((Water) enemy_woke).DealDamage(CurrentWoke);
                                if (CurrentWoke instanceof Fire) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Grass) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                            } else if (enemy_woke instanceof Grass) {
                                int dealt = ((Grass) enemy_woke).DealDamage(CurrentWoke);
                                if (CurrentWoke instanceof Water) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Fire) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                            }


                            if (CurrentWoke.getCurrHP() <= 0) {
                                CurrentWoke.setFaint(true);
                                break;
                            }
                            if (enemy_woke.getCurrHP() <= 0) {
                                System.out.println("You just defeated " + enemy_woke.getName() + "!");
                                enemy_size--;
                                break;
                            }
                            if (CurrentWoke instanceof Grass) {
                                CurrentWoke.HealHP(((Grass) CurrentWoke).Heal());
                            }
                            if (enemy_woke instanceof Grass) {
                                enemy_woke.HealHP(((Grass) enemy_woke).Heal());
                            }
//                            if (CurrentWoke instanceof Water) {
//                                ((Water) CurrentWoke).RevertMoist();
//                            }
                            // ITEMS
                        } else if (input2.equalsIgnoreCase("2")) {
                            System.out.println("==========YOUR ITEM LIST==========");
                            if (torena instanceof Aquamancer) {
                                if (((Aquamancer) torena).getPotion() + ((Aquamancer) torena).getAttack_boost() != 0) {
                                    while (true) {
                                        System.out.println("[1.] Potion x " + ((Aquamancer) torena).getPotion());
                                        System.out.println("[2.] Attack Boost x " + ((Aquamancer) torena).getAttack_boost());
                                        System.out.print("Your Option : ");
                                        String input3 = s.next() + s.nextLine();
                                        if (input3.equalsIgnoreCase("1")) {
                                            ((Aquamancer) torena).UsePotion(1, CurrentWoke);
                                            break;
                                        } else if (input3.equalsIgnoreCase("2")) {
                                            ((Aquamancer) torena).UseBoost(1, CurrentWoke);
                                            main_attack_boost++;
                                            break;
                                        } else {
                                            System.out.println("Unknown Input!");
                                        }
                                    }
                                } else {
                                    System.out.println("You Have no Item!");
                                }
                            } else if (torena instanceof Pyromaniac) {
                                if (((Pyromaniac) torena).getAttack_boost() + ((Pyromaniac) torena).getRevive() != 0) {
                                    while (true) {
                                        System.out.println("[1.] Attack Boost x " + ((Pyromaniac) torena).getAttack_boost());
                                        System.out.println("[2.] Revive x " + ((Pyromaniac) torena).getRevive());
                                        String input3 = s.next() + s.nextLine();
                                        if (input3.equalsIgnoreCase("1")) {
                                            ((Pyromaniac) torena).UseBoost(1, CurrentWoke);
                                            main_attack_boost++;
                                            break;
                                        } else if (input3.equalsIgnoreCase("2")) {
                                            int dead_count = 0;
                                            for (int a = 0; a < torena.getList_woke().size(); a++) {
                                                if (torena.getList_woke().get(a).isFaint() == true) {
                                                    dead_count++;
                                                }
                                            }
                                            if (dead_count == 0) {
                                                System.out.println("No Revivable Zokemon Available! You're doing great!");
                                            } else {
                                                int index_torevive = r.nextInt(torena.ExtractDead().size());
                                                ((Pyromaniac) torena).UseRevive(1, torena.ExtractDead().get(index_torevive)); // begitu dirubah jadi true, dia sudah otomatis masuk hidup
                                            }
                                            break;
                                        } else {
                                            System.out.println("Unknown Input!");
                                        }
                                    }
                                } else {
                                    System.out.println("You Have no Item!");
                                }
                            } else if (torena instanceof Druid) {
                                if (((Druid) torena).getPotion() + ((Druid) torena).getRevive() != 0) {
                                    while (true) {
                                        System.out.println("[1.] Potion x " + ((Druid) torena).getPotion());
                                        System.out.println("[2.] Revive x " + ((Druid) torena).getRevive());
                                        String input3 = s.next() + s.nextLine();
                                        if (input3.equalsIgnoreCase("1")) {
                                            ((Druid) torena).UsePotion(1, CurrentWoke);
                                            break;
                                        } else if (input3.equalsIgnoreCase("2")) {
                                            int dead_count = 0;
                                            for (int a = 0; a < torena.getList_woke().size(); a++) {
                                                if (torena.getList_woke().get(a).isFaint() == true) {
                                                    dead_count++;
                                                }
                                            }
                                            if (dead_count == 0) {
                                                System.out.println("No Revivable Zokemon Available! You're doing great!");
                                            } else {
                                                int index_torevive = r.nextInt(torena.ExtractDead().size());
                                                ((Pyromaniac) torena).UseRevive(1, torena.ExtractDead().get(index_torevive));
                                            }
                                            break;
                                        } else {
                                            System.out.println("Unknown Input!");
                                        }
                                    }
                                }
                            } else {
                                System.out.println("You Have no Item!");
                            }

                            if (enemy_woke instanceof Fire) {
                                int dealt = ((Fire) enemy_woke).DealExtraDamage(CurrentWoke);
                                if (CurrentWoke instanceof Grass) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Water) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                            } else if (enemy_woke instanceof Water) {
                                int dealt = ((Water) enemy_woke).DealDamage(CurrentWoke);
                                if (CurrentWoke instanceof Fire) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Grass) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                            } else if (enemy_woke instanceof Grass) {
                                int dealt = ((Grass) enemy_woke).DealDamage(CurrentWoke);
                                if (CurrentWoke instanceof Water) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Fire) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                            }


                            if (CurrentWoke.getCurrHP() <= 0) {
                                CurrentWoke.setFaint(true);
                                break;
                            }
                            if (CurrentWoke instanceof Grass) {
                                CurrentWoke.HealHP(((Grass) CurrentWoke).Heal());
                            }
                            if (enemy_woke instanceof Grass) {
                                enemy_woke.HealHP(((Grass) enemy_woke).Heal());
                            }
//                            if (CurrentWoke instanceof Water) {
//                                ((Water) CurrentWoke).RevertMoist();
//                            }

                            //CHANGE ACTIVE
                        } else if (input2.equalsIgnoreCase("3")) {
                            while (true) {
                                boolean temp = false;
                                System.out.println("==========Your Zokemon List==========");
                                for (int a = 0; a < torena.getList_woke().size(); a++) {

                                    System.out.print("[" + (a + 1) + "]" + torena.getAllWokeNames().get(a));
                                    if (torena.getList_woke().get(a) instanceof Fire) {
                                        System.out.print(" || Fire");
                                    } else if (torena.getList_woke().get(a) instanceof Water) {
                                        System.out.print(" || Water");
                                    } else if (torena.getList_woke().get(a) instanceof Grass) {
                                        System.out.print(" || Grass");
                                    }
                                    if (torena.getList_woke().get(a).isFaint() == true) {
                                        System.out.print("[DEAD]");
                                    }
                                    System.out.println("");
                                }

                                System.out.println("\nCheck Zokemon Index (0 to go Back) : ");
                                int index = 0;
                                while (true) {
                                    index = 0;
                                    boolean validInput = false;
                                    while (!validInput) {
                                        if (s.hasNextInt()) {
                                            index = s.nextInt();
                                            validInput = true;
                                        } else {
                                            System.out.println("Please enter an integer!");
                                            s.next();
                                        }
                                    }
                                    if (index >= 0 && index <= torena.getList_woke().size()) {
                                        if (index == 0) {
                                            break;
                                        }
                                        if (torena.getList_woke().get(index - 1).isFaint() == true) {
                                            System.out.println("The chosen zokemon is unalive, revive it first!");
                                        } else {
                                            break;
                                        }
                                    } else {
                                        System.out.println("Index not found!");
                                    }
                                }
                                if (index == 0) {
                                    System.out.println("Back to main menu");
                                    break;
                                }
                                while (true) {
                                    int x = index - 1;
                                    System.out.println("==========Chosen Zokemon==========");
                                    System.out.println("Name : " + torena.getList_woke().get(x).getName());
                                    if (torena.getList_woke().get(x) instanceof Water) {
                                        System.out.println("Type : " + ((Water) torena.getList_woke().get(x)).getType());
                                    } else if (torena.getList_woke().get(x) instanceof Fire) {
                                        System.out.println("Type : " + ((Fire) torena.getList_woke().get(x)).getType());
                                    } else if (torena.getList_woke().get(x) instanceof Grass) {
                                        System.out.println("Type : " + ((Grass) torena.getList_woke().get(x)).getType());
                                    }
                                    System.out.println("Level : " + torena.getList_woke().get(x).getLevel() + "\nHealth : " + torena.getList_woke().get(x).getCurrHP() + "/" + torena.getList_woke().get(x).getMaxHP()
                                            + "\nAttack : " + torena.getList_woke().get(x).getAttack() + "\nRarity : " + torena.getList_woke().get(x).getRarity() + "\n==============================");

                                    System.out.print("1. Set Active Zokemon\n0. Back\nYour Option : ");
                                    String input3 = s.next() + s.nextLine();
                                    if (input3.equalsIgnoreCase("1")) {
                                        CurrentWoke = torena.getList_woke().get(x);
                                        System.out.println(CurrentWoke.getName() + " is now The Active Zokemon!");
                                        temp = true;
                                        break;
                                    } else if (input3.equalsIgnoreCase("0")) {
                                        break;
                                    } else {
                                        System.out.println("Unknown Input!");
                                    }
                                }
                                if (temp == true) {
                                    break;
                                }
                            }

                            if (enemy_woke instanceof Fire) {
                                int dealt = ((Fire) enemy_woke).DealExtraDamage(CurrentWoke);
                                if (CurrentWoke instanceof Grass) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Water) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you with Blaze (Extra " + ((Fire) CurrentWoke).getBlaze() + " of Attack)! (" + dealt + " dealt!)");
                            } else if (enemy_woke instanceof Water) {
                                int dealt = ((Water) enemy_woke).DealDamage(CurrentWoke);
                                if (CurrentWoke instanceof Fire) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Grass) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                            } else if (enemy_woke instanceof Grass) {
                                int dealt = ((Grass) enemy_woke).DealDamage(CurrentWoke);
                                if (CurrentWoke instanceof Water) {
                                    System.out.println("Weakness Detected! 1.5x Damage Dealt!");
                                } else if (CurrentWoke instanceof Fire) {
                                    System.out.println("Enemy is prepared! Only 0.75x Damage Dealt!");
                                }
                                System.out.println("The Enemy " + enemy_woke.getName() + " attacked you! (" + dealt + " dealt!)");
                            }


                            if (CurrentWoke.getCurrHP() <= 0) {
                                CurrentWoke.setFaint(true);
                                break;
                            }
                            if (CurrentWoke instanceof Grass) {
                                CurrentWoke.HealHP(((Grass) CurrentWoke).Heal());
                            }
                            if (enemy_woke instanceof Grass) {
                                enemy_woke.HealHP(((Grass) enemy_woke).Heal());
                            }
//                            if (CurrentWoke instanceof Water) {
//                                ((Water) CurrentWoke).RevertMoist();
//                            }
                        } else if (input2.equalsIgnoreCase("0")) {
                            System.out.println("Dude, seriously?");
                            break;
                        } else {
                            System.out.println("Unknown Input!");
                        }

                        if (torena.ExtractDead().size() == torena.getList_woke().size()) {
                            break;
                        }
                        // SWITCH------------------
                    }
                    if (enemy_size == 0) {
                        System.out.println("You Won!!! $200 and 1 level up to all!");
                        torena.AddMoney(200);
                        for (int a = 0; a < torena.getList_woke().size(); a++) {
                            torena.getList_woke().get(a).addLevel(1);
                        }
                        if (main_attack_boost != 0) {
                            for (int a = 0; a < main_attack_boost; a++) {
                                CurrentWoke.RevertBoost();
                            }
                        }
//                        if (CurrentWoke instanceof Water) {
//                            ((Water) CurrentWoke).RevertMoist();
//                        }
                        break;
                    }

                }

                //==========================================================================================================================
                //==========================================================================================================================
                //==========================================================================================================================
            } else if (input1.equalsIgnoreCase("4")) {
                while (true) {
                    boolean nomoney = false;
                    System.out.println("==========WOKE MART==========\nWhat are you buying, Stranger?\nMoney : " + torena.getMoney() + "\n");
                    System.out.println("[1.] Wokeball $10");
                    if (torena instanceof Aquamancer) {
                        System.out.print("[2.] Potion $40\n[3.] Attack Boost $30\n[4.] Oceball $20\n[0.] Back\nYour Option : ");
                    } else if (torena instanceof Pyromaniac) {
                        System.out.print("[2.] Attack Boost $30\n[3.] Revive $100\n[4.] Volcaball $20\n[0.] Back\nYour Option : ");
                    } else if (torena instanceof Druid) {
                        System.out.print("[2.] Potion $40\n[3.] Revive $100\n[4.] Treball $20\n[0.] Back\nYour Option : ");
                    }
                    String input2 = s.next() + s.nextLine();
                    if (input2.equalsIgnoreCase("1")) {
                        nomoney = torena.AddWokeball(1);
                        System.out.println("Wokeboll added!");
                        if (nomoney == true) {
                            System.out.println("Not enough money! Go win some battle!");
                            break;
                        }
                    } else if (input2.equalsIgnoreCase("2")) {
                        if (torena instanceof Aquamancer) {
                            nomoney = ((Aquamancer) torena).AddPotion(1);
                            System.out.println("Potion added!");
                        } else if (torena instanceof Pyromaniac) {
                            nomoney = ((Pyromaniac) torena).AddBoost(1);
                            System.out.println("Attack Boost added!");
                        } else if (torena instanceof Druid) {
                            nomoney = ((Druid) torena).AddPotion(1);
                            System.out.println("Potion added!");
                        }
                        if (nomoney == true) {
                            System.out.println("Not enough money! Go win some battle!");
                            break;
                        }
                    } else if (input2.equalsIgnoreCase("3")) {
                        if (torena instanceof Aquamancer) {
                            nomoney = ((Aquamancer) torena).AddBoost(1);
                            System.out.println("Attack Boost added!");
                        } else if (torena instanceof Pyromaniac) {
                            nomoney = ((Pyromaniac) torena).AddRevive(1);
                            System.out.println("Revive added!");
                        } else if (torena instanceof Druid) {
                            nomoney = ((Druid) torena).AddRevive(1);
                            System.out.println("Revive added!");
                        }
                        if (nomoney == true) {
                            System.out.println("Not enough money! Go win some battle!");
                            break;
                        }
                    } else if (input2.equalsIgnoreCase("4")) {
                        if (torena instanceof Aquamancer) {
                            nomoney = ((Aquamancer) torena).AddOceball(1);
                            System.out.println("Oceball added to your pocket!");
                        } else if (torena instanceof Pyromaniac) {
                            nomoney = ((Pyromaniac) torena).AddVolcaball(1);
                            System.out.println("Volcaball added to your pocket!");
                        } else if (torena instanceof Druid) {
                            nomoney = ((Druid) torena).AddGaiaball(1);
                            System.out.println("Treball added to your pocket!");
                        }
                        if (nomoney == true) {
                            System.out.println("Not enough money! Go win some battle!");
                            break;
                        }
                    } else if (input2.equalsIgnoreCase("0")) {
                        System.out.println("Back to menu!");
                        break;
                    } else {
                        System.out.println("Unknown Input!");
                    }
                }
            } else if (input1.equalsIgnoreCase("0")) {
                System.out.println("See you next time!");
                System.exit(0);
            } else {
                System.out.println("Unknown input!");
            }
        }
    }

}
