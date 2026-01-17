import java.util.Scanner;
public class Game {

    private Scanner scan;
    private Player hero;
    private Player enemy;
    private Spells spells;

    public Game() {
        scan = new Scanner(System.in);
    }


    public void playGame() {
        System.out.print("Welcome! What is your name: ");
        hero = new Player(100, 1, scan.nextLine());
        spells = new Spells();
        for (int i = 1; i < 21; i++) {
            enemy = new Player(100 * i, i, "Enemy");
            hero.setLevel(i);
            playRound();
            i++;
        }
    }

    public void useSpell () {
        System.out.println(spells.getPossibleSpells());
        System.out.print("Type exact name of the spell you want to use: ");
        String spell = scan.nextLine();
        if (spell.equals("Poison")) {
            spells.Poison(enemy);
        } else if (spell.equals("Thunder")) {
            spells.Thunder(hero,enemy);
        } else if (spell.equals("Heal")) {
            spells.Heal(hero);
        } else if (spell.equals("Freeze")) {
            spells.Freeze(hero,enemy);
        } else {
            System.out.println("Witchcraft! This spell isn't possible to use yet or doesn't exist!");
            hero.takeDamage(10);
        }
    }

    public void playRound() {
        while (enemy.getHealth() > 0 && hero.getHealth() > 0) {
            System.out.println("---Your Turn---");
            System.out.println(hero.getInfo());
            System.out.print("Type the number of the option you wish to choose: ");
            int choice = scan.nextInt();
            if (choice == 1) {
                enemy.takeDamage(hero.getLevel() * hero.getDamage());
            } else if (choice == 2) {
                useSpell();
            } else {
                hero.setShielding(true);
            }
            System.out.println("---Enemy Turn---");
            if (enemy.isPoisoned()) {
                System.out.println("Enemy takes poison damage!");
                enemy.takeDamage(10);
            }
            if (!(enemy.isFrozen())) {
                int atk = enemy.getDamage() * enemy.getLevel();
                if (hero.isShielding() && atk <= hero.getMax_health() / 10) {
                    System.out.println("Your shield protects you!");
                } else {
                    if (hero.isShielding()) {
                        System.out.println("Your shield isn't strong enough!");
                    }
                    System.out.println("Enemy attacks for: " + atk + "damage.");
                    hero.takeDamage(enemy.getDamage() * enemy.getLevel());
                }
                hero.setShielding(false);
            } else {
                enemy.setFrozen(false);
            }
        }

    }
}