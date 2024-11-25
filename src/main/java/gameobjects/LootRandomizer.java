package gameobjects;

import java.util.Random;

public class LootRandomizer {

    private static final Random rand = new Random();

    // Method to simulate a random weapon drop based on drop chances from the WeaponType enum
    public static Weapon randomWeaponDrop() {
        // Calculate the total drop chance (sum of all individual drop chances)
        double totalDropChance = 0.0;
        for (Weapon.WeaponType weaponType : Weapon.WeaponType.values()) {
            totalDropChance += weaponType.getDropChance();
        }

        // Generate a random number between 0 and the totalDropChance
        double roll = rand.nextDouble() * totalDropChance;
        double currentChance = 0.0;

        // Loop through the weapon types and determine which one drops
        for (Weapon.WeaponType weaponType : Weapon.WeaponType.values()) {
            currentChance += weaponType.getDropChance();
            if (roll <= currentChance) {
                // Return the weapon corresponding to the drop
                return weaponType.toWeapon();
            }
        }

        // Fallback (although this shouldn't be hit if drop chances are set correctly)
        return null;
    }

    //repeat same code for random armor drop
    public static Armor randomArmorDrop() {
        double totalDropChance = 0.0;
        for (Armor.ArmorType armorType : Armor.ArmorType.values()) {
            totalDropChance += armorType.getDropChance();
        }

        double roll = rand.nextDouble() * totalDropChance;
        double currentChance = 0.0;

        for (Armor.ArmorType armorType : Armor.ArmorType.values()) {
            currentChance += armorType.getDropChance();
            if (roll <= currentChance) {
                return armorType.toArmor();
            }
        }

        return null;
    }

    public static void main(String[] args) {
            Weapon droppedWeapon = randomWeaponDrop();
            System.out.println("Dropped weapon: " + droppedWeapon);

            Armor armor = randomArmorDrop();
            System.out.println("Armor: " + armor);
        }
}
