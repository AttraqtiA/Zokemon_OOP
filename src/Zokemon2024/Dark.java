package Zokemon2024;

public class Dark extends Zokemon {
    public Dark() {
        super();
        type = "Light";
        level_atk = 3;
        level_hp = 2;
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

        } else {
            enemy.MinusHP(attack);
        }
        return attack;
    }
}
