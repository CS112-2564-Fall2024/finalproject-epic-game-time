package gameobjects;

public class Player {
    private int maxHealthPoints;
    private int healthPoints;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    //Full constructor
    public Player(int maxHealthPoints, Weapon equippedWeapon, Armor equippedArmor) {
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
        this.equippedWeapon = equippedWeapon;
        this.equippedArmor = equippedArmor;
    }

    //getter for health
    public int getHealthpoints() {
        return healthPoints;
    }

    //getter for max health
    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    //taking damage
    public void takeDamage(int enemyDamage) {
        healthPoints -= equippedArmor.calculateBlockedDamage(enemyDamage);
        //prevent going below 0 hp
        if (healthPoints <= 0) {
            healthPoints = 0;
        }
    }

    //to heal player
    public void heal(int healAmount) {
        healthPoints += healAmount;
        // cap to max health
        if (healthPoints > maxHealthPoints) {
            healthPoints = maxHealthPoints;
        }

    }

    // equip a new weapon to player
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        System.out.println("Equipped the " + weapon.getName());
    }

    //TODO not final way for switch weapon
    public void switchWeapon(Weapon newWeapon) {
        this.equippedWeapon = newWeapon;
        System.out.println("switched to the " + newWeapon.getName());
    }

    //calculated attack based on weapon damage, and its crit percent
    //Crit does 1.5x damage
    public void attack() {
        double damage = equippedWeapon.calculateDamage();
        System.out.println("Attacks with " + equippedWeapon.getName() + " dealing " + damage + " damage.");
    }

    //boolean check if player is still alive
    public boolean isDead() {
        return healthPoints == 0;
    }

    //Testing method to display current health
    public void displayHealthPoints() {
        System.out.println("Health Points: " + healthPoints+ "/" + maxHealthPoints);
    }
    public String toString() {
        return "{ Health: " + healthPoints + ", Weapon: " + equippedWeapon.getName() + ", Armor: " + equippedArmor.getName() + " }";
    }
}
