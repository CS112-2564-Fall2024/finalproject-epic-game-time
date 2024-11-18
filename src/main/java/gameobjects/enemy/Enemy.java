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



}
