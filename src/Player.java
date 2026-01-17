public class Player {
    private int health;
    private int damage;
    private int level;
    private int max_health;
    private boolean poisoned;
    private boolean frozen;
    private boolean shielding;
    private String name;




    public Player (int health, int level, String name) {
        max_health = health;
        this.health = health;
        this.name = name;
        this.level = level;
        shielding = false;
        poisoned = false;
        frozen = false;
        damage = 1;
    }


    public void takeDamage(int dmg) {
        if (health - dmg < 0) {
            health = 0;
        } else if (health - dmg > max_health) {
            health = max_health;
        } else {
            health -= dmg;
        }
    }


    public void setLevel(int level) {
        this.level = level;
    }


    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }


    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public void setShielding(boolean shielding) {
        this.shielding = shielding;
    }

    public boolean isShielding() {
        return shielding;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public int getHealth() {
        return health;
    }

    public int getMax_health() {
        return max_health;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }


    public String getName() {
        return name;
    }


    public String getInfo () {
        return "Health: " + getHealth() + "\nLevel: " + getLevel() + "\n--Options--" + "\n1. Attack" + "\n2. Spells" + "\n3. Shield";
    }
}
