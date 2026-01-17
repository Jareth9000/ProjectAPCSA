import java.util.HashMap;
public class Spells {

    /*
    This hash map stores the spells that the user can use based on their
    level along with a description of what each spell does. Every method
    that represents a spell (except poison) checks if any key in the hash map
    matches the spell's name before the spell is cast.
     */
    private HashMap<String, String> possibleSpells = new HashMap<>();


    public Spells() {
        possibleSpells.put("Poison", "Does 10 damage per turn.");
    }

    public void putSpell(String spell, String description) {
        possibleSpells.put(spell, description);
    }

    public void Poison (Player enemy) {
        enemy.setPoisoned(true);
    }


    public void Thunder(Player enemy) {
        if (possibleSpells.containsKey("Thunder")) {
            if (Math.random() > 0.5) {
                enemy.takeDamage(50);
            } else {
                System.out.println("You missed!");
            }
        }
    }


    public void Heal(Player caster) {
        if (possibleSpells.containsKey("Heal")) {
            caster.takeDamage(-3 * caster.getLevel());
            System.out.println("You have healed to " + caster.getHealth() + " health!");
        }
    }


    public void Freeze (Player enemy) {
        if ((possibleSpells.containsKey("Freeze"))) {
            enemy.setFrozen(true);
        }
    }

    public HashMap<String, String> getPossibleSpells() {
        return possibleSpells;
    }
}
