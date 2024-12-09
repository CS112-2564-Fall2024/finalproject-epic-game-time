package gameobjects.enemy;

import gameobjects.Player;
import javafx.scene.image.Image;

public abstract class Enemy {

    private String name;
    private double enemyHealth;
    private double enemyMaxHealth;
    private int attackDamage;
    private int defense;
    public boolean enemyIsAlive;

    //Full Constructor
    public Enemy(String name, double enemyMaxHealth, int attackDamage, int defense) {
        this.name = name;
        this.enemyMaxHealth = enemyMaxHealth;
        this.enemyHealth = enemyMaxHealth;
        this.attackDamage = attackDamage;
        this.defense = defense;
        enemyIsAlive = true;
    }

    //setters/getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(double enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public double getEnemyMaxHealth() {
        return enemyMaxHealth;
    }

    public void setEnemyMaxHealth(double enemyMaxHealth) {
        this.enemyMaxHealth = enemyMaxHealth;
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

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public abstract Image getImage();

    public boolean enemyIsAlive() {
        return enemyIsAlive;
    }

    public void enemyTakeDamage(double damage) {
        // Calculate blocked damage (e.g., based on armor, defense, etc.)
        double finalDamage = calculateBlockedDamageEnemy(damage);  // Block damage and calculate final damage

        // Apply the final damage to enemy health
        this.enemyHealth -= finalDamage;

        if (enemyHealth <= 0) {
            enemyHealth = 0;
            enemyIsAlive = false;
        }
    }

    //TODO might have to create a function built from scratch to determine the damage, consider player defense
//    public double enemyDamageValue() {
//        double enemyDamageValue = getAttackDamage()
//    }

    public double calculateBlockedDamageEnemy(double damage) {
        damage -= getDefense();

        if (damage <= 0) {
            damage = 0;
        }

        return damage;
    }

    public void displayHealthEnemy() {
        System.out.println("Enemy Health: " + enemyHealth + "/" + enemyMaxHealth);
    }

    public abstract void enemyAttack(Player player);
}
