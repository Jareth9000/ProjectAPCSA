import java.util.HashMap;
public class Spells {


    private HashMap<String, String> possibleSpells = new HashMap<>();


    public Spells() {
        possibleSpells.put("Poison", "Does 10 damage per turn.");
    }


    public void Poison (Player enemy) {
        enemy.setPoisoned(true);
    }


    public int Thunder(Player caster, Player enemy) {
        if (caster.getLevel() > 3 && Math.random() > 0.5) {
            if (!(possibleSpells.containsKey("Thunder"))) {
                possibleSpells.put("Thunder", "50% chance to inflict 50 damage.");
            }
            return 50;
        }
        return 0;
    }


    public int Heal(Player caster) {
        if (caster.getLevel() > 7) {
            if (!(possibleSpells.containsKey("Heal"))) {
                possibleSpells.put("Heal", "Heals user for 3 times their level and poison.");
            }
            return 3 * caster.getLevel();
        }
        return 0;
    }


    public void Freeze (Player caster, Player enemy) {
        if (caster.getLevel() > 11) {
            if (!(possibleSpells.containsKey("Freeze"))) {
                possibleSpells.put("Freeze", "Opponent cannot move on this turn.");
            }
            enemy.setFrozen(true);
        }
    }


    public HashMap<String, String> getPossibleSpells() {
        return possibleSpells;
    }
}
