import java.util.Scanner;
public class Game {

    private Scanner scan;
    private Player hero;
    private Player enemy;
    private Spells spells;

    public Game() {
        scan = new Scanner(System.in);
    }


    public String introMessage() {
        return "Hello " + hero.getName() + "!\nIn this game there are 20 rounds total where you will fight increasingly stronger enemies.\nTry to beat them all!";
    }

    public void playGame() {
        System.out.print("Welcome! What is your name: ");
        hero = new Player(100, 1, scan.nextLine());
        System.out.println(introMessage());
        spells = new Spells();
        for (int i = 1; i < 21; i++) {
            enemy = new Player(100 * i, i, "Enemy");
            System.out.println("*----Round " + i + "----*");
            playRound();
            if (hero.isDead()) {
                System.out.print("Oh no, you lost on round " + i + "! Thank you for playing.");
                break;
            } else {
                System.out.println("---Level Up!---");
                System.out.println("You won the round and level up.");
                hero.levelUp();
                // if statements below check user's level and append the possibleSpells hash map in the Spells class as needed
                if (hero.getLevel() == 4) {
                    System.out.println("You unlocked the Thunder spell!");
                    spells.putSpell("Thunder","50% chance to do 50 damage.");
                } else if (hero.getLevel() == 8) {
                    System.out.println("You unlocked the Heal spell!");
                    spells.putSpell("Heal","Heals user for 3 times their level.");
                } else if (hero.getLevel() == 12) {
                    System.out.println("You unlocked the Freeze spell!");
                    spells.putSpell("Freeze","Enemy cannot move on their next turn.");
                }
            }
        }
        System.out.println("Congrats! You won!");
    }

    // them method below is the code for when the user chooses to use a spell (option 2)
    public void useSpell () {
        scan.nextLine();
        System.out.println(spells.getPossibleSpells());
        System.out.print("Type exact name of the spell you want to use: ");
        String spell = scan.nextLine();
        // intellij recommended, more concise substitute for if statements
        switch (spell) {
            case "Poison" -> spells.Poison(enemy);
            case "Thunder" -> spells.Thunder(enemy);
            case "Heal" -> spells.Heal(hero);
            case "Freeze" -> spells.Freeze(enemy);
            default -> {
                System.out.println("Witchcraft! This spell isn't possible to use yet or doesn't exist!");
                System.out.println("You take 10 damage in a spell gone wrong!");
                hero.takeDamage(10);
            }
        }
    }

    public void heroTurn() {
        System.out.println("---Your Turn---");
        System.out.println(hero.getInfo());
        System.out.print("Type the number of the option you wish to choose: ");
        int choice = scan.nextInt();
        if (choice == 1) {
            enemy.takeDamage(hero.getLevel() * hero.getDamage());
            System.out.println("You attack for " + hero.getLevel() * hero.getDamage() + " damage.");
        } else if (choice == 2) {
            useSpell();
        } else if (choice == 3) {
            hero.setShielding(true);
        } else {
            // if user doesn't type a possible option
            System.out.println("You...do nothing?");
        }
    }

    public void enemyTurn() {
        System.out.println("---Enemy Turn---");
        System.out.println("Enemy health: " + enemy.getHealth());
        if (enemy.isPoisoned()) {
            System.out.println("Enemy takes 10 poison damage!");
            enemy.takeDamage(10);
        }
        if (!(enemy.isFrozen()) && !(enemy.isDead())) {
            int atk = enemy.getDamage() * enemy.getLevel();
            if (hero.isShielding() && atk <= hero.getMax_health() / 10) {
                System.out.println("Your shield protects you, but the enemy still grows in strength!");
            } else {
                // shielding is still checked for to give the user a warning that the shield option won't work anymore for the current round
                if (hero.isShielding()) {
                    System.out.println("Your shield isn't strong enough!");
                }
                System.out.println("Enemy attacks for: " + atk + " damage and grows stronger.");
                hero.takeDamage(atk);
            }
            // resets user's shield to go down and increase enemy's strength by a random amount (2 to 4) to make game harder
            hero.setShielding(false);
            enemy.setDamage(enemy.getDamage() + (int) ((Math.random() * 2) + 2));
        } else if (!(enemy.isDead())) {
            System.out.println("Enemy is frozen and can't do anything!");
            // freeze only lasts for 1 turn, so the enemy's frozen status is reset afterward
            enemy.setFrozen(false);
        }
    }

    public void playRound() {
        while (enemy.getHealth() > 0 && hero.getHealth() > 0) {
            heroTurn();
            if (enemy.isDead()) {
                System.out.println("Enemy defeated!");
                break;
            }
            // the hero can die on their own turn in the rare case they incorrectly type a spell (lines 60 - 64) and go to 0 health
            if (hero.isDead()) {
                System.out.println("You were defeated!");
                break;
            }
            enemyTurn();
            if (hero.isDead()) {
                System.out.println("You were defeated!");
                break;
            } else if (enemy.isDead()) {
                System.out.println("Enemy defeated!");
                break;
            }
        }
    }
}