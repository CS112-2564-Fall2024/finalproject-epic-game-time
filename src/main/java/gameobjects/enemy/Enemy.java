package gameobjects.enemy;

public class Enemy {

    private String name;
    private int health;
    private int maxHealth;
    private int attackDamage;
    private int defense;

    //Full Constructor
    public Enemy(String name, int maxHealth, int attackDamage, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackDamage = attackDamage;
        this.defense = defense;
    }

    //setters/getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage() {
        this.attackDamage = attackDamage;
    }

    public boolean enemyIsDead() {
        return health == 0;
    }

    public int enemyAttack () {
        int damage = getAttackDamage();
        return damage;
    }
}
