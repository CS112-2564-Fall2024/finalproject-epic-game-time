package gameobjects;

import java.util.Optional;
import java.util.Random;

public class Weapon {

    public enum WeaponType {
        //Weapon types based on rarity
        //white 60% drop chance
        IRON_SWORD("Iron Sword", 4, Optional.of(0.0), "white", 0.1),
        IRON_MACE("Iron Mace", 4,Optional.of(0.0),"white", 0.1),
        GREAT_SWORD("Great Sword", 6,Optional.of(0.02),"white", 0.1),
        BOW("Bow", 4,Optional.of(0.0),"white", 0.1),
        LONGBOW("LongBow", 6,Optional.of(0.03),"white", 0.1),
        SPEAR("Spear", 7,Optional.of(0.01),"white", 0.1),

        //Green 34% drop chance
        WAR_HAMMER("War Hammer", 8,Optional.of(0.05), "green",0.07),
        HALBERD("Halberd", 10,Optional.of(0.03),"green",0.07),
        KATANA("Katana", 6,Optional.of(0.02),"green", 0.07),
        LONGSWORD("LongSword", 11,Optional.of(0.02)," green", 0.07),
        KHOPESH("Khopesh", 9,Optional.of(0.05),"green", 0.06),

        //Red 6%
        SERGALS_BLOOD("Sergal's Blood", 15,Optional.of(0.08),"red", 0.02),
        // life steal after every attack, 10% of damage
        ORCAS_GLAVE("Orca's Glave", 9,Optional.of(0.07), "red", 0.02),
        //on hit damage + ice (forst DOT 8 damage every 2 turns)
        FLAME_REND("FlameRend", 12,Optional.of(0.1), "red",0.02);
        //applies additional fire damage(4 DOT every turn)

        private final String name;
        private final int attackDamage;
        private final Optional<Double> critChance;
        private final String rarity;
        private final double dropChance;

        //enum constrctor
        WeaponType(String name, int attackDamage, Optional<Double> critChance, String rarity, double dropChance) {
            this.name = name;
            this.attackDamage = attackDamage;
            this.critChance = critChance;
            this.rarity = rarity;
            this.dropChance = dropChance;
        }

        //turns enum weapon into actual object
        public Weapon toWeapon() {
            return new Weapon(name, attackDamage, critChance, rarity);
        }

        //getter for drop chance
        public double getDropChance() {
            return dropChance;
        }

    }

    //Variables
    private String name;
    private int attackDamage;
    private Optional<Double> critChance; // optional as some weapons do not have crit while others do
    private String rarity;

    //Full constructor
    public Weapon(String name, int attackDamage, Optional<Double> critChance, String rarity) {
        this.name = name;
        this.attackDamage = attackDamage;
        this.critChance = critChance;
        this.rarity = rarity;
    }

    //getters/setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public Optional<Double> getCritChance() {
        return critChance;
    }

    public void setCritChance(Optional<Double> critChance) {
        this.critChance = critChance;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    //calculates damage with crit chance as well
    public double calculateDamage() {
        double damage = getAttackDamage();

        //apply the crit multiplier and check if crit hit landed
        if(critChance.isPresent() && isCriticalHit(critChance.get())) {
            damage *= 1.5;
        }

        damage += applyFireDamage();

        return damage;
    }

    //roll to determine if hit is a crit
    //creates a random double and if its less/equal then crit chance is a success
    private boolean isCriticalHit(double critChance) {
        Random random = new Random();
        return random.nextDouble() <= critChance;
    }

    //TODO change to special effects and account for red weapon effects, fire damage, life steal, something else
    public double applyFireDamage() {
        double fireDamage = 5.0;

        if(name.equalsIgnoreCase("flamerend")) {
            return fireDamage;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Weapon{name= " + name + "', attackDamage= " + attackDamage + ", critChance= " + critChance.orElse(0.0)
                + ", rarity= " + rarity + "}";
    }
}
