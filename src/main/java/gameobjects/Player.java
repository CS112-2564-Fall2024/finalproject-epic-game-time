package gameobjects;

import gameobjects.enemy.Enemy;

public class Player {

    //constant values will be starting values for character

    //Variables
    private double playerMaxHealthPoints;
    private double playerHealthPoints;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    public boolean isAlive;

    //Full constructor
    public Player(double playerMaxHealthPoints, Weapon equippedWeapon, Armor equippedArmor) {
        this.playerMaxHealthPoints = playerMaxHealthPoints;
        this.playerHealthPoints = playerMaxHealthPoints;
        this.equippedWeapon = equippedWeapon;
        this.equippedArmor = equippedArmor;
        this.isAlive = true;
    }

    //getter for health
    public double getPlayerHealthPoints() {
        return playerHealthPoints;
    }

    //getter for max health
    public double getPlayerMaxHealthPoints() {
        return playerMaxHealthPoints;
    }

    public void setPlayerHealthPoints(double playerHealthPoints) {
        this.playerHealthPoints = playerHealthPoints;
    }

    public void setPlayerMaxHealthPoints(double playerMaxHealthPoints) {
        this.playerMaxHealthPoints = playerMaxHealthPoints;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() { return equippedArmor; }

    private void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    //boolean to check player status alive or dead (true or false)
    public boolean isAlive() {
        return isAlive;
    }

    //method to handle how the player takes damage
    public void playerTakeDamage(int enemyDamage) {
        this.playerHealthPoints -= equippedArmor.calculateBlockedDamage(enemyDamage);
        //prevent going below 0 hp
        if (playerHealthPoints <= 0) {
            playerHealthPoints = 0;
            isAlive = false;
        }
    }

    //this heals the player
    public void heal(int healAmount) {
        playerHealthPoints += healAmount;
        // cap to max health
        if (playerHealthPoints > playerMaxHealthPoints) {
            playerHealthPoints = playerMaxHealthPoints;
        }

    }

    // equip a new weapon/armor to player

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        System.out.println("Equipped the " + weapon.getName());
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
        System.out.println("Equipped the " + armor.getName());
    }


    //code that is not needed for project
    public void switchWeapon(Weapon newWeapon) {
        this.equippedWeapon = newWeapon;
        System.out.println("switched to the " + newWeapon.getName());
    }

    //calculated attack based on weapon damage, and its crit percent
    //Crit does 1.5x damage
    public void playerAttack(Enemy enemy) {
        double damage = equippedWeapon.calculateDamage();  // Get weapon damage
        enemy.enemyTakeDamage(damage);// Let the enemy handle its damage and blocking logicDamageAfterDefense + " damage.");
    }

    //if player block,
    // reduces damage by 50% + the block percentage of current equipped armor minus the armor rating
    public void playerBlock(double enemyDamage) {
        double calculatedDamage = (enemyDamage * (0.5 + getEquippedArmor().getBlockPercentage().orElse(0.0)));

        //set player health to 0 if it goes below 0
        playerTakeDamage((int) calculatedDamage);

        if(playerHealthPoints <= 0) {
            playerHealthPoints = 0;
            isAlive = false;
        }
    }

    //all this does is return the value of attack after it is calculated through the block formula for output in the console
    public double playerBlockValue (double enemyDamage) {
        double playerBlockValue = (enemyDamage * (0.5 + getEquippedArmor().getBlockPercentage().orElse(0.0))
                - getEquippedArmor().getArmorValue());

        if (playerBlockValue <= 0) {
            playerBlockValue = 0;
        }

        return playerBlockValue;
    }

    //Testing method to display current health

    public void displayHealthPoints() {
        System.out.println("Player Health Points: " + playerHealthPoints + "/" + playerMaxHealthPoints);
    }

    public String toString() {
        return "{ Health: " + playerHealthPoints + ", Weapon: " + equippedWeapon.getName() + ", Armor: " + equippedArmor.getName() + " }";
    }
}
