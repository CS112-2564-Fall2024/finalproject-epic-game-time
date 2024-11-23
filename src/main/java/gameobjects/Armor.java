package gameobjects;

import java.util.Optional;

public class Armor {

    public enum ArmorType {
        //Armor types based on rarity
        //white 60% drop chance
        CHAIN_MAIL("Chain Mail", 4, Optional.of(0.0), "white", 0.1),
        IRON_ARMOR("Iron Armor", 6, Optional.of(0.0), "white", 0.1),
        SILVER_ARMOR("Silver Armor", 5, Optional.of(0.0), "white", 0.1),
        LEATHER_ARMOR("Leather Armor", 2, Optional.of(0.0), "white", 0.1),
        PLATED_ARMOR("Plated Armor", 7, Optional.of(0.0), "white", 0.1),
        BANDED_ARMOR("Banded Armor", 3, Optional.of(0.0), "white", 0.1),

        //Green 34% drop chance
        SPLINTED_ARMOR("Splinted Armor", 6, Optional.of(0.03), "green", 0.07),
        LAMELLAR_ARMOR("Lamellar Armor", 7, Optional.of(0.02), "green", 0.07),
        BRIGANDINE_ARMOR("Brigandine Armor", 8, Optional.of(0.04), "green", 0.07),
        SCALE_ARMOR("Scale Armor", 10, Optional.of(0.03), "green", 0.06),
        VIKING_ARMOR("Viking Armor", 7, Optional.of(0.05), "green", 0.07),

        //red 6% drop chance
        SCALES_OF_IMMORTALITY("Scales of Immortality", 12, Optional.of(0.09), "red", 0.02),
        //grants 5 health after every turn
        DEATH_OF_DELIRIUM("Death of Delirium", 14, Optional.of(0.07), "red", 0.02),
        //+5 damage, + 5% crit chance
        BERSERKERS_ARMOR("Berserkers Armor", 7, Optional.of(0.02), "red", 0.02);
        //grants double damage

        //variables
        private final String name;
        private final int armorValue;
        private final Optional<Double> blockPercentage;
        private final String rarity;
        private final double dropChance;

        //enum constructor
        ArmorType(String name, int armorValue, Optional<Double> blockPercentage, String rarity, double dropChance) {
            this.name = name;
            this.armorValue = armorValue;
            this.blockPercentage = blockPercentage;
            this.rarity = rarity;
            this.dropChance = dropChance;
        }

        //converts enum armor to actually armor object
        public Armor toArmor() {
            return new Armor(name, armorValue, blockPercentage, rarity);
        }

        //gets drop chance
        public double getDropChance() {
            return dropChance;
        }
    }

    //variables
    private String name;
    private int armorValue;
    private Optional<Double> blockPercentage;
    private String rarity;

    //full constructor
    public Armor(String name, int armorValue, Optional<Double> blockPercentage, String rarity) {
        this.name = name;
        this.armorValue = armorValue;
        this.blockPercentage = blockPercentage;
        this.rarity = rarity;
    }

    //GETTERS/SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
    }

    public Optional<Double> getBlockPercentage() {
        return blockPercentage;
    }

    public void setBlockPercentage(Optional<Double> blockPercentage) {
        this.blockPercentage = blockPercentage;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    //calculates damage received based on defense of armor
    public int calculateBlockedDamage(int damage) {
            damage -= getArmorValue();

            if (damage <= 0) {
                damage = 0;
            }

        return damage;
    }

    //TODO add specialty cases to red rarity armor

    public String toString() {
        return "Armor{Name= " + name + ", Defense= " + armorValue + ", BlockPercentage= " + blockPercentage.orElse(0.0) + ", Rarity= " + rarity + "}";
    }

}
